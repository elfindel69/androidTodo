package com.hb.androidtodo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import com.hb.androidtodo.db.TodoDBHelper;
import com.hb.androidtodo.pojos.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoDAO extends DAO{

    public TodoDAO(Context context) {
        super(new TodoDBHelper(context));
    }

    public Todo find(Long id){
        Todo todo = null;

        open();

        Cursor cursor = db.rawQuery("select * from " +  TodoDBHelper.TODO_TABLE_NAME  +
                " where "+ TodoDBHelper.TODO_KEY + " = ?",new String[] {String.valueOf(id)});


        if(cursor != null && cursor.moveToFirst()){
            todo = new Todo();
            todo.setId(cursor.getLong(TodoDBHelper.TODO_KEY_COLUMN_INDEX));
            todo.setName(cursor.getString(TodoDBHelper.TODO_NAME_COLUMN_INDEX));
            todo.setUrgency(cursor.getString(TodoDBHelper.TODO_URGENCY_COLUMN_INDEX));

            cursor.close();
        }

        close();

        return todo;

    }

    public List<Todo> list(){
        List<Todo> todos = new ArrayList<>();

        open();

        Cursor cursor = db.rawQuery("select * from "+TodoDBHelper.TODO_TABLE_NAME,null);

        if(cursor!=null && cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                Todo todo = new Todo();
                todo.setId(cursor.getLong(TodoDBHelper.TODO_KEY_COLUMN_INDEX));
                todo.setName(cursor.getString(TodoDBHelper.TODO_NAME_COLUMN_INDEX));
                todo.setUrgency(cursor.getString(TodoDBHelper.TODO_URGENCY_COLUMN_INDEX));

                todos.add(todo);

                cursor.moveToNext();
            }
            cursor.close();
        }
        close();

        return todos;
    }

    public void add(Todo todo){

        open();

        ContentValues values = new ContentValues();

        values.put(TodoDBHelper.TODO_NAME,todo.getName());
        values.put(TodoDBHelper.TODO_URGENCY,todo.getUrgency());

        long id = db.insert(TodoDBHelper.TODO_TABLE_NAME ,null,values);
        todo.setId(id);

        close();
    }

    public void update(Todo todo){
        open();

        ContentValues values = new ContentValues();

        values.put(TodoDBHelper.TODO_NAME,todo.getName());
        values.put(TodoDBHelper.TODO_URGENCY,todo.getUrgency());

       db.update(TodoDBHelper.TODO_TABLE_NAME,values,TodoDBHelper.TODO_KEY+ " = ?",
                new String[]{String.valueOf(todo.getId())});


        close();
    }

    public void delete(Todo todo){
        open();

        db.delete(TodoDBHelper.TODO_TABLE_NAME,TodoDBHelper.TODO_KEY+ " = ?",
                new String[]{String.valueOf(todo.getId())});

        close();
    }


}
