package com.example.thuchanh1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText etSearch;
    private AppCompatEditText etName;
    private AppCompatEditText etPrice;
    private AppCompatEditText etOrigin;
    private AppCompatSpinner spinnerImage;
    private PhoneAdapter phoneAdapter;
    private int position;
    ArrayList<Integer> imagePhoneList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniViews();
        initActions();

    }

    private void initActions() {
        findViewById(R.id.tvAdd).setOnClickListener(v -> addPhone());

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                phoneAdapter.searchPhone(s.toString());
            }
        });
        findViewById(R.id.tvUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPhone(position);
            }
        });
    }

    private void iniViews() {
        etName = findViewById(R.id.etName);
        etSearch = findViewById(R.id.etSearch);
        etPrice = findViewById(R.id.etPrice);
        etOrigin = findViewById(R.id.etOrigin);
        spinnerImage = findViewById(R.id.imageSpinner);
        RecyclerView recyclerView = findViewById(R.id.rvPhone);

        imagePhoneList.add(R.drawable.ic_samsung);
        imagePhoneList.add(R.drawable.ic_asus);
        imagePhoneList.add(R.drawable.ic_apple);
        imagePhoneList.add(R.drawable.ic_xiamo);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, R.layout.spinner_item, imagePhoneList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view;
                if (convertView == null) {
                    view = getLayoutInflater().inflate(R.layout.spinner_item, parent, false);
                } else {
                    view = convertView;
                }
                ImageView imageView = view.findViewById(R.id.imageView);
                imageView.setImageResource(imagePhoneList.get(position));
                return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view;
                if (convertView == null) {
                    view = getLayoutInflater().inflate(R.layout.spinner_item, parent, false);
                } else {
                    view = convertView;
                }
                ImageView imageView = view.findViewById(R.id.imageView);
                imageView.setImageResource(imagePhoneList.get(position));
                return view;
            }
        };
        spinnerImage.setAdapter(adapter);

        phoneAdapter = new PhoneAdapter(new ArrayList<>());
        recyclerView.setAdapter(phoneAdapter);
    }

    private void addPhone() {
        if(validateFields()) {
            String name = etName.getText().toString();
            String price = etPrice.getText().toString();
            String origin = etOrigin.getText().toString();
            int image = imagePhoneList.get(spinnerImage.getSelectedItemPosition());
            Phone phone = new Phone(name, price,origin, image);
            phoneAdapter.addPhone(phone);
        }
    }


    public void editPhone(int position) {
        if (validateFields()) {
            String name = etName.getText().toString();
            String price = etPrice.getText().toString();
            String origin = etOrigin.getText().toString();
            int image = imagePhoneList.get(spinnerImage.getSelectedItemPosition());
            Phone phone = new  Phone(name, price,origin, image);
            phoneAdapter.updatePhone(position, phone);
        }
    }

    private boolean validateFields() {
        String name = etName.getText().toString();
        String price = etPrice.getText().toString();
        String origin = etOrigin.getText().toString();

        int image = (int) spinnerImage.getSelectedItem();

        if (name.isEmpty()) {
            etName.setError("Name is required");
            return false;
        }

        if (price.isEmpty()) {
            etPrice.setError("Price is required");
            return false;
        }

        if (origin.isEmpty()) {
            etOrigin.setError("Origin is required");
            return false;
        }

        if (!origin.matches("^[a-zA-Z0-9]*$")) {
            etOrigin.setError("Origin is invalid");
            return false;
        }

        if (!price.matches("^[0-9]*$")) {
            etPrice.setError("Price is invalid");
            return false;
        }


        if (image == 0) {
            return false;
        }

        return true;
    }

    void setInputFromAdapter(Phone phone, int position) {
        etName.setText(phone.getName());
        etPrice.setText(phone.getPrice());
        etOrigin.setText(phone.getOrigin());
        spinnerImage.setSelection(imagePhoneList.indexOf(phone.getDrawableId()),true);
        findViewById(R.id.tvUpdate).setBackgroundResource(R.drawable.bg_green);
        this.position = position;
    }
}