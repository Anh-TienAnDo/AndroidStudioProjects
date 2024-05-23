package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    private TextView result;
    private EditText in1, in2;
    private Button cal;
    private Spinner spOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initView();
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double n1 = Double.parseDouble(in1.getText().toString());
                    double n2 = Double.parseDouble(in2.getText().toString());
                    String ope = spOperator.getSelectedItem().toString();
                    String res = Cal(n1, n2, ope);
                    result.setText(res);
                } catch (Exception e) {
                    System.out.println(e);
                    result.setText("error! Please enter number");
                }
            }
        });
    }

    private void initView() {
        result = findViewById(R.id.result);
        in1 = findViewById(R.id.inNumber1);
        in2 = findViewById(R.id.inNumber2);
        cal = findViewById(R.id.cal);
        spOperator = findViewById(R.id.spOperator);
    }

    private String Cal(double n1, double n2, String operator) {
        String res = "";
        switch (operator) {
            case "add":
                res = "Add: " + (n1 + n2);
                break;
            case "sub":
                res = "Sub: " + (n1 - n2);
                break;
            case "mul":
                res = "Mul: " + (n1 * n2);
                break;
            case "div":
                if (n2 == 0) {
                    res = "error number 2 = 0";
                } else {
                    res = "Div: " + (n1 / n2);
                }
                break;
        }
        return res;
    }
}