package com.demo.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.demo.R;

// giao diện người dùng và logic
public class FragmentFood extends Fragment {
    @Nullable
    @Override
//    hiển thị giao diện người dùng
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
//        trả về một đối tượng View, đại diện cho giao diện người dùng
        return view;
    }
}
