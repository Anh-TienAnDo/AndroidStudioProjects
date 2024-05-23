package com.track_management.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.track_management.R;
import com.track_management.UpdateDeleteActivity;
import com.track_management.adapter.RecycleViewAdapter;
import com.track_management.dao.SQLiteHelper;
import com.track_management.model.Item;
import com.track_management.model.ItemListener;

import java.util.List;

public class FragmentHistory extends Fragment implements ItemListener {
//    triển khai giao diện ItemListener
//    cung cấp dữ liệu cho RecyclerView
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
//    thao tác với cơ sở dữ liệu SQLite.
    private SQLiteHelper sqLiteHelper;


//    tạo và trả về giao diện người dùng của Fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

//    thực hiện các thao tác sau khi giao diện người dùng của Fragment đã được tạo
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.initView(view);
        this.reRender();
        this.catchEvent();
    }

    private void catchEvent() {
        this.adapter.setItemListener(this);
    }

    private void initView(View view) {
        this.recyclerView = view.findViewById(R.id.recycleView);
        this.adapter = new RecycleViewAdapter();
        this.sqLiteHelper = new SQLiteHelper(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(this.adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Item item = this.adapter.getItem(position);
//        tạo một Intent để chuyển đến Activity UpdateDeleteActivity và truyền mục được chọn thông qua Intent
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }

//    gọi lại phương thức reRender() để cập nhật dữ liệu trong RecyclerView
    @Override
    public void onResume() {
        super.onResume();

        this.reRender();
    }

//    cập nhật dữ liệu trong RecyclerView
    private void reRender() {
        List<Item> list = this.sqLiteHelper.getByIsFavourite(true);
        this.adapter.setList(list);
    }
}
