package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CheckBox cb1, cb2, cb3;
    private RatingBar rbar;
    private RadioButton rb1, rb2;
    private Button sub;
    private TextView result;
    private Spinner spinner, spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);
        spinner = findViewById(R.id.sp1);
        spinner2 = findViewById(R.id.sp2);
        String[] educations = {"PTIT", "HUST", "NEU"};
        String[] country = getResources().getStringArray(R.array.country);
        ArrayAdapter<String> ada2 = new ArrayAdapter<>(this, R.layout.item, country);
        spinner2.setAdapter(ada2);
        ArrayAdapter<String> ada = new ArrayAdapter<>(this, R.layout.item, educations);
        spinner.setAdapter(ada);
        initView();
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String res = "";
                if(cb1.isChecked()){
                    res += cb1.getText().toString() + ", ";
                }
                if(cb2.isChecked()){
                    res += cb2.getText().toString() + ", ";
                }
                if(cb3.isChecked()){
                    res += cb3.getText().toString() + ", ";
                }
                res = res.substring(0, res.length()-2);
                if(rb1.isChecked()){
                    res += '\n' + rb1.getText().toString();
                }else{
                    res+= '\n' + rb2.getText().toString();
                }
                res += '\n' + String.valueOf(rbar.getRating());
                res += '\n' + spinner.getSelectedItem().toString();
                res += '\n' + spinner2.getSelectedItem().toString();
                result.setText(res);
            }
        });
    }

    private void initView() {
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        rbar = findViewById(R.id.rbar);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        sub = findViewById(R.id.submit);
        result = findViewById(R.id.txtRes);
    }



}