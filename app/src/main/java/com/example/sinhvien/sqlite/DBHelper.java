package com.example.sinhvien.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_Name ="Demo6";
    public static final int DB_VERSION = 2;

    public DBHelper(@Nullable Context context) {
        super(context, DB_Name, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE sinhvien(id text primary key, name text not null," + " salary rel not null)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql ="DROP TABLE IF EXISTS sinhvien";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
