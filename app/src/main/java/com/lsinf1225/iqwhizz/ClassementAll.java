package com.lsinf1225.iqwhizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.lsinf1225.iqwhizz.Database.QuizDbHelper;
import com.lsinf1225.iqwhizz.R;
import com.lsinf1225.iqwhizz.User;

import java.util.ArrayList;
import java.util.List;

public class ClassementAll extends AppCompatActivity {

    private User account;
    private ListView mListView2;
    private static int highscore;

    ArrayList<String> userTable = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement_all);

        mListView2 = findViewById(R.id.listView2);
        loadScore();
        loadClassementAll();
    }

    private void loadClassementAll() {
        QuizDbHelper db = new QuizDbHelper(this);
        List<User> users = db.getUser();
        int count = 1;
        for (User user : users){
            String username = user.getUsername();
            for (int i = username.length(); i <10;i++){
                username= username+" ";
            }
            String leaderboard = count + "                               "
                    + username +"                " + user.getScore() ;
            count++;
            userTable.add(leaderboard);
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,userTable);
        mListView2.setAdapter(adapter);

    }

    private void loadScore() {
        Intent intent = getIntent();
        account = (User) intent.getSerializableExtra("account");
        QuizDbHelper db = new QuizDbHelper(this);
        if(account.getScore()>= highscore) {
            db.update(account);
            highscore = account.getScore();
        }
    }
}
