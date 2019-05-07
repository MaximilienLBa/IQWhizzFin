package com.lsinf1225.iqwhizz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lsinf1225.iqwhizz.Database.QuizContract;
import com.lsinf1225.iqwhizz.Database.QuizDbHelper;

import java.util.ArrayList;
import java.util.List;

public class Classement extends AppCompatActivity {
    private TextView score;
    private User account;
    private ListView mListView;
    private static int highscore;
    ArrayList <String> userTable = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);
        mListView = (ListView) findViewById(R.id.listView);
        //score = findViewById(R.id.score);
        loadScore();
        loadLeaderBoard();

    }



    private void loadScore(){
        Intent intent = getIntent();
        account = (User) intent.getSerializableExtra("account");
        QuizDbHelper db = new QuizDbHelper(this);
        int dbe = db.getScoreDB(account.getUsername());

        if(account.getScore()> highscore) {
            db.update(account);
            highscore = account.getScore();
        }
    }

    private void loadLeaderBoard(){
        QuizDbHelper db = new QuizDbHelper(this);
       List <User> users = db.getUser();
       int count = 1;
       for (User user : users){
           String username = user.getUsername();
           for (int i = username.length(); i <10;i++){
                   username= username+" ";
           }

           String leaderboard = count + "                               "
                   + username +" " + user.getScore() ;
           count++;
           userTable.add(leaderboard);

       }

       ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,userTable);
       mListView.setAdapter(adapter);

    }
}
