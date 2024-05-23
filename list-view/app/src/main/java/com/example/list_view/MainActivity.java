package com.example.list_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ListView listView;
    private ArrayAdapter<String> technologyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initView();
        this.initListView();
        this.setOnClick();
    }

    private void setOnClick() {
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedItem = technologyAdapter.getItem(position);
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListView() {
        String[] technologies = getResources().getStringArray(R.array.technologies);
        this.technologyAdapter = new ArrayAdapter<>(this, R.layout.list_view_item, technologies);
        this.listView.setAdapter(this.technologyAdapter);
    }

    private void initView() {
        this.textView = findViewById(R.id.textViewId);
        this.listView = findViewById(R.id.listViewId);
    }
}