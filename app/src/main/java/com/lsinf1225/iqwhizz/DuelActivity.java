package com.lsinf1225.iqwhizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lsinf1225.iqwhizz.Database.QuizDbHelper;

import java.util.List;

public class DuelActivity extends AppCompatActivity{

    private Button startquiz,scoreuser1,scoreuser2;
    private TextView mTextViewRegister;
    private Spinner spinnerCategory;
    private Spinner spinnerQuestionSet;

    private static final int REQUEST_CODE_QUIZZ = 1;
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";
    public static final String EXTRA_CATEGORY_ID = "extraCategoryId";
    public static final String EXTRA_QUESTION_SET = "extraQuestionSet";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duel);

        spinnerCategory = findViewById(R.id.spinner_category); // affichage du spinner
        spinnerQuestionSet = findViewById(R.id.spinner_question_set);

        startquiz = findViewById(R.id.button_start_quiz);
        startquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
        scoreuser1 = findViewById(R.id.button_score_user1);
        scoreuser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private void startQuiz() {
        String questionSet = spinnerQuestionSet.getSelectedItem().toString();

        Category selectedCategory = (Category) spinnerCategory.getSelectedItem();
        int categoryID = selectedCategory.getId();
        String categoryName = selectedCategory.getName();


        Intent intent = new Intent(DuelActivity.this, QuizActivity.class);
        intent.putExtra(EXTRA_QUESTION_SET, questionSet);
        intent.putExtra(EXTRA_CATEGORY_ID,categoryID);
        intent.putExtra(EXTRA_CATEGORY_NAME,categoryName);
        startActivityForResult(intent,REQUEST_CODE_QUIZZ);
    }

}
