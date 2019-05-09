package com.lsinf1225.iqwhizz.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lsinf1225.iqwhizz.Category;
import com.lsinf1225.iqwhizz.User;
import com.lsinf1225.iqwhizz.Question;
import com.lsinf1225.iqwhizz.Database.QuizContract.*;
import com.lsinf1225.iqwhizz.User;

import java.lang.annotation.AnnotationTypeMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Object;
import java.lang.String;
import java.util.Arrays;
import java.lang.Class;



public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "IQWhizz-J1B45.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;


    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context){
        if (instance == null){
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    //public ArrayList<Integer> Amis = User.getAmis();

    //endroit ou la database est créée
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME +" TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_QUESTIONSET + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " +
                UserTable.TABLE_NAME + " ( " +
                UserTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UserTable.COLUMN_USERNAME + " TEXT, " +
                UserTable.COLUMN_MAIL + " TEXT, " +
                UserTable.COLUMN_AGE + " TEXT, " +
                UserTable.COLUMN_PASSWORD + " TEXT, " +
                UserTable.COLUMN_SCORE  + " INTEGER " +
                ")";


        final String SQL_CREATE_FRIENDS_TABLE = "CREATE TABLE " +
                FriendsTable.TABLE_NAME + " ( " +
                FriendsTable.FRIENDS_LOGIN1 + " TEXT, " +
                FriendsTable.FRIENDS_LOGIN2 + " TEXT " +
                ")";


        db.execSQL(SQL_CREATE_FRIENDS_TABLE);
        db.execSQL(SQL_CREATE_USER_TABLE);
        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        fillCategoriesTable();
        fillQuestionsTable();
    }

    //permet de refaire une nouvelle version de la base de donnée ce qui évite de devoir supprimé
    //l'application à chaque fois
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FriendsTable.TABLE_NAME);
        onCreate(db);
    }

    //Permet d'activer les Foreign key dans la base de données
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }


    //Remplis manuellement la table de catégories en faisant appel à insertCategory
    private void fillCategoriesTable(){
        Category c1 = new Category("Programming");
        insertCategory(c1);
        Category c2 = new Category("Geography");
        insertCategory(c2);
        Category c3 = new Category("Math");
        insertCategory(c3);
    }

    private void fillFriendsTable(){
        for(int i = 0;i<User.getAmis().length;i++)
        {
            i++;
        }
    }

    public void addFriends(User user){
        db = getWritableDatabase();
        insertFriend(user);
    }

    private void insertFriend(User user)
    {
        ContentValues cv = new ContentValues();
        cv.put(FriendsTable.FRIENDS_LOGIN2,user.getLogin());
        //cv.put(FriendsTable.FRIENDS_LOGIN1,this.user.getLogin())
        //cv.put(FriendsTable.COLUMN_ID,user.getIdask());
        db.insert(FriendsTable.TABLE_NAME,null,cv);

    }

    //ajoute directement la catégorie à la table en faisant appel à insertCategory
    public void addCategory (Category category){
        db = getWritableDatabase();
        insertCategory(category);
    }

    //Permet d'ajouter une liste de catégories à la table en faisant appel à insertCategory
    public void addCategories (List<Category> categories){
        db = getWritableDatabase();

        for (Category category: categories){
            insertCategory(category);
        }
    }

    // cette methode est celle qui ajoute vraiment la categorie en argument dans la base de donnée
    private void insertCategory(Category category){
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME,null,cv);

    }

    //Remplis manuellement la table "Question" en faisant appel à insertQuestion
    private void fillQuestionsTable() {
        Question q1 = new Question("Programming - QS 1: A is correct",
                "A", "B", "C","D", 1, Question.QUESTION_SET_1,Category.PROGRAMMING);
        insertQuestion(q1);
        Question q2 = new Question("Programming - QS 1: B is correct",
                "A", "B", "C","D", 2, Question.QUESTION_SET_1,Category.PROGRAMMING);
        insertQuestion(q2);
        Question q3 = new Question("Programming - QS 1: C is correct",
                "A", "B", "C","D", 3, Question.QUESTION_SET_1, Category.PROGRAMMING);
        insertQuestion(q3);
        Question q4 = new Question("Programming - QS 1: A is correct again",
                "A", "B", "C","D", 1, Question.QUESTION_SET_1, Category.PROGRAMMING);
        insertQuestion(q4);
        Question q5 = new Question("Programming - QS 1: D is correct",
                "A", "B", "C","D", 4, Question.QUESTION_SET_1, Category.PROGRAMMING);
        insertQuestion(q5);
        Question q6 = new Question("Programming - QS 2: B is correct again",
                "A", "B", "C","D", 2, Question.QUESTION_SET_2, Category.PROGRAMMING);
        insertQuestion(q6);

    }

    //ajoute directement l'objet question à la table en faisant appel à insertQuestion
    public void addQuestion (Question question){
        db = getWritableDatabase();
        insertQuestion(question);
    }

    //ajoute directement la liste d'objet question à la table en faisant appel à insertQuestion
    public void addQuestions (List<Question> questions){
        db = getWritableDatabase();

        for (Question question : questions) {
            insertQuestion(question);
        }
    }

    //Methode qui permet l'ajout de l'objet question dans la base de donnée
    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_QUESTIONSET, question.getQuestionSet());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID,question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    //Methode qui permet de renvoyer une liste de toute les catégories présente dans la table
    public List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME,null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);

            }while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }

    public ArrayList<User> getAllFriends(){
        ArrayList<User> Friends = new ArrayList<>();
        User user = new User();
        db = getReadableDatabase();
        //Cursor c = db.query("SELECT user1,user2 FROM " + FriendsTable.TABLE_NAME + " WHERE FriendsTable.FRIENDS_LOGIN1=user.getLogin() OR FriendsTable.FRIENDS_LOGIN2=user.getLogin()", null,null,null);
        //String selection = FriendsTable.FRIENDS_LOGIN1 + " = ? " + " and " + FriendsTable.FRIENDS_LOGIN2 + " = ? ";
        //String[] selectionArgs = new String[]{,String.valueOf(categoryID)};

        Cursor c = db.query(
                FriendsTable.TABLE_NAME,
                null,
                 null,
                null,
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            do {
                //user.setFriend(c.getString(c.getColumnIndex(FriendsTable.FRIENDS)));
                if(FriendsTable.FRIENDS_LOGIN1==user.getLogin()){
                    user.setFriend(FriendsTable.FRIENDS_LOGIN1);
                    Friends.add(user);
                }
                else {
                    user.setFriend(FriendsTable.FRIENDS_LOGIN2);
                    Friends.add(user);
                }
            }while (c.moveToNext());
        }
        c.close();
        return Friends;
    }



    //Methode qui permet de renvoyer une liste de tout les objets question présente dans la table
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setQuestionSet(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTIONSET)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    //permet d'avoir la question + sa catégorie
    public ArrayList<Question> getQuestions(String questionSet, int categoryID) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_QUESTIONSET + " = ? " + " and " + QuestionsTable.COLUMN_CATEGORY_ID + " = ? ";
        String[] selectionArgs = new String[]{questionSet,String.valueOf(categoryID)};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setQuestionSet(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTIONSET)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }


    public boolean create(User account){
        boolean result = true;
        try{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(UserTable.COLUMN_USERNAME,account.getUsername());
            contentValues.put(UserTable.COLUMN_PASSWORD,account.getMdp());
            contentValues.put(UserTable.COLUMN_AGE,account.getAge());
            contentValues.put(UserTable.COLUMN_MAIL,account.getMail());
            contentValues.put(UserTable.COLUMN_SCORE,0);

            result = db.insert(UserTable.TABLE_NAME,null,contentValues) > 0;

        }catch(Exception e){
            result = false;
        }
        return result;
    }

    public boolean update(User account){
        boolean result = true;
        try{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(UserTable.COLUMN_USERNAME,account.getUsername());
            contentValues.put(UserTable.COLUMN_MAIL,account.getMail());
            contentValues.put(UserTable.COLUMN_AGE,account.getAge());
            contentValues.put(UserTable.COLUMN_PASSWORD,account.getMdp());
            contentValues.put(UserTable.COLUMN_SCORE,account.getScore());

            result = db.update(UserTable.TABLE_NAME,contentValues, UserTable.COLUMN_ID +" = ?",new String[] {String.valueOf(account.getId())}) > 0;

        }catch(Exception e){
            result = false;
        }
        return result;
    }

    public User login(String username, String password){
        User account = null;
        try{
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + UserTable.TABLE_NAME + " where username = ? and password = ?", new String[] {username,password});

            if (cursor.moveToFirst()){
                account = new User();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setMail(cursor.getString(2));
                account.setAge(cursor.getString(3));
                account.setMdp(cursor.getString(4));
                account.setScore(cursor.getInt(5));

            }
        }catch (Exception e){
            account = null;
        }

        return account;
    }

    public User find( int id){
        User account = null;
        try{
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + UserTable.TABLE_NAME + " where id = ?", new String[] {String.valueOf(id)});

            if (cursor.moveToFirst()){
                account = new User();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setMail(cursor.getString(2));
                account.setAge(cursor.getString(3));
                account.setMdp(cursor.getString(4));
                account.setScore(cursor.getInt(5));

            }
        }catch (Exception e){
            account = null;
        }

        return account;
    }


    public User checkUsername(String username){
        User account = null;
        try{
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + UserTable.TABLE_NAME + " where username = ?", new String[] {username});

            if (cursor.moveToFirst()){
                account = new User();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setMail(cursor.getString(2));
                account.setAge(cursor.getString(3));
                account.setMdp(cursor.getString(4));
                account.setScore(cursor.getInt(5));

            }
        }catch (Exception e){
            account = null;
        }

        return account;
    }

    public int getScoreDB(String username){
        int score = 0 ;
        try{
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("select score from " + UserTable.TABLE_NAME + " where username = ?", new String[] {username});

            if (cursor.moveToFirst()) {
                do {
                    score = cursor.getInt(cursor.getColumnIndex("score"));
                } while (cursor.moveToNext());
            }
            cursor.close();
            return score;

        }catch (Exception e){
            return -1;
        }
    }

    public List<User> getUser(){
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor User = db.query(UserTable.TABLE_NAME,
                new String[] { UserTable.COLUMN_USERNAME, UserTable.COLUMN_SCORE},
                null,
                null,
                null,
                null,
                UserTable.COLUMN_SCORE+" DESC");
        if (User.moveToFirst()) {
            do {
                User user = new User();
                user.setUsername(User.getString(User.getColumnIndex(UserTable.COLUMN_USERNAME)));
                user.setScore(User.getInt(User.getColumnIndex(UserTable.COLUMN_SCORE)));
                userList.add(user);
            } while (User.moveToNext());
        }
        return userList;
    }
}