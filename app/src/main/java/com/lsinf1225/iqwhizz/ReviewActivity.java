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

        private TextView QI;
        private ImageView image;
        private  int  score;
        private int qi;
        private TextView imagename;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_review);

            //lie les textView xml à java
            QI=findViewById(R.id.user_qi);
            image=findViewById(R.id.review_image);
            imagename=findViewById(R.id.image_name);

            //lie les boutons xml à java pour pouvoir leurs donnés des actions
            Button rb = findViewById(R.id.Review_next_button);
            Button Qreview = findViewById(R.id.Review_question_button);
            score=QuizActivity.getScore();

            //calcul le qi est l'affiche
            setqi();
            QI.setText(Integer.toString(qi));

            //affiche l'une des 3 images en fonction du qi
            setImage();

            //bouton qui termine la review et reset les tableaux d'infos sur questions et réponses et le score
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    QuizActivity.Rtab.clear();
                    QuizActivity.R_user_tab.clear();
                    QuizActivity.Qtab.clear();

                    score=0;
                    finish();
                }

            });

            //lance la review des question sur un click
            Qreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent QreviewIntent = new Intent (ReviewActivity.this, QuestionReviewActivity.class);
                    startActivity(QreviewIntent);
                }

            });
        }

        //Calcule le QI de l'utisateur sur ce questionnaire
        private void setqi() {
            qi = (score*2) +60;
        }

        //affiche l'image en fct du qi
        private void setImage(){
            if(qi>120){
                image.setImageResource(R.drawable.einstein);
                imagename.setText("Einstein");
            }
            if(qi<90){
                image.setImageResource(R.drawable.fluffy);
                imagename.setText("Fluffy");
            }
            if(qi>=90 && qi<=120){
                image.setImageResource(R.drawable.charlie);
                imagename.setText("Charlie");
            }

        }
    }


