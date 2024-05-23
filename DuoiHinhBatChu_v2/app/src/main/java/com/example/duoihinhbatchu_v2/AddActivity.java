package com.example.duoihinhbatchu_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duoihinhbatchu_v2.adapters.SpinnerAdapter;
import com.example.duoihinhbatchu_v2.dao.SQLiteHelper;
import com.example.duoihinhbatchu_v2.models.Riddle;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spImageId;
    private EditText eNameId;
    private EditText eResultId;
    private Button btnAddId;
    private Button btnCancelId;
    private SQLiteHelper sqLiteHelper;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        this.initView();
        this.catchEvent();
    }
    private void initView() {
        this.spImageId = findViewById(R.id.spImageId);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this);
        this.spImageId.setAdapter(spinnerAdapter);
        this.eNameId = findViewById(R.id.eNameId);
        this.eResultId = findViewById(R.id.eResultId);
        this.btnAddId = findViewById(R.id.btnAddId);
        this.btnCancelId = findViewById(R.id.btnCancelId);
        sqLiteHelper = new SQLiteHelper(this);
    }
    private void catchEvent() {
        this.btnAddId.setOnClickListener(this);
        this.btnCancelId.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == this.btnCancelId) {
            finish();
        }

        else if (view == this.btnAddId) {
            int image = spImageId.getSelectedItemPosition();
            String nameText = eNameId.getText().toString();
            String resultText = eResultId.getText().toString();
            if(!nameText.isEmpty() || !resultText.isEmpty()){
                Riddle riddle = new Riddle(image, nameText, resultText);
                sqLiteHelper.addItem(riddle);
                finish();
            }else if(nameText.isEmpty()){
                Toast.makeText(getApplicationContext(), "Enter riddle name", Toast.LENGTH_SHORT);
            }else if(nameText.isEmpty()){
                Toast.makeText(getApplicationContext(), "Enter riddle name", Toast.LENGTH_SHORT);
            }
        }
    }

}
