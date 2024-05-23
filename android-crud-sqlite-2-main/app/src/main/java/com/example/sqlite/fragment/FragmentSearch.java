package com.example.sqlite.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.AddActivity;
import com.example.sqlite.R;
import com.example.sqlite.adapter.RecycleViewAdapter;
import com.example.sqlite.dal.SQLiteHelper;
import com.example.sqlite.model.Category;
import com.example.sqlite.model.Item;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FragmentSearch extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private TextView tvTong, tvStatistic;
    private Button btSearch;
    private SearchView searchView;
    private EditText eFromDate, eToDate;
    private Spinner spCategory;
    private RecycleViewAdapter adapter;
    private SQLiteHelper sqLiteHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.initView(view);
        this.handleStatistic();

        List<Item> list = this.sqLiteHelper.getAll();
        this.handleChangedData(list);

        this.catchEvent();
    }

    private void initView(View view) {
        this.recyclerView = view.findViewById(R.id.recycleView);
        this.tvTong = view.findViewById(R.id.tvTong);
        this.btSearch = view.findViewById(R.id.btSearch);
        this.searchView = view.findViewById(R.id.search);
        this.eFromDate = view.findViewById(R.id.eFrom);
        this.eToDate = view.findViewById(R.id.eTo);
        this.spCategory = view.findViewById(R.id.spCategory);
        this.tvStatistic = view.findViewById(R.id.tvStat);

        this.initRecycleView();
        this.initSQLiteHelper();
        this.initSpinner(this.spCategory, getResources().getStringArray(R.array.category));
    }

    private void handleStatistic() {
        String[] strCategories = getResources().getStringArray(R.array.category);
        List<Category> objCategories = new ArrayList<>();

        for (int i = 0; i < strCategories.length; i++) {
            List<Item> items = sqLiteHelper.getItemsByCategory(strCategories[i]);
            objCategories.add(new Category(strCategories[i], items.size()));
        }

        Comparator<Category> byQuantityDesc = Comparator.comparing(Category::getQuantity).reversed();
        Collections.sort(objCategories, byQuantityDesc);

        String statistic = "";
        for (Category category : objCategories) {
            System.out.println(category.getName() + ": " + category.getQuantity());
            statistic += category.getName() + ": " + category.getQuantity() + "; ";
        }

        this.tvStatistic.setText(statistic);
    }

    private void initSQLiteHelper() {
        this.sqLiteHelper = new SQLiteHelper(getContext());
    }

    private void initRecycleView() {
        this.adapter = new RecycleViewAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(this.adapter);
    }

    private void catchEvent() {
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Item> list = sqLiteHelper.getItemsByTitle(s);
                handleChangedData(list);
                return true;
            }
        });

        this.eFromDate.setOnClickListener(this);
        this.eToDate.setOnClickListener(this);
        this.btSearch.setOnClickListener(this);

        this.spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedCategory = spCategory.getItemAtPosition(position).toString();

                List<Item> list;
                if (!selectedCategory.equalsIgnoreCase("all")) {
                    list = sqLiteHelper.getItemsByCategory(selectedCategory);
                } else {
                    list = sqLiteHelper.getAll();
                }

                handleChangedData(list);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private int getTotal(List<Item> list) {
        int res = 0;

        for (Item item : list) {
            res += Integer.parseInt(item.getPrice());
        }

        return res;
    }

    private void initSpinner(Spinner spinner, String[] arr) {

        String[] arr1 = new String[arr.length + 1];
        arr1[0] = "All";
        for (int i = 0; i < arr.length; i++) {
            arr1[i + 1] = arr[i];
        }

        spinner.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.item_spinner, arr1));
    }

    private void handleChangedData(List<Item> list) {
        adapter.setList(list);
        tvTong.setText("Total money: " + getTotal(list) + "K");
    }

    @Override
    public void onClick(View view) {
        if (view == this.eFromDate) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int yyyy, int mm, int dd) {
                    String day = dd > 9 ? dd + "" : "0" + dd;
                    String month = mm > 8 ? (mm + 1) + "" : "0" + (mm + 1);
                    String date = day + "/" + month + "/" + yyyy;

                    eFromDate.setText(date);
                }
            }, year, month, day);

            datePickerDialog.show();
            return;
        }

        if (view == this.eToDate) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int yyyy, int mm, int dd) {
                    String day = dd > 9 ? dd + "" : "0" + dd;
                    String month = mm > 8 ? (mm + 1) + "" : "0" + (mm + 1);
                    String date = day + "/" + month + "/" + yyyy;

                    eToDate.setText(date);
                }
            }, year, month, day);

            datePickerDialog.show();
            return;
        }

        if (view == this.btSearch) {
            String from = eFromDate.getText().toString();
            String to = eToDate.getText().toString();

            if (!from.isEmpty() && !from.isEmpty()) {
                List<Item> list = sqLiteHelper.getItemsByTimePeriod(from, to);
                this.handleChangedData(list);
                this.eFromDate.setText("");
                this.eToDate.setText("");
            }
        }
    }
}
