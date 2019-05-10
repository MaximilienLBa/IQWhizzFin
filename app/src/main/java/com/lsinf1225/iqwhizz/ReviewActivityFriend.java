package com.lsinf1225.iqwhizz;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReviewActivityFriend extends AppCompatActivity {

    private TextView QI,QI2;
    //private ImageView image;
    private  int  score1,score2;
    private int qi1,qi2;
    private TextView mGagnant;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_friend);

        //lie les textView xml à java
        QI=findViewById(R.id.user1_qi);
        QI2=findViewById(R.id.user2_qi);
        mGagnant=findViewById(R.id.user_gagnant);


        //lie les boutons xml à java pour pouvoir leurs donnés des actions
        Button rb = findViewById(R.id.Review2_next_button);
        //Button Qreview = findViewById(R.id.Review_question_button);
        score1=DuelActivity.scoreUser1;
        score2=DuelActivity.scoreUser2;


        if (score1 > score2){
            mGagnant.setText("Vous êtes clairement plus intelligent que votre ami");
        }
        else if (score2 > score1){
            mGagnant.setText("Votre ami à surement du avoir de la chance");
        }else{
            mGagnant.setText("Votre intelligence est équivalente, bravo !");
        }


        //calcul le qi est l'affiche
        setqi1();
        setqi2();
        QI.setText(Integer.toString(qi1));
        QI2.setText(Integer.toString(qi2));

        //affiche l'une des 3 images en fonction du qi
        //setImage();

        //bouton qui termine la review et reset les tableaux d'infos sur questions et réponses et le score
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                QuizActivity.Rtab.clear();
                QuizActivity.R_user_tab.clear();
                QuizActivity.Qtab.clear();

                score1=0;
                score2=0;
                finish();
            }

        });

    }

    //Calcule le QI de l'utisateur sur ce questionnaire
    private void setqi1() {
        qi1 = (score1*2) +60;
    }
    private void setqi2() {
        qi2 = (score2*2) +60;
    }
    //affiche l'image en fct du qi

}