package com.hb.androidtodo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hb.androidtodo.adapters.TodoAdapter;
import com.hb.androidtodo.dao.TodoDAO;
import com.hb.androidtodo.pojos.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private static final String KEY_TODO = "todo";
    private RecyclerView rvTodo;
    private String todoString = "";
    private List<Todo> todos = new ArrayList<>();
    private Context context;
    private TodoDAO todoDAO;
    private TodoAsyncTasks todoAsyncTasks;
    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_main);
        context = getApplicationContext();

        todoDAO = new TodoDAO(context);

        rvTodo = findViewById(R.id.rvTodo);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvTodo.setHasFixedSize(true);
        rvTodo.setLayoutManager(layoutManager);

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




    public class TodoAsyncTasks extends AsyncTask<Nullable, Nullable, List<Todo>> implements com.hb.androidtodo.TodoAsyncTasks {

        @Override
        protected List<Todo> doInBackground(Nullable... nullables) {

            return todoDAO.list();
        }


        @Override
        protected void onPostExecute(List<Todo> todos){
           todoAdapter = new TodoAdapter(todos);
           rvTodo.setAdapter(todoAdapter);
        }


    }
}