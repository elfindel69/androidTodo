package com.hb.androidtodo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoDBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "todo.db";

    public static final String TODO_KEY = "id";
    public static final String TODO_NAME = "name";
    public static final String TODO_URGENCY = "urgency";

    public static final String TODO_TABLE_NAME = "Todo";

    public static final int TODO_KEY_COLUMN_INDEX = 0;
    public static final int TODO_NAME_COLUMN_INDEX = 1;
    public static final int TODO_URGENCY_COLUMN_INDEX = 2;

    private static final String TODO_TABLE_CREATE =
            "CREATE TABLE " + TODO_TABLE_NAME + " ("+
                    TODO_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    TODO_NAME + " TEXT, "+
                    TODO_URGENCY + " TEXT);";

    private static final String TODO_TABLE_DROP =
            "DROP TABLE IF EXISTS "+ TODO_TABLE_NAME + ";";

    public TodoDBHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TODO_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TODO_TABLE_DROP);
        onCreate(db);
    }
}
