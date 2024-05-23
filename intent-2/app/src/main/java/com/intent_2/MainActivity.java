package com.intent_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.intent_2.model.Account;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initView();

        Intent intent = getIntent();
        if (intent.getSerializableExtra("loginAccount") != null && intent.getSerializableExtra("registeredAccount") != null) {
            Account loginAccount = (Account) intent.getSerializableExtra("loginAccount");
            Account registeredAccount = (Account) intent.getSerializableExtra("registeredAccount");

            if (loginAccount.getUsername().equals(registeredAccount.getUsername()) && loginAccount.getPassword().equals(registeredAccount.getPassword())) {
                textView.setText("Logged in successfully");
            } else {
                textView.setText("Wrong login information");
            }
        } else {
            textView.setText("Login failed");
        }
    }

    private void initView() {
        this.textView = findViewById(R.id.txtInfo);
    }
}