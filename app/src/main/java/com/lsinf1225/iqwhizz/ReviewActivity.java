package com.lsinf1225.iqwhizz;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lsinf1225.iqwhizz.Database.QuizDbHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

    public class ReviewActivity extends AppCompatActivity {
        private TextView GoodAnswerNumber;
        private TextView BadAnswerNumber;
        private RadioButton rb;
        private TextView QI;
        private ImageView image;
        private User user;
        private  int  score;
        private int time;
        private int questionnumber;
        private int qi;
        private int age;


        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_review);

//lie les textView xml à java
            //GoodAnswerNumber = findViewById(R.id.text_view_good);
            // BadAnswerNumber =  findViewById(R.id.text_view_bad);
            QI=findViewById(R.id.user_qi);
            image=findViewById(R.id.review_image);
            //lie les boutons xml à java pour pouvoir leurs donnés des actions
            Button rb = findViewById(R.id.Review_next_button);


            time=QuizActivity.getTotaltime();
            score=QuizActivity.getScore();
            questionnumber=QuizActivity.getQuestionCountTotal();
            age=Integer.parseInt(StartingScreenActivity.account.getAge());

            //image.setImageResource(R.drawable.einstein);


            setqi();
            QI.setText(Integer.toString(qi));

            setImage();
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    score=0;
                    finish();
                }

            });





        }




        //Calcule le QI de l'utisateur sur ce questionnaire, manque l'age
        private void setqi() {
            int l = (score/questionnumber)*100;
            qi=l;
            if(score>questionnumber/2){qi=125;}
            if(l<70){qi=70;}

        }

        private void setImage(){
            if(qi>120){ image.setImageResource(R.drawable.einstein); }
            if(qi<90){ image.setImageResource(R.drawable.fluffy);}
            if(qi>=90 && qi<=120){ image.setImageResource(R.drawable.charlie);}

        }






    }


