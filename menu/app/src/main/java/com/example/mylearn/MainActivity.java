package com.example.mylearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mFile:
                Toast.makeText(this, "ban chon File", Toast.LENGTH_LONG).show();
                break;
            case R.id.mEmail:
                System.exit(0);
                break;
            case R.id.mPhone:
                Toast.makeText(this, "Phone", Toast.LENGTH_LONG).show();
                break;
                default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}