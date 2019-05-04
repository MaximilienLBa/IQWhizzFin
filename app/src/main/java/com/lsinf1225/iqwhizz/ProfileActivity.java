package com.lsinf1225.iqwhizz;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lsinf1225.iqwhizz.Database.DataBaseHelperForUser;
public class ProfileActivity extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword, editTextAge,editTextEmail;
    private Button buttonSave, buttonCancel;
    private User account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        editTextUsername = findViewById(R.id.edittext_username);
        editTextPassword = findViewById(R.id.edittext_password);
        editTextAge = findViewById(R.id.edittext_age);
        editTextEmail = findViewById(R.id.edittext_email);

        loadData();

        buttonSave = findViewById(R.id.save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    DataBaseHelperForUser accountDB = new DataBaseHelperForUser(getApplicationContext());
                    User currentAccount = accountDB.find(account.getId());
                    String NewUsername = editTextUsername.getText().toString();
                    User temp = accountDB.checkUsername(NewUsername);
                    if (!NewUsername.equalsIgnoreCase(currentAccount.getUsername())&& temp != null ){
                        Toast.makeText(ProfileActivity.this, "Change profile failed", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    currentAccount.setUsername(editTextUsername.getText().toString());
                    currentAccount.setAge(editTextAge.getText().toString());
                    currentAccount.setMail(editTextEmail.getText().toString());

                    String password = editTextPassword.getText().toString();
                    if(!password.isEmpty()){
                        currentAccount.setMdp(editTextPassword.getText().toString());
                    }
                    if(accountDB.update(currentAccount)){
                        Intent intent = new Intent(ProfileActivity.this, StartingScreenActivity.class);
                        intent.putExtra("account",currentAccount);
                        startActivity(intent);
                    }else{
                        Toast.makeText(ProfileActivity.this, "Change profile failed", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(ProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });


        buttonCancel = findViewById(R.id.cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, StartingScreenActivity.class);
                intent.putExtra("account",account);
                startActivity(intent);
            }
        });

    }

    private void loadData(){
        Intent intent = getIntent();
        account = (User) intent.getSerializableExtra("account");
        editTextEmail.setText(account.getMail());
        editTextAge.setText(account.getAge());
        editTextUsername.setText(account.getUsername());
        editTextPassword.setText(account.getMdp());

    }
}