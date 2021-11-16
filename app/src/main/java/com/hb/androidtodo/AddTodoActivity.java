package com.hb.androidtodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hb.androidtodo.pojos.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddTodoActivity extends AppCompatActivity {
   public static final String KEY_TODO = "todoObj";

    EditText edtName;
    Spinner spUrgency;
    Button btnOK;
    Button btnCancel;
    Todo todo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        edtName = findViewById(R.id.edtName);
        spUrgency = findViewById(R.id.spUrgency);
        btnOK = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);

        String[] items = new String[]{
                "Low Urgency",
                "Medium Urgency",
                "High Urgency"
        };

        final List<String> itemsList = new ArrayList<>(Arrays.asList(items));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                this,R.layout.spinner_item,itemsList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spUrgency.setAdapter(spinnerArrayAdapter);

        btnOK.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            if (name.length() < 3){
                Toast toast = Toast.makeText(getApplicationContext(), "text should be 3 letters long", Toast.LENGTH_LONG);
                toast.show();
            }else{
                String urgency = spUrgency.getSelectedItem().toString();
                todo = new Todo(name,urgency);

                Intent resultIntent = new Intent();
                resultIntent.putExtra(KEY_TODO, todo);
                setResult(RESULT_OK, resultIntent);
                finish();
            }

        });

        btnCancel.setOnClickListener(v->{
            Intent resultIntent = new Intent();
            setResult(RESULT_CANCELED, resultIntent);
            finish();
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}