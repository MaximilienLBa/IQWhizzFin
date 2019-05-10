package com.lsinf1225.iqwhizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.lsinf1225.iqwhizz.Database.QuizContract;
import com.lsinf1225.iqwhizz.Database.QuizDbHelper;


import java.util.List;

public class StartingScreenActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZZ = 1;
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";
    public static final String EXTRA_CATEGORY_ID = "extraCategoryId";
    public static final String EXTRA_QUESTION_SET = "extraQuestionSet";

    private TextView mTextViewLogOut;
    private Spinner spinnerCategory;
    private Spinner spinnerQuestionSet;

    private TextView textViewUtilsateur;

    public static User account;

    public static boolean rapidQuiz = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        spinnerCategory = findViewById(R.id.spinner_category); // affichage du spinner
        spinnerQuestionSet = findViewById(R.id.spinner_question_set);
        textViewUtilsateur = findViewById(R.id.nom_utilisateur);

        String[] questionsSets = Question.getAllQuestionSet();
        ArrayAdapter<String> adapterQuestionSet = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,questionsSets);
        adapterQuestionSet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuestionSet.setAdapter(adapterQuestionSet);

        Intent intent = getIntent();
        account = (User) intent.getSerializableExtra("account");
        textViewUtilsateur.setText("Connecté : " + account.getUsername());

        //Load les catégories
        loadCategories();
        //Load le highscore
        Button buttonClassement = findViewById(R.id.button_leaderboard);
        buttonClassement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizDbHelper accountDB = new QuizDbHelper(getApplicationContext());
                User currentAccount = accountDB.find(account.getId());
                currentAccount.setScore(QuizActivity.scoreFinal);
                Intent intent = new Intent(StartingScreenActivity.this, Classement.class);
                intent.putExtra("account", currentAccount);
                startActivity(intent);

            }
        });

        // Lancement du quizz
        Button buttonNormalQuiz = findViewById(R.id.button_start_quiz);
        buttonNormalQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        Button buttonRapidQuiz = findViewById(R.id.button_rapid_quiz);
        buttonRapidQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rapidQuiz = true;
                startQuiz();
            }
        });

        Button buttonProfil = findViewById(R.id.button_prof);
        buttonProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(StartingScreenActivity.this, ProfileActivity.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });

        Button buttonFriend = findViewById(R.id.button_friends);
        buttonFriend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartingScreenActivity.this, FriendActivity.class);
                intent.putExtra("account",account);
                startActivity(intent);
            }
        });

        mTextViewLogOut = (TextView) findViewById(R.id.textview_logout);
        mTextViewLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(StartingScreenActivity.this, LoginActivity.class);
                startActivity(LoginIntent);
            }
        });
    }

    //Lancement du quizz en fonction des catégories
    private void startQuiz() {
        String questionSet = spinnerQuestionSet.getSelectedItem().toString();

        Category selectedCategory = (Category) spinnerCategory.getSelectedItem();
        int categoryID = selectedCategory.getId();
        String categoryName = selectedCategory.getName();


        Intent intent = new Intent(StartingScreenActivity.this, QuizActivity.class);
        intent.putExtra(EXTRA_QUESTION_SET, questionSet);
        intent.putExtra(EXTRA_CATEGORY_ID,categoryID);
        intent.putExtra(EXTRA_CATEGORY_NAME,categoryName);
        startActivityForResult(intent,REQUEST_CODE_QUIZZ);
    }


    private void loadCategories() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        List<Category> categories = dbHelper.getAllCategories();

        ArrayAdapter<Category> adapterCategories = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategories);
    }
}