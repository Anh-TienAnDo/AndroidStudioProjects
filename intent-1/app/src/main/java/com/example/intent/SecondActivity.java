package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intent.model.Student;

import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private Button button;
    private TextView tvName, tvSub, tvST, tvList;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        this.button = findViewById(R.id.bt);
        this.tvName = findViewById(R.id.tvName);
        this.tvSub = findViewById(R.id.tvSub);
        this.tvST = findViewById(R.id.tvST);
        this.img = findViewById(R.id.img);
        this.tvList = findViewById(R.id.tvList);

        Intent t = getIntent();

        String name = t.getStringExtra("name");
        int age = t.getIntExtra("age", 21);
        String[] subjects = t.getStringArrayExtra("subjects");
        Student s = (Student) t.getSerializableExtra("st");
        List<Student> list = (List<Student>) t.getSerializableExtra("data");
        String tt = "";

        for (Student i : list) {
            tt += i.getName() + ", " + i.getAge();
        }

        this.tvName.setText(name + ", " + age + " years old");
        this.tvSub.setText(Arrays.toString(subjects));
        this.tvST.setText("Name: " + s.getName() + ", age: " + s.getAge());
        this.img.setImageResource(s.getImg());
        this.tvList.setText(tt);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                kết thúc activity hiện tại và quay lại activity trước
                finish();
            }
        });
    }
}