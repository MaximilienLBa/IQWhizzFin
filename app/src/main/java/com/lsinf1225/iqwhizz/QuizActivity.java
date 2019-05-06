package com.lsinf1225.iqwhizz;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lsinf1225.iqwhizz.Database.QuizDbHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCategory;
    private TextView textViewQuestionSet;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultrb;
    private  ColorStateList textColorDefaultcd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private boolean answered;
    public  int scoreDB;
    public static int scoreFinal;

    private long backPressedTime;

    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //lie les textView xml à java
        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewQuestionSet = findViewById(R.id.text_view_question_set);
        textViewCategory = findViewById(R.id.text_view_category);
        textViewCountDown = findViewById(R.id.text_view_countdown);

        //lie les boutons xml à java pour pouvoir leurs donnés des actions
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        //permet d'avoir les couleurs de base pour les changer par la suite
        textColorDefaultrb = rb1.getTextColors();
        textColorDefaultcd = textViewCountDown.getTextColors();

        //Affichage de la catégorie utilisé lors du quizz
        Intent intent = getIntent();
        int categoryID = intent.getIntExtra(StartingScreenActivity.EXTRA_CATEGORY_ID,0);
        String categoryName = intent.getStringExtra(StartingScreenActivity.EXTRA_CATEGORY_NAME);
        String questionSet = intent.getStringExtra(StartingScreenActivity.EXTRA_QUESTION_SET);

        textViewQuestionSet.setText(questionSet);
        textViewCategory.setText("Category: " + categoryName);

        if(savedInstanceState == null ){
            QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);

            questionList = dbHelper.getQuestions(questionSet,categoryID);
            if(StartingScreenActivity.rapidQuiz == true){
                questionCountTotal = 5;
            }else{
                questionCountTotal = 40;
            }
            Collections.shuffle(questionList);
            showNextQuestion();
        }else {
            questionList= savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            if (questionList == null){
                finish();
            }
            if(StartingScreenActivity.rapidQuiz == true){
                questionCountTotal = 5;
            }else{
                questionCountTotal = 40;
            }
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter -1);
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if (!answered){
                startCountDown();
            }else{
                updateCountDownText();
                showSolution();
            }
        }

        //Permet le passage à la question suivante
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        checkAnswer();
                    }else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();
                }
            }
        });

    }

    //Permet l'affichage de la bonne réponse et de montrer la suivante sauf si le quizz est fini
    private void showNextQuestion(){
        rb1.setTextColor(textColorDefaultrb);
        rb2.setTextColor(textColorDefaultrb);
        rb3.setTextColor(textColorDefaultrb);
        rb4.setTextColor(textColorDefaultrb);
        rbGroup.clearCheck();


        if (questionCounter < questionCountTotal){
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            questionCounter++;
            textViewQuestionCount.setText("Question" + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        }
        else {
            finishQuiz();
            StartingScreenActivity.rapidQuiz = false;
        }
    }

    //Permet de lancer le countdown
    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();

    }

    //permet d'update la couleur du texte du countdown en rouge si c'est moins de 10s
    private void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d",minutes,seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.RED);

        }else {
            textViewCountDown.setTextColor(textColorDefaultcd);
        }

    }

    //permet de check si la réponse donné par l'utilisateur est correct
    private void checkAnswer(){
        answered = true;

        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()){
            scoreDB++;
            textViewScore.setText("Score: " + scoreDB);
        }

        showSolution();
    }

    //Permet l'affichage des solutions, en rouge les réponses fausse, en vert les correct
    private void showSolution(){
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);


        switch (currentQuestion.getAnswerNr()) {
            case 1:
                rb1.setTextColor((Color.GREEN));
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor((Color.GREEN));
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor((Color.GREEN));
                textViewQuestion.setText("Answer 3 is correct");
                break;
            case 4:
                rb4.setTextColor((Color.GREEN));
                textViewQuestion.setText("Answer 4 is correct");
                break;
        }
        if (questionCounter < questionCountTotal){
            buttonConfirmNext.setText("Next");
        }
        else{
            buttonConfirmNext.setText("Finish");
        }
    }

    //Permet de terminer le quizz
    private void finishQuiz(){
        scoreFinal = scoreDB;
        scoreDB = 0;
        finish();
    }

    //si jamais l'utilisateur veut quitter l'appli il devra cliquer deux fois, à la première fois
    // il aura un warning et aura quelque seconde pour ré-appuyer pour quitter
    @Override
    public void onBackPressed() {
        if (backPressedTime +2000 > System.currentTimeMillis()){
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    //cancel le timer
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer !=null){
            countDownTimer.cancel();

        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST,questionList);
    }
}