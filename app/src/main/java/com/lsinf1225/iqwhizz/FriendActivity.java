package com.lsinf1225.iqwhizz;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lsinf1225.iqwhizz.Database.QuizContract;
import com.lsinf1225.iqwhizz.Database.QuizDbHelper;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;


public class FriendActivity extends AppCompatActivity {
    private User account;
    private EditText editTextFriend;
    private static String friendRemake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_activity);
       // mListView = (ListView) findViewById(R.id.listView_friend);
        // loadFriends();
        editTextFriend = findViewById(R.id.editText_friend);
        Button buttonAdd = findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    QuizDbHelper accountDB = new QuizDbHelper(getApplicationContext());
                    String friend = editTextFriend.getText().toString().trim();
                    if (friend.equals("") ) {
                        Toast.makeText(FriendActivity.this, "field empty", Toast.LENGTH_SHORT).show();
                    }
                    else if (alreadyFriend(friend) || alreadyFriendYS(friend)){
                        Toast.makeText(FriendActivity.this, "already friend ", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        User temp = accountDB.checkUsername(friend);
                        if(temp != null) {
                            friendRemake = friend;
                            insertFriend(friend);
                            Toast.makeText(FriendActivity.this, "Succes", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(FriendActivity.this, "Username doesn't exist", Toast.LENGTH_SHORT).show();
                        }

                    }
                }catch (Exception e){
                    Toast.makeText(FriendActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent intent = getIntent();
        account = (User) intent.getSerializableExtra("account");

        Button buttonListFriend = findViewById(R.id.button_friendList);
        buttonListFriend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                QuizDbHelper accountDB = new QuizDbHelper(getApplicationContext());
                User currentAccount = accountDB.find(account.getId());
                Intent intent = new Intent(FriendActivity.this, FriendList.class);
                intent.putExtra("account", currentAccount);
                startActivity(intent);
            }
        });


        Button buttonDelete = findViewById(R.id.button_remove);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizDbHelper accountDB = new QuizDbHelper(getApplicationContext());
                String friend = editTextFriend.getText().toString().trim();
                if (friend.equals("")) {
                    Toast.makeText(FriendActivity.this, "field empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (alreadyFriend(friend)) {
                        accountDB.deleteName(friend);
                        Toast.makeText(FriendActivity.this, "Succes", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(FriendActivity.this, "Username isn't you friend or doesn't exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void insertFriend(String user2)
    {
        QuizDbHelper db = new QuizDbHelper(this);
        Intent intent = getIntent();
        account = (User) intent.getSerializableExtra("account");
        db.createFriend(user2,account.getUsername());
    }

    private boolean alreadyFriend(String user2){
        QuizDbHelper db = new QuizDbHelper(this);
        Intent intent = getIntent();
        account = (User) intent.getSerializableExtra("account");

        return db.checkFriend(account.getUsername(), user2);
    }

    private boolean alreadyFriendYS(String user2){
        QuizDbHelper db = new QuizDbHelper(this);
        Intent intent = getIntent();
        account = (User) intent.getSerializableExtra("account");

        return db.checkFriendYS(account.getUsername(), user2);
    }

}
