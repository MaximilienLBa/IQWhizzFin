package com.lsinf1225.iqwhizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lsinf1225.iqwhizz.Database.QuizDbHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;
    private TextView mTextViewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.edittext_username);
        editTextPassword = findViewById(R.id.edittext_password);
        buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizDbHelper accountDB = new QuizDbHelper(getApplicationContext());
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                User account = accountDB.login(username, password);
                if (account == null){
                    Toast.makeText(LoginActivity.this, "Invalid account", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(LoginActivity.this, StartingScreenActivity.class);
                    intent.putExtra("account",account);
                    startActivity(intent);
                }
            }
        });
        mTextViewRegister = (TextView)findViewById(R.id.textview_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

    }
}