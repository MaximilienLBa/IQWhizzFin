package com.lsinf1225.iqwhizz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class StartingScreenActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZZ = 1;
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";
    public static final String EXTRA_CATEGORY_ID = "extraCategoryId";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighscore;
    private Spinner spinnerCategory;

    private int highscore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        textViewHighscore = findViewById(R.id.text_view_highscore); // affichage du highscore
        spinnerCategory = findViewById(R.id.spinner_category); // affichage du spinner

        //Load les catégories
        loadCategories();
        //Load le highscore
        loadHighscore();

        // Lancement du quizz
        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });


    }

    //Lancement du quizz en fonction des catégories
    private void startQuiz() {
        Category selectedCategory = (Category) spinnerCategory.getSelectedItem();
        int categoryID = selectedCategory.getId();
        String categoryName = selectedCategory.getName();


        Intent intent = new Intent(StartingScreenActivity.this, QuizActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID,categoryID);
        intent.putExtra(EXTRA_CATEGORY_NAME,categoryName);
        startActivityForResult(intent,REQUEST_CODE_QUIZZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZZ){
            if (resultCode == RESULT_OK){
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE,0);
                if (score > highscore){
                    updateHighScore(score);
                }
            }
        }
    }
    private void loadCategories() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        List<Category> categories = dbHelper.getAllCategories();

        ArrayAdapter<Category> adapterCategories = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategories);
    }

    private void loadHighscore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE,0);
        textViewHighscore.setText("Highscore : "+ highscore);
    }

    private void updateHighScore(int highscoreNew){
        highscore = highscoreNew;
        textViewHighscore.setText("Highscore: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highscore);
        editor.apply();
    }
}