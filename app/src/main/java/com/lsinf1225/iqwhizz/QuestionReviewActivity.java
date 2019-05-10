package com.lsinf1225.iqwhizz;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.lsinf1225.iqwhizz.Database.QuizDbHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class QuestionReviewActivity extends AppCompatActivity {
    private RadioButton riq;
    private TextView Q1;
    private TextView R1;
    private TextView RU1;

    private TextView Q2;
    private TextView R2;
    private TextView RU2;

    private TextView Q3;
    private TextView R3;
    private TextView RU3;

    private TextView Q4;
    private TextView R4;
    private TextView RU4;

    private TextView Q5;
    private TextView R5;
    private TextView RU5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionreview);

        Q1=findViewById(R.id.q1);
        R1=findViewById(R.id.r1);
        RU1=findViewById(R.id.ru1);

        Q2=findViewById(R.id.q2);
        R2=findViewById(R.id.r2);
        RU2=findViewById(R.id.ru2);

        Q3=findViewById(R.id.q3);
        R3=findViewById(R.id.r3);
        RU3=findViewById(R.id.ru3);

        Q4=findViewById(R.id.q4);
        R4=findViewById(R.id.r4);
        RU4=findViewById(R.id.ru4);

        Q5=findViewById(R.id.q5);
        R5=findViewById(R.id.r5);
        RU5=findViewById(R.id.ru5);

        Button riq = findViewById(R.id.Review_back_button);

        setQuestion();

//bouton qui permet de revenir à review quiz
        riq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }

//affiche les questions, la bonne réponse et celle qu'on a fournie
    private void setQuestion(){

        if(QuizActivity.getQuestionCountTotal()==5){

            Q1.setText("Question 1: " +QuizActivity.getQtab(0));
            R1.setText( "True Answer: " +QuizActivity.getRtab(0));
            RU1.setText("Your Answer: " +QuizActivity.getRUtab(0));


            Q2.setText("Question 2: " +QuizActivity.getQtab(1));
            R2.setText( "True Answer: " +QuizActivity.getRtab(1));
            RU2.setText("Your Answer: " +QuizActivity.getRUtab(1));


            Q3.setText("Question 3: " +QuizActivity.getQtab(2));
            R3.setText( "True Answer: " +QuizActivity.getRtab(2));
            RU3.setText("Your Answer: " +QuizActivity.getRUtab(2));


            Q4.setText("Question 4: " +QuizActivity.getQtab(3));
            R4.setText( "True Answer: " +QuizActivity.getRtab(3));
            RU4.setText("Your Answer: " +QuizActivity.getRUtab(3));


            Q5.setText("Question 5: " +QuizActivity.getQtab(4));
            R5.setText( "True Answer: " +QuizActivity.getRtab(4));
            RU5.setText("Your Answer: " +QuizActivity.getRUtab(4));





        }

    }

}
