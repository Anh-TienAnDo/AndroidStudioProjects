package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sqlite.dal.SQLiteHelper;
import com.example.sqlite.model.Item;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinner;
    private EditText eTitle, ePrice, eDate;
    private Button btUpdate, btRemove, btBack;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        this.initView();
        this.catchEvent();
        this.handleData();
    }

    private void handleData() {
        Intent intent = getIntent();

        this.item = (Item) intent.getSerializableExtra("item");

        this.eTitle.setText(this.item.getTitle());
        this.ePrice.setText(this.item.getPrice());
        this.eDate.setText(this.item.getDate());

        int currentPositionSpinner = 0;
        for (int i = 0; i < this.spinner.getCount(); i++) {
            if (this.spinner.getItemAtPosition(i).toString().equalsIgnoreCase(this.item.getCategory())) {
                currentPositionSpinner = i;
                break;
            }
        }
        this.spinner.setSelection(currentPositionSpinner);
    }

    private void catchEvent() {
        this.btUpdate.setOnClickListener(this);
        this.btRemove.setOnClickListener(this);
        this.btBack.setOnClickListener(this);
        this.eDate.setOnClickListener(this);
    }

    private void initView() {
        this.spinner = findViewById(R.id.spCategory);
        this.eTitle = findViewById(R.id.tvTitle);
        this.ePrice = findViewById(R.id.tvPrice);
        this.eDate = findViewById(R.id.tvDate);
        this.btUpdate = findViewById(R.id.btUpdate);
        this.btRemove = findViewById(R.id.btRemove);
        this.btBack = findViewById(R.id.btBack);

        this.spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View view) {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);

        if (view == this.eDate) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int yyyy, int mm, int dd) {
                    String day = dd > 9 ? dd + "" : "0" + dd;
                    String month = mm > 8 ? (mm + 1) + "" : "0" + (mm + 1);
                    String date = day + "/" + month + "/" + yyyy;

                    eDate.setText(date);
                }
            }, year, month, day);

            datePickerDialog.show();
            return;
        }

        if (view == this.btBack) {
            finish();
            return;
        }

        if (view == this.btUpdate) {
            String title = this.eTitle.getText().toString();
            String price = this.ePrice.getText().toString();
            String category = this.spinner.getSelectedItem().toString();
            String date = this.eDate.getText().toString();

            if (!title.isEmpty() && price.matches("\\d+")) {
                int id = this.item.getId();
                Item newItem = new Item(id, title, category, price, date);

                sqLiteHelper.update(newItem);
                finish();
            }
        }

        if (view == this.btRemove) {
            int id = this.item.getId();

            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban co chac muon xoa " + this.item.getTitle() + " khong?");
            builder.setIcon(R.drawable.remove);

            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    sqLiteHelper.delete(id);
                    finish();
                }
            });

            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}