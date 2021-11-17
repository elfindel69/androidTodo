package com.hb.androidtodo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hb.androidtodo.dao.TodoDAO;
import com.hb.androidtodo.pojos.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private static final String KEY_TODO = "todo";
    private TextView tvTodo;
    private String todoString = "";
    private List<Todo> todos = new ArrayList<>();
    private Context context;
    private TodoDAO todoDAO;
    private TodoAsyncTasks todoAsyncTasks;

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
        todoAsyncTasks = new TodoAsyncTasks();
        todoAsyncTasks.execute();
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
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showData(){
        todos = todoDAO.list();
        todoString = "";
        tvTodo.setText("");
        for (Todo todo : todos){
            todoString =todo.getName()+" // "+todo.getUrgency()+"\n";
            tvTodo.append(todoString);
        }
    }

    public class TodoAsyncTasks extends AsyncTask<Nullable, Nullable, List<Todo>> implements com.hb.androidtodo.TodoAsyncTasks {

        @Override
        protected List<Todo> doInBackground(Nullable... nullables) {

            return todoDAO.list();
        }


        @Override
        protected void onPostExecute(List<Todo> todos){
            showData();
        }


    }
}