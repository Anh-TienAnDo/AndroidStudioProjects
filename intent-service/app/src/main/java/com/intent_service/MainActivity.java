package com.intent_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private Button startServiceBtn, stopServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.intent = new Intent(this, MyService.class);
        this.startServiceBtn = findViewById(R.id.startService);
        this.stopServiceBtn = findViewById(R.id.stopService);

        this.startServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(view);
            }
        });

        this.stopServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(view);
            }
        });
    }

    public void startService(View view) {
        this.intent.putExtra("data", "Data from MainActivity");

        startService(this.intent);
    }

    public void stopService(View view) {
        stopService(this.intent);
    }
}