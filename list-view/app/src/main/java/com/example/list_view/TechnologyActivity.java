package com.example.list_view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.list_view.model.Technology;
import com.example.list_view.model.TechnologyAdapter;

public class TechnologyActivity extends AppCompatActivity {
    private ListView listView;
    private TechnologyAdapter technologyAdapter;
    private Technology[] technologies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);

        this.initView();
        this.initData();

        this.technologyAdapter = new TechnologyAdapter(this, this.technologies);
        this.listView.setAdapter(technologyAdapter);

        this.setOnClick();
    }

    private void setOnClick() {
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for (int i = 0; i < listView.getAdapter().getCount(); i++) {
                    listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }

                Technology technology = technologyAdapter.getItem(position);
                Toast.makeText(getApplicationContext(), technology.getName(), Toast.LENGTH_SHORT).show();

                listView.getChildAt(position).setBackgroundColor(Color.GREEN);
            }
        });
    }

    private void initView() {
        this.listView = findViewById(R.id.listViewId);
    }

    private void initData() {
        Integer[] images = {R.drawable.tech_img_1, R.drawable.tech_img_2, R.drawable.tech_img_3, R.drawable.tech_img_4};
        String[] names = {"Android", "IOS", "Blackberry", "Window"};
        String[] subs = {"Sub Android", "Sub IOS", "Sub Blackberry", "Sub Window"};
        String[] descriptions = {"Desc Android", "Desc IOS", "Desc Blackberry", "Desc Window"};

        this.technologies = new Technology[images.length];
        for (int i = 0; i < this.technologies.length; i++) {
            this.technologies[i] = new Technology(images[i], names[i], subs[i], descriptions[i]);
        }
    }
}