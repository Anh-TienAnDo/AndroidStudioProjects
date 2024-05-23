package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fragment.model.FragmentA;
import com.example.fragment.model.FragmentB;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private Button btnA, btnB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        this.initView();
        this.catchEvents();
    }

    private void catchEvents() {
        this.btnA.setOnClickListener(this);
        this.btnB.setOnClickListener(this);
    }

    private void initView() {
        this.btnA = findViewById(R.id.addA);
        this.btnB = findViewById(R.id.addB);
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment;

        switch (view.getId()) {
            case R.id.addA:
                fragment = new FragmentA();
                fragmentTransaction.add(R.id.fragment, fragment);
                break;
            case R.id.addB:
                fragment = new FragmentB();
                fragmentTransaction.add(R.id.fragment, fragment);
                break;
        }

        fragmentTransaction.commit();
    }
}