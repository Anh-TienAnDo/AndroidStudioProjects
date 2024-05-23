package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.model.Technology;
import com.example.myapplication.model.TechnologyAdapter;

import java.util.ArrayList;

public class TechnologyActivity extends AppCompatActivity {

    private ListView listView;
    TechnologyAdapter technologyAdapter;
    private Technology[] dataList;
    private TextView topic;
    private boolean show=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);
        listView=findViewById(R.id.listTechnology);
        topic = findViewById(R.id.topic);
        initData();
        topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(show){
                    technologyAdapter = new TechnologyAdapter(TechnologyActivity.this, dataList);
                    listView.setAdapter(technologyAdapter); // hiện trên giao diện theo kiểu ListView
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            for (int j = 0; j < listView.getAdapter().getCount(); j++) {
                                listView.getChildAt(j).setBackgroundColor(Color.WHITE);
                            }
                            listView.getChildAt(position).setBackgroundColor(Color.GREEN);
                        }

                    });
                    show = false;
                }
                else{
                    technologyAdapter = new TechnologyAdapter(TechnologyActivity.this, new Technology[0]);
                    listView.setAdapter(technologyAdapter); // hiện trên giao diện theo kiểu ListView
                    show = true;
                }
            }
        });
    }

    private void initData() {
        Integer[] imgs = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background};
        String[] names = {"android", "ios"};
        String[] describes = {"describe android", "describe ios"};
        String[] subs = {"sub android", "sub ios"};
        dataList = new Technology[imgs.length];
        for(int i=0;i<dataList.length;i++){
            dataList[i] = new Technology(imgs[i],names[i],describes[i],subs[i]);;
        }
    }


}