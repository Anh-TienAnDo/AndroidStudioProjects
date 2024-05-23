package com.example.recycleview_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recycleview_crud.model.Cat;
import com.example.recycleview_crud.model.CatAdapter;
import com.example.recycleview_crud.model.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CatAdapter.CatItemListener, SearchView.OnQueryTextListener {
    private Spinner spinner;
    private int[] images = {R.drawable.cat_1, R.drawable.cat_2, R.drawable.cat_3, R.drawable.cat_4};
    private RecyclerView recyclerView;
    private CatAdapter catAdapter;
    private EditText nameET, descriptionET, priceET;
    private Button addBtn, updateBtn;
    private int selectedItemIndex;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initView();
        this.setOnClick();
        this.setOnQuery();
    }

    private void setOnQuery() {
        this.searchView.setOnQueryTextListener(this);
    }

    private void setOnClick() {
        this.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cat cat = new Cat();
                int i = spinner.getSelectedItemPosition();
                String nameText = nameET.getText().toString();
                String descText = descriptionET.getText().toString();
                String priceText = priceET.getText().toString();

                try {
                    int img = images[i];
                    double price = Double.parseDouble(priceText);

                    cat.setImg(img);
                    cat.setName(nameText);
                    cat.setDescription(descText);
                    cat.setPrice(price);

                    catAdapter.add(cat);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cat cat = new Cat();
                int i = spinner.getSelectedItemPosition();
                String nameText = nameET.getText().toString();
                String descText = descriptionET.getText().toString();
                String priceText = priceET.getText().toString();

                try {
                    int img = images[i];
                    double price = Double.parseDouble(priceText);

                    cat.setImg(img);
                    cat.setName(nameText);
                    cat.setDescription(descText);
                    cat.setPrice(price);

                    catAdapter.update(selectedItemIndex, cat);
                    addBtn.setEnabled(true);
                    updateBtn.setEnabled(false);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        this.spinner = findViewById(R.id.imgSpinnerId);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this);
        this.spinner.setAdapter(spinnerAdapter);
        this.nameET = findViewById(R.id.nameId);
        this.descriptionET = findViewById(R.id.descriptionId);
        this.priceET = findViewById(R.id.priceId);
        this.recyclerView = findViewById(R.id.recycleViewId);
        this.addBtn = findViewById(R.id.addBtn);
        this.updateBtn = findViewById(R.id.updateBtn);
        this.updateBtn.setEnabled(false);
        this.catAdapter = new CatAdapter(this);
        this.catAdapter.setClickListener(this);
        this.searchView = findViewById(R.id.seachId);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(this.catAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        this.addBtn.setEnabled(false);
        this.updateBtn.setEnabled(true);
        this.selectedItemIndex = position;
        Cat cat = catAdapter.getCat(position);

        this.reRenderForm(cat);
    }

    private void reRenderForm(Cat cat) {
        this.nameET.setText(cat.getName());
        this.descriptionET.setText(cat.getDescription());
        this.priceET.setText(cat.getPrice() + "");

        int img = cat.getImg();
        int indexOfImgInSpinner = 0;

        for (int i = 0; i < images.length; i++) {
            if (img == images[i]) {
                indexOfImgInSpinner = i;
            }
        }
        spinner.setSelection(indexOfImgInSpinner, true);
    }

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
        List<Cat> filteredCats = new ArrayList<>();

        for (Cat cat : catAdapter.getBackupCats()) {
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