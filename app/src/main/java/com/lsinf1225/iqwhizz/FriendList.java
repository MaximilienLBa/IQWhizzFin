package com.lsinf1225.iqwhizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.lsinf1225.iqwhizz.Database.QuizDbHelper;

import java.util.ArrayList;

public class FriendList extends AppCompatActivity {
    private ListView mListView;
    ArrayList<String> friendsTable = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        mListView = findViewById(R.id.listView_friendList);

        loadFriends();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent duelScreen = new Intent(FriendList.this,DuelActivity.class);
                startActivity(duelScreen);
            }
        });
    }

    private void loadFriends(){
        QuizDbHelper db = new QuizDbHelper(this);
        ArrayList<String> friendList = db.getAllFriends();
        for (String friends : friendList){
            friendsTable.add(friends);
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,friendsTable);
        mListView.setAdapter(adapter);
    }
}
