package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.intent.model.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.button = findViewById(R.id.bt);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(MainActivity.this, SecondActivity.class);

                String name = "Hoang Minh Tuan";
                t.putExtra("name", name);

                int age = 22;
                t.putExtra("age", age);

                String[] subjects = {"LTDT", "WEB", "OOP"};
                t.putExtra("subjects", subjects);

                Student s = new Student(R.drawable.img, "Shounoop", 0);
                t.putExtra("st", s);

                List<Student> list = new ArrayList<>();
                list.add(s);
                list.add(new Student(R.drawable.img, "Shounoop1", 1));
                list.add(new Student(R.drawable.img, "Shounoop2", 2));
                list.add(new Student(R.drawable.img, "Shounoop3", 3));
                t.putExtra("data", (Serializable) list);

                startActivity(t);
            }
        });
    }
}