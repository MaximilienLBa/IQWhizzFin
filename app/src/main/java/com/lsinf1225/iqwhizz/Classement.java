package com.lsinf1225.iqwhizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.lsinf1225.iqwhizz.Database.QuizDbHelper;

import java.util.ArrayList;
import java.util.List;

public class Classement extends AppCompatActivity {
    private User account;
    private ListView mListView;
    private int highscore;
    private Button buttonClassement;

    ArrayList <String> userTable = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);
        mListView = (ListView) findViewById(R.id.listView);
        loadScore();
        loadLeaderBoardTop10();

        buttonClassement = findViewById(R.id.button_classement);
        buttonClassement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizDbHelper accountDB = new QuizDbHelper(getApplicationContext());
                User currentAccount = accountDB.find(account.getId());
                currentAccount.setScore(QuizActivity.scoreFinal);
                Intent intent = new Intent(Classement.this, ClassementAll.class);
                intent.putExtra("account", currentAccount);
                startActivity(intent);
            }
        });
    }


    public void loadScore(){
        Intent intent = getIntent();
        account = (User) intent.getSerializableExtra("account");
        QuizDbHelper db = new QuizDbHelper(this);
        highscore = db.getScoreDB(account.getUsername());
        if(account.getScore()>= highscore) {
            db.update(account);
            highscore = account.getScore();
        }
    }

    private void loadLeaderBoardTop10(){
        QuizDbHelper db = new QuizDbHelper(this);
        List <User> users = db.getUser();
        int count = 1;


        String leaderboard ="";
        for (User user : users){
            if (count == 11){
                break;
            }
           String username = user.getUsername();
           for (int i = username.length(); i <10;i++){
                   username= username+" ";
           }


           if (count <=9){
                leaderboard = "  "+ count + "                                 "
                       + username +"              " + ((user.getScore()*2)+60) ;
                count++;
           }
           else{
               leaderboard = count + "                                    "
                       + username +"                                   " + ((user.getScore()*2)+60);
               count++;
           }
           userTable.add(leaderboard);
        }

       ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,userTable);
       mListView.setAdapter(adapter);

    }
}
