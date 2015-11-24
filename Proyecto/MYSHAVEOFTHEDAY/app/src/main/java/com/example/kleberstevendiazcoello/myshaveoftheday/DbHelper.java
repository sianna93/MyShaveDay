package com.example.kleberstevendiazcoello.myshaveoftheday;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kleberstevendiazcoello on 14/9/15.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "shave.sqlite";
    private static final int DB_SCHEME_VERSION = 1 ;

    public DbHelper(Context context) {
        super(context,DB_NAME,null, DB_SCHEME_VERSION);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {


        db.execSQL(DatabaseManager.CREATE_TABLE_TYPE_CATEGORY);
        db.execSQL(DatabaseManager.CREATE_TABLE_Shave_Day);
        db.execSQL(DatabaseManager.CREATE_TABLE_Kit_Articulos);
        db.execSQL(DatabaseManager.CREATE_TABLE);
        db.execSQL(DatabaseManager.CREATE_TABLE_Category);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
