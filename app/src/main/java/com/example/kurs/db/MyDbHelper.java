package com.example.kurs.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper
{
    public MyDbHelper(@Nullable Context context)
    {
        super(context, MyConstants.DB_NAME, null, MyConstants.DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(MyConstants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer)
    {
        db.execSQL(MyConstants.DROP_TABLE);
        onCreate(db);
    }
}
