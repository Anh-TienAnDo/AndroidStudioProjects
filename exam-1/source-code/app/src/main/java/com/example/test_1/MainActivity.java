package com.example.test_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.test_1.model.TaskAdapter;
import com.example.test_1.model.TaskItemListener;
import com.example.test_1.model.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskItemListener, SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private EditText name, since, toDate;
    private Button addBtn, updateBtn;
    private CheckBox isDone;
    private int currentPosition;
    private TaskAdapter taskAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initView();
        this.setOnClick();

        this.searchView.setOnQueryTextListener(this);
    }

    private void setOnClick() {
        this.taskAdapter.setClickListener(this);

        this.since.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();

                int cy = c.get(Calendar.YEAR);
                int cm = c.get(Calendar.MONTH);
                int cd = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        since.setText(d + "-" + (m + 1) + "-" + y);
                    }
                }, cy, cm, cd);
                dialog.show();
            }
        });

        this.toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();

                int cy = c.get(Calendar.YEAR);
                int cm = c.get(Calendar.MONTH);
                int cd = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        toDate.setText(d + "-" + (m + 1) + "-" + y);
                    }
                }, cy, cm, cd);
                dialog.show();
            }
        });

        this.isDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        this.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = isDone.isChecked();

                Task task = new Task(name.getText().toString(), since.getText().toString(), toDate.getText().toString(), checked);

                taskAdapter.add(task);
                name.setText("");
                since.setText("");
                toDate.setText("");
                isDone.setChecked(false);
            }
        });

        this.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = isDone.isChecked();

                Task task = new Task(name.getText().toString(), since.getText().toString(), toDate.getText().toString(), checked);

                taskAdapter.update(task, currentPosition);
                name.setText("");
                since.setText("");
                toDate.setText("");
                isDone.setChecked(false);

                addBtn.setEnabled(true);
                updateBtn.setEnabled(false);
            }
        });
    }

    private void initView() {
        this.recyclerView = findViewById(R.id.recyclerView);
        this.addBtn = findViewById(R.id.addBtnId);
        this.updateBtn = findViewById(R.id.updateBtnId);
        this.name = findViewById(R.id.nameId);
        this.since = findViewById(R.id.sinceId);
        this.toDate = findViewById(R.id.toDateId);
        this.isDone = findViewById(R.id.isDoneId);
        this.searchView = findViewById(R.id.searchId);

        this.updateBtn.setEnabled(false);

        this.taskAdapter = new TaskAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, recyclerView.VERTICAL, false);
        this.recyclerView.setLayoutManager(manager);
        this.recyclerView.setAdapter(taskAdapter);
    }

    @Override
    public void onTaskItemClick(View view, int position) {
        this.addBtn.setEnabled(false);
        this.updateBtn.setEnabled(true);
        this.currentPosition = position;

        Task task = taskAdapter.getItem(position);

        this.name.setText(task.getName());
        this.since.setText(task.getSince());
        this.toDate.setText(task.getToDate());
        this.isDone.setChecked(task.isDone());
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        this.filterTasks(s);
        return false;
    }

    private void filterTasks(String s) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : taskAdapter.getBackupTasks()) {
            if (task.getName().toLowerCase().contains(s.toLowerCase())) {
                filteredTasks.add(task);
            }
        }

        this.taskAdapter.filterTasks(filteredTasks);
    }
}