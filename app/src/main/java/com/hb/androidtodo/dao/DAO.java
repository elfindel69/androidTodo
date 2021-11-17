package com.hb.androidtodo.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO {
    protected SQLiteDatabase db = null;
    private SQLiteOpenHelper baseHepler = null;

    public DAO(SQLiteOpenHelper baseHepler){
        this.baseHepler = baseHepler;
    }

    public SQLiteDatabase open() {
        db = baseHepler.getWritableDatabase();

        return db;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDB(){
        return db;
    }
}
