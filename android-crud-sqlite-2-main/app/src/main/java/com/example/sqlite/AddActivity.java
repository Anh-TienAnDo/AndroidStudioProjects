package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinner;
    private EditText eTitle, ePrice, eDate;
    private Button btAdd, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.initView();
        this.catchEvent();
    }

    private void catchEvent() {
        this.btAdd.setOnClickListener(this);
        this.btCancel.setOnClickListener(this);
        this.eDate.setOnClickListener(this);
    }

    private void initView() {
        this.spinner = findViewById(R.id.spCategory);
        this.eTitle = findViewById(R.id.tvTitle);
        this.ePrice = findViewById(R.id.tvPrice);
        this.eDate = findViewById(R.id.tvDate);
        this.btAdd = findViewById(R.id.btAdd);
        this.btCancel = findViewById(R.id.btCancel);

        this.spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View view) {
        if (view == eDate) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        if (view == btCancel) {
            finish();
            return;
        }

        if (view == btAdd) {
            String title = this.eTitle.getText().toString();
            String price = this.ePrice.getText().toString();
            String category = this.spinner.getSelectedItem().toString();
            String date = this.eDate.getText().toString();

            if (!title.isEmpty() && price.matches("\\d+")) {
                Item item = new Item(title, category, price, date);

                SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
                sqLiteHelper.addItem(item);
                finish();
            }
        }
    }
}