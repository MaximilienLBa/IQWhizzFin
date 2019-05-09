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


public class FriendActivity extends AppCompatActivity {

    private User account;
    private ListView mListView;
    ArrayList<String> friendsTable = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_activity);
        mListView = (ListView) findViewById(R.id.listView_friend);
        loadFriends();

    }

    private void loadFriends(){
        QuizDbHelper db = new QuizDbHelper(this);
        ArrayList<User> friends = db.getAllFriends();
        String friend = "";
        int count = 1;
        for (User user : friends){
            String username = user.getUsername();

            friend = count + "  "
                    + username;
            count++;
            friendsTable.add(friend);

        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,friendsTable);
        mListView.setAdapter(adapter);
    }

}
