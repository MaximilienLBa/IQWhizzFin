package com.lsinf1225.iqwhizz.Database;

import android.provider.BaseColumns;

public final class QuizContract {

    //constructeur vide private pour pas pouvoir faire un objet QuizContract
    private QuizContract() {
    }

    //différente colonne de la table des catégories
    public static class CategoriesTable implements BaseColumns{
        public static final String TABLE_NAME = "quiz_categories";
        public static final String COLUMN_NAME = "name";
    }

    //différente colonne de la table des questions et réponses
    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
        public static final String COLUMN_QUESTIONSET = "questionset";
        public static final String COLUMN_CATEGORY_ID = "category_id";

    }

    public static class UserTable implements BaseColumns{
        public static  String TABLE_NAME ="registeruser";
        public static  String COLUMN_ID ="ID";
        public static  String COLUMN_USERNAME ="username";
        public static  String COLUMN_PASSWORD ="password";
        public static  String COLUMN_AGE = "age";
        public static  String COLUMN_MAIL = "email";
        public static  String COLUMN_SCORE = "score";
    }

    public static class FriendsTable implements BaseColumns{
        public static String TABLE_NAME = "friends";
        public static String FRIENDS_LOGIN = "friendslogin";
        public static String FRIENDS = "friends";
    }
}

