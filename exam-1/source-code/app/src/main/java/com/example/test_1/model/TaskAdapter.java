package com.example.test_1.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_1.R;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.CVHolder> {
    private Context context;
    private List<Task> tasks = new ArrayList<>();
    private List<Task> backupTasks = new ArrayList<>();
    private TaskItemListener taskItemListener;

//    Constructor

    public TaskAdapter(Context context) {
        this.context = context;
        this.tasks = new ArrayList<>();
        this.backupTasks = new ArrayList<>();
    }

    //    functions
    public List<Task> getBackupTasks() {
        return this.backupTasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
        this.backupTasks.add(task);
        notifyDataSetChanged();
    }

    public void update(Task task, int position) {
        this.tasks.set(position, task);
        this.backupTasks.set(position, task);
        notifyDataSetChanged();
    }

    public void filterTasks(List<Task> filteredTasks) {
        this.tasks = filteredTasks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new CVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CVHolder holder, int position) {
        Task task = tasks.get(position);

        if (task == null) return;

        holder.name.setText(task.getName());
        holder.since.setText(task.getSince());
        holder.toDate.setText(task.getToDate());
        holder.isDone.setChecked(task.isDone());

        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasks.remove(holder.getAdapterPosition());
                backupTasks.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    public Task getItem(int position) {
        if (position < tasks.size()) return tasks.get(position);
        return null;
    }

    @Override
    public int getItemCount() {
        if (this.tasks != null) return this.tasks.size();
        return 0;
    }

    public void setClickListener(TaskItemListener taskItemListener) {
        this.taskItemListener = taskItemListener;
    }

    public class CVHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView since;
        private TextView toDate;
        private CheckBox isDone;
        private Button removeBtn;

        public CVHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.nameId);
            this.since = itemView.findViewById(R.id.tuNgayId);
            this.toDate = itemView.findViewById(R.id.denNgayId);
            this.isDone = itemView.findViewById(R.id.isDoneId);
            this.removeBtn = itemView.findViewById(R.id.bRemove);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (taskItemListener != null)
                taskItemListener.onTaskItemClick(view, getAdapterPosition());
        }
    }
}
