package com.lsinf1225.iqwhizz;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    public static final String QUESTION_SET_1 = "Question Set 1";
    public static final String QUESTION_SET_2 = "Question Set 2";
    public static final String QUESTION_SET_3 = "Question Set 3";

    //Initialisation des variables réponses, la question, la catégorie et son id ainsi que le numéro
    //correspondant à la bonne réponse
    private String question;
    public static String option1;
    public static String option2;
    public static String option3;
    public static String option4;
    private static int answerNr;
    private String questionSet;
    private int categoryID;
    private int id;


    //constructeur vide
    public Question() {
    }

    //Constructeur d'une question
    public Question(String question, String option1, String option2, String option3,String option4, int answerNr,String questionSet ,int categoryID) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerNr = answerNr;
        this.questionSet = questionSet;
        this.categoryID = categoryID;
    }

    protected Question(Parcel in) {
        id = in.readInt();
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        answerNr = in.readInt();
        questionSet = in.readString();
        categoryID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeString(questionSet);
        dest.writeInt(answerNr);
        dest.writeInt(categoryID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    // Bloc entier de getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(String questionSet) {
        this.questionSet = questionSet;
    }
    public static String[] getAllQuestionSet(){
        return new String[] {QUESTION_SET_1, QUESTION_SET_2, QUESTION_SET_3 };
    }
    public  String getAnswer(){
        if (this.answerNr==1){ return this.option1;}
        if (this.answerNr==2){ return this.option2;}
        if (this.answerNr==3){ return this.option3;}
        if (this.answerNr==4){ return this.option4;}
        return "error";
    }

    public  String getOption(int i){
        if (i==1){ return this.option1;}
        if (i==2){ return this.option2;}
        if (i==3){ return this.option3;}
        if (i==4){ return this.option4;}
        return "error";
    }
}

