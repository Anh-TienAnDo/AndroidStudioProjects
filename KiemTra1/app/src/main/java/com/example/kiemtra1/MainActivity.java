package com.example.kiemtra1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kiemtra1.model.CauThu;
import com.example.kiemtra1.model.CauThuAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CauThuAdapter.CatItemListener, SearchView.OnQueryTextListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initView();
        this.setOnClick();
        this.setOnQuery();
    }

//    private Spinner spinner;
    private int[] images = {R.drawable.man, R.drawable.woman};
    private RecyclerView recyclerView;
    private CauThuAdapter catAdapter;
    private EditText nameET, dateET;
    private RadioButton rbMan, rbWoman;
    private CheckBox cbHauve, cbTienve, cbTiendao;
    private Button addBtn, updateBtn;
    private int selectedItemIndex;
    private SearchView searchView;

    private void initView() {
//        this.spinner = findViewById(R.id.imgSpinnerId);
//        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this);
//        this.spinner.setAdapter(spinnerAdapter);
        this.nameET = findViewById(R.id.nameId);
        this.dateET = findViewById(R.id.sinhId);
        cbHauve = findViewById(R.id.cbHauVe);
        cbTienve = findViewById(R.id.cbTienVe);
        cbTiendao = findViewById(R.id.cbTienDao);
        rbMan = findViewById(R.id.rbMan);
        rbWoman = findViewById(R.id.rbWoman);
        this.recyclerView = findViewById(R.id.recycleViewId);
        this.addBtn = findViewById(R.id.addBtn);
        this.updateBtn = findViewById(R.id.updateBtn);
        this.updateBtn.setEnabled(false);
        this.catAdapter = new CauThuAdapter(this);
//        event click list data
        this.catAdapter.setClickListener(this);
        this.searchView = findViewById(R.id.seachId);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.recyclerView.setLayoutManager(linearLayoutManager);
//        call method show data from TourAdapter
        this.recyclerView.setAdapter(this.catAdapter);
    }

    private void setOnQuery() {
        this.searchView.setOnQueryTextListener(this);
    }


    //    click add/update
    private void setOnClick() {
        this.dateET.setOnClickListener(new View.OnClickListener(){
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
           @Override
           public void onClick(View view) {
               DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                       dateET.setText(d + "/" + (m + 1) + "/" + y);

                   }
               }, year, month, day);
               dpd.show();
           }
        });
        this.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CauThu cat = new CauThu();
//                get data
//                int i = spinner.getSelectedItemPosition();
                String nameText = nameET.getText().toString();
                String date = String.valueOf(dateET.getText());
                String gt = "";
                if(rbMan.isChecked()){
                    gt += rbMan.getText().toString();
                }else{
                    gt += rbWoman.getText().toString();
                }
                String vt = "";
                if(cbHauve.isChecked()){
                    vt += cbHauve.getText().toString() + ",";
                }
                if(cbTienve.isChecked()){
                    vt += cbTienve.getText().toString() + ",";
                }
                if(cbTiendao.isChecked()){
                    vt += cbTiendao.getText().toString() + ",";
                }

                try {
//                    check and save data
                    cat.setName(nameText);
                    cat.setNgaysinh(date);
                    cat.setGt(gt);
                    cat.setVt(vt);
                    catAdapter.add(cat);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CauThu cat = new CauThu();
//                get data
//                int i = spinner.getSelectedItemPosition();
                String nameText = nameET.getText().toString();
                String date = String.valueOf(dateET.getText());
                String gt = "";
                if(rbMan.isChecked()){
                    gt += rbMan.getText().toString();
                }else{
                    gt += rbWoman.getText().toString();
                }
                String vt = "";
                if(cbHauve.isChecked()){
                    vt += cbHauve.getText().toString() + ",";
                }
                if(cbTienve.isChecked()){
                    vt += cbTienve.getText().toString() + ",";
                }
                if(cbTiendao.isChecked()){
                    vt += cbTiendao.getText().toString() + ",";
                }
//                vt = vt.substring(0,vt.length()-1);

                try {
//                    check and save data
                    cat.setName(nameText);
                    cat.setNgaysinh(date);
                    cat.setGt(gt);
                    cat.setVt(vt);
                    catAdapter.update(MainActivity.this.selectedItemIndex, cat);
                    addBtn.setEnabled(true);
                    updateBtn.setEnabled(false);
                    MainActivity.this.rbMan.setChecked(true);
                    MainActivity.this.rbWoman.setChecked(false);
                    MainActivity.this.cbHauve.setChecked(false);
                    MainActivity.this.cbTienve.setChecked(false);
                    MainActivity.this.cbTiendao.setChecked(false);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    //    click 1 object
    @Override
    public void onItemClick(View view, int position) {
        this.addBtn.setEnabled(false);
        this.updateBtn.setEnabled(true);
        this.selectedItemIndex = position;
        CauThu cat = catAdapter.getCat(position);
        this.cbHauve.setChecked(false);
        this.cbTienve.setChecked(false);
        this.cbTiendao.setChecked(false);

        this.reRenderForm(cat);
    }

    private void reRenderForm(CauThu cat) {
        this.nameET.setText(cat.getName());
        this.dateET.setText(cat.getNgaysinh());
        if(cat.getGt().equals("man")){
            this.rbMan.setChecked(true);
        }else{
            this.rbWoman.setChecked(true);
        }
        if(cat.getVt().indexOf("Hau ve")>=0){
            this.cbHauve.setChecked(true);
        }
        if(cat.getVt().indexOf("Tien ve")>=0){
            this.cbTienve.setChecked(true);
        }
        if(cat.getVt().indexOf("Tien dao")>=0){
            this.cbTiendao.setChecked(true);
        }
        int indexOfImgInSpinner = 0;

    }

    //    logic search
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        filter(query);
        return false;
    }

    private void filter(String query) {
        List<CauThu> filteredCats = new ArrayList<>();

        for (CauThu cat : catAdapter.getBackupCats()) {
            if (cat.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredCats.add(cat);
            }
        }

        if (filteredCats.isEmpty()) {
            Toast.makeText(this, "No data matched", Toast.LENGTH_SHORT).show();
        }
        this.catAdapter.filterCats(filteredCats);
    }
}