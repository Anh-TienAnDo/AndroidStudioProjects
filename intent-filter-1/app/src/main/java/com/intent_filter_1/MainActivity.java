package com.intent_filter_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button sendEmail, sendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initView();

        this.sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail(view);
            }
        });

        this.sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(view);
            }
        });
    }

    private void initView() {
        this.sendEmail = findViewById(R.id.btnSendEmail);
        this.sendMessage = findViewById(R.id.btnSendMessage);
    }

    public void sendEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, "hmtuan9a@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Invite you to join the Android course");
        startActivity(Intent.createChooser(intent, "Choose email..."));
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        startActivity(intent);
    }
}