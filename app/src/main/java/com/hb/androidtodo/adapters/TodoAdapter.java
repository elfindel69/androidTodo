package com.hb.androidtodo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hb.androidtodo.R;
import com.hb.androidtodo.pojos.Todo;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private List<Todo> todos;

    public class TodoViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName;
        public TextView tvUrgency;
        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvUrgency = itemView.findViewById(R.id.tvUrgency);
        }
    }

    public TodoAdapter(List<Todo> todos){
        this.todos = todos;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item,parent,
                false);
        return new TodoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoViewHolder holder, int position) {
        Todo todo = todos.get(position);

        holder.tvName.setText(todo.getName());
        holder.tvUrgency.setText(todo.getUrgency());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }
}
