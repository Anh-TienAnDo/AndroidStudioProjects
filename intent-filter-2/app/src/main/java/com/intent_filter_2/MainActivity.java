package com.intent_filter_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button openWebBtn, sendMessageBtn, callPhoneBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initView();

        this.intent = new Intent();

        this.openWebBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWeb(view);
            }
        });

        this.sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(view);
            }
        });

        this.callPhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone(view);
            }
        });
    }

    private void initView() {
        this.openWebBtn = findViewById(R.id.btnOpenWeb);
        this.sendMessageBtn = findViewById(R.id.btnSendMessage);
        this.callPhoneBtn = findViewById(R.id.btnCallPhone);
    }

    public void openWeb(View view) {
        this.intent.setAction(Intent.ACTION_VIEW);
        this.intent.setData(Uri.parse("https://github.com/shounoop"));

        startActivity(this.intent);
    }

    public void sendMessage(View view) {
        this.intent.setAction(Intent.ACTION_VIEW);
        this.intent.setData(Uri.parse("sms:084324343"));
        this.intent.putExtra("sms_body", "Do you join the course?");

        startActivity(this.intent);
    }

    public void callPhone(View view) {
        this.intent.setAction(Intent.ACTION_DIAL);
        this.intent.setData(Uri.parse("tel:0868814071"));

        startActivity(this.intent);
    }
}