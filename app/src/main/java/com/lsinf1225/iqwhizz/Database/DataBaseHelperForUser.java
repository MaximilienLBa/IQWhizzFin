package com.lsinf1225.iqwhizz.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lsinf1225.iqwhizz.User;
import com.lsinf1225.iqwhizz.Database.QuizContract.*;

import java.util.concurrent.ExecutionException;

public class DataBaseHelperForUser extends SQLiteOpenHelper {
    private static  String dbName = "accountDB" ;
    public static  String TABLE_NAME ="registeruser";
    public static  String COLUMN_ID ="ID";
    public static  String COLUMN_USERNAME ="username";
    public static  String COLUMN_PASSWORD ="password";
    public static  String COLUMN_AGE = "age";
    public static  String COLUMN_MAIL = "email";

    public DataBaseHelperForUser(Context context) {
        super(context, dbName ,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, email TEXT, age TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean create(User account){
        boolean result = true;
        try{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_USERNAME,account.getUsername());
            contentValues.put(COLUMN_PASSWORD,account.getMdp());
            contentValues.put(COLUMN_AGE,account.getAge());
            contentValues.put(COLUMN_MAIL,account.getMail());

            result = db.insert(TABLE_NAME,null,contentValues) > 0;

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
            contentValues.put(COLUMN_USERNAME,account.getUsername());
            contentValues.put(COLUMN_MAIL,account.getMail());
            contentValues.put(COLUMN_AGE,account.getAge());
            contentValues.put(COLUMN_PASSWORD,account.getMdp());

            result = db.update(TABLE_NAME,contentValues, COLUMN_ID +" = ?",new String[] {String.valueOf(account.getId())}) > 0;

        }catch(Exception e){
            result = false;
        }
        return result;
    }

    public User login(String username, String password){
        User account = null;
        try{
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where username = ? and password = ?", new String[] {username,password});

            if (cursor.moveToFirst()){
                account = new User();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setMail(cursor.getString(2));
                account.setAge(cursor.getString(3));
                account.setMdp(cursor.getString(4));

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
            Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where id = ?", new String[] {String.valueOf(id)});

            if (cursor.moveToFirst()){
                account = new User();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setMail(cursor.getString(2));
                account.setAge(cursor.getString(3));
                account.setMdp(cursor.getString(4));

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
            Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where username = ?", new String[] {username});

            if (cursor.moveToFirst()){
                account = new User();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setMail(cursor.getString(2));
                account.setAge(cursor.getString(3));
                account.setMdp(cursor.getString(4));

            }
        }catch (Exception e){
            account = null;
        }

        return account;
    }
}
