package com.example.duoihinhbatchu_v2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duoihinhbatchu_v2.adapters.SpinnerAdapter;
import com.example.duoihinhbatchu_v2.dao.SQLiteHelper;
import com.example.duoihinhbatchu_v2.models.Riddle;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener{
    private int[] images = {R.drawable.vong_tuan_hoan, R.drawable.baovemoitruong_susongtruongton,
            R.drawable.langphi_ngheodoi, R.drawable.nonggian_nhansac,
            R.drawable.gieonhangatqua, R.drawable.lamnhieudieutot_duocnhieunguoiquy};
    private Spinner spImageId;
    private ImageView imageId;
    private EditText eNameId;
    private EditText eResultId;
    private Button btnUpdateId;
    private Button btnRemoveId;
    private Button btnBackId;
    private SQLiteHelper sqLiteHelper;;
    private Riddle riddle = new Riddle();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        this.initView();
        this.handleData();
        this.catchEvent();
    }
    private void initView() {
        this.spImageId = findViewById(R.id.spImageId);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this);
        this.spImageId.setAdapter(spinnerAdapter);
        this.imageId = findViewById(R.id.imageId);
        this.eNameId = findViewById(R.id.eNameId);
        this.eResultId = findViewById(R.id.eResultId);
        this.btnUpdateId = findViewById(R.id.btnUpdateId);
        this.btnRemoveId = findViewById(R.id.btnRemoveId);
        this.btnBackId = findViewById(R.id.btnBackId);
        sqLiteHelper = new SQLiteHelper(this);
    }
    private void handleData() {
        Intent intent = getIntent();

        this.riddle = (Riddle) intent.getSerializableExtra("item");

        this.eNameId.setText(this.riddle.getName());
        this.eResultId.setText(this.riddle.getResult());
        for (int i = 0; i < images.length; i++) {
            if(i==this.riddle.getImage()){
                this.imageId.setImageResource(images[i]);
            }
        }
    }
    private void catchEvent() {
        this.btnUpdateId.setOnClickListener(this);
        this.btnRemoveId.setOnClickListener(this);
        this.btnBackId.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == this.btnBackId) {
            finish();
        }

        else if (view == this.btnUpdateId) {
            int image = spImageId.getSelectedItemPosition();
            String nameText = eNameId.getText().toString();
            String resultText = eResultId.getText().toString();
            if(!nameText.isEmpty() || !resultText.isEmpty()){
                int id = this.riddle.getId();
                Riddle riddle = new Riddle(id, image, nameText, resultText);
                sqLiteHelper.update(riddle);
                finish();
            }else if(nameText.isEmpty()){
                Toast.makeText(getApplicationContext(), "Enter riddle name", Toast.LENGTH_SHORT);
            }else if(nameText.isEmpty()){
                Toast.makeText(getApplicationContext(), "Enter riddle name", Toast.LENGTH_SHORT);
            }
        }

        else if(view == this.btnRemoveId){
            int id = this.riddle.getId();

            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setTitle("Delete Notification");
            builder.setMessage("Do you want to delete: " + this.riddle.getResult());
            builder.setIcon(android.R.drawable.sym_def_app_icon);

            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    sqLiteHelper.delete(id);
                    finish();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
