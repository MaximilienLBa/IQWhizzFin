package com.lsinf1225.iqwhizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lsinf1225.iqwhizz.Database.QuizDbHelper;

public class Classement extends AppCompatActivity {
    private TextView score;
    private User account;
    private static int highscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);

        score = findViewById(R.id.score);
        loadScore();
    }
    private void loadScore(){
        Intent intent = getIntent();
        account = (User) intent.getSerializableExtra("account");
        QuizDbHelper db = new QuizDbHelper(this);
        int dbe = db.getScoreDB(account.getUsername());

        if(account.getScore()> highscore) {
            db.update(account);
            highscore = account.getScore();
            score.setText("Votre highscore est : " + highscore);
        }
        else{
            score.setText("Votre highscore est : " + dbe);
        }

    }
}
