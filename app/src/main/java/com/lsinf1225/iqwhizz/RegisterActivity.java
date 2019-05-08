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

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword, editTextAge,editTextEmail,editTextPasswordCnf;
    private Button buttonSave;
    private TextView mTextViewLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsername = findViewById(R.id.edittext_username);
        editTextPassword = findViewById(R.id.edittext_password);
        editTextAge = findViewById(R.id.edittext_age);
        editTextEmail = findViewById(R.id.edittext_email);
        editTextPasswordCnf = findViewById(R.id.edittext_cnf_password);
        buttonSave = findViewById(R.id.button_register);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    QuizDbHelper accountDB = new QuizDbHelper(getApplicationContext());
                    User account = new User();
                    String user = editTextUsername.getText().toString().trim();
                    String age = editTextAge.getText().toString().trim();
                    String mail = editTextEmail.getText().toString().trim();
                    String pwd = editTextPassword.getText().toString().trim();
                    String cnf_pwd = editTextPasswordCnf.getText().toString().trim();
                    if (user.equals("") || pwd.equals("")|| age.equals("") || mail.equals("")) {
                        Toast.makeText(RegisterActivity.this, "Registeration Error - field empty", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (pwd.equals(cnf_pwd)){
                            if(mail.contains("@") == false && mail.contains(".") == false){
                                Toast.makeText(RegisterActivity.this, "Wrong email", Toast.LENGTH_SHORT).show();
                            }else{
                                User temp = accountDB.checkUsername(editTextUsername.getText().toString());
                                if(temp == null) {
                                    account.setMail(editTextEmail.getText().toString());
                                    account.setAge(editTextAge.getText().toString());
                                    account.setUsername(editTextUsername.getText().toString());
                                    account.setMdp(editTextPassword.getText().toString());
                                    account.setScore(0);
                                    if (accountDB.create(account)) {
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Cannot create", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(RegisterActivity.this, "Username Exist", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mTextViewLogin = (TextView) findViewById(R.id.textview_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(LoginIntent);
            }
        });





    }
}

