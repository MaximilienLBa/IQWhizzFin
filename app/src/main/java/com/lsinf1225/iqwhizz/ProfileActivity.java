package com.lsinf1225.iqwhizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    TextView mTextViewLogin;
    TextView mTextViewAge;
    TextView mTextViewMDP;
    TextView mTextViewMail;
    TextView mTextViewProfil;

    EditText mTextAge;
    EditText mTextMDP;
    EditText mTextMail;
    EditText mTextProfil;

    Button mButtonModAge;
    Button mButtonModMDP;
    Button mButtonModMail;
    Button mButtonModProfil;

    ProfileDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_own);

        db = new ProfileDbHelper(this);
        mTextViewLogin = (TextView) findViewById(R.id.textViewLogin);
        mTextViewAge = (TextView) findViewById(R.id.textViewAge);
        mTextViewMDP = (TextView) findViewById(R.id.textViewMDP);
        mTextViewMail =  (TextView) findViewById(R.id.textViewMail);
        mTextViewProfil = (TextView) findViewById(R.id.textViewProf);

        mTextAge = (EditText) findViewById(R.id.editText_corr_age);
        mTextMDP= (EditText) findViewById(R.id.editText_corr_mdp);
        mTextMail = (EditText) findViewById(R.id.editText_corr_mail);
        mTextProfil = (EditText) findViewById(R.id.editText_corr_profile);

        mButtonModAge = (Button) findViewById(R.id.button_mod_age);
        mButtonModMail = (Button) findViewById(R.id.button_mod_mail);
        mButtonModMDP = (Button) findViewById(R.id.button_mod_mdp);
        mButtonModProfil = (Button) findViewById(R.id.button_mod_prof);


        //Affichage des informations du profil de l'utilisateur
        Intent intent = getIntent();
        int loginid = intent.getIntExtra(StartingScreenActivity.EXTRA_CATEGORY_ID,0);
        String loginname = intent.getStringExtra(StartingScreenActivity.EXTRA_CATEGORY_NAME);

        mTextViewLogin.setText(loginname);

        //Modification des informations du profil de l'utilisateur
    }
}
