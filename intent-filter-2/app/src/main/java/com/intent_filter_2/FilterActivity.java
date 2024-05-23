package com.intent_filter_2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class FilterActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        this.tv = findViewById(R.id.tv);

//        this.handleOpenWeb();
//        this.handleSendMessage();
        this.handleCallPhone();
    }

    private void handleCallPhone() {
        String dataString = getIntent().getDataString(); // tel:0868814071
        String phoneNumber = dataString.substring(4); // 0868814071

        this.tv.setText("Phone number: " + phoneNumber);
    }

    private void handleSendMessage() {
        String s = "Content: " + getIntent().getStringExtra("sms_body").toString();
        s += "\nData: " + getIntent().getDataString(); // sms:084324343

        this.tv.setText(s);
    }

    private void handleOpenWeb() {
        Uri url = getIntent().getData();

        String s = "Scheme:" + url.getScheme() + "\nHost:" + url.getHost();

        int k = 1;
        for (String i : url.getPathSegments()) {
            s += "\nParam-" + (k++) + ": " + i;
        }
        s += "\nAction: " + getIntent().getAction();

        this.tv.setText(s);
    }


}