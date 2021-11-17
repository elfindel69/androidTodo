package com.hb.androidtodo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hb.androidtodo.dao.TodoDAO;
import com.hb.androidtodo.pojos.Todo;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String KEY_TODO = "todo";
    private TextView tvTodo;
    private String todoString = "";
    private List<Todo> todos = new ArrayList<>();
    private Context context;
    private TodoDAO todoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        todoDAO = new TodoDAO(context);

        tvTodo = findViewById(R.id.tvTodo);


    }

    @Override
    protected void onStart() {
        super.onStart();
        getTodosFromDAO();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addToDo) {
            Intent intent = new Intent(context, AddTodoActivity.class);
            startActivityForResult(intent,2);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void getTodosFromDAO(){
        todos = todoDAO.list();
        todoString = "";
        for (Todo todo : todos){
            todoString +=todo.getName()+" // "+todo.getUrgency()+"\n";

        }
        tvTodo.setText(todoString);
    }
}