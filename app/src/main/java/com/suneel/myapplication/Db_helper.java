package com.suneel.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Db_helper extends SQLiteOpenHelper {
    private static final String DB_NAME = "register";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "registration";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "Name";
    private static final String EMAIL_COL = "Email";
    private static final String MOBILE_COL = "number";
    private static final String PASSWORD_COL ="password";
    private static final String CONFIRMPASSWORD_COL ="cnfpassword";
    private static final String ADDRESS_COL = "address";

    public Db_helper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="CREATE TABLE "+ TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + MOBILE_COL + " TEXT,"
                + PASSWORD_COL +" TEXT,"
                + CONFIRMPASSWORD_COL +" TEXT,"
                + ADDRESS_COL +" TEXT)";
        db.execSQL(query);
    }
    public Boolean addRegister(String name,String email, String number, String password1, String cnfpassword, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(NAME_COL, name);
        values.put(EMAIL_COL, email);
        values.put(MOBILE_COL,number);
        values.put(PASSWORD_COL, password1);
        values.put(CONFIRMPASSWORD_COL, cnfpassword);
        values.put(ADDRESS_COL,address);
       Long result = db.insert(TABLE_NAME,null,values);
       if(result==-1) return false;
       else
           return true;

    }
    public Boolean checkemailpassword(String email, String password1){
        SQLiteDatabase db = this.getWritableDatabase() ;
        Cursor cursor = db.rawQuery("Select * from registration where email = ? and password1 = ?", new String[] {email,password1});
        if(cursor.getCount()>0)
            return true;

        else
            return false;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
