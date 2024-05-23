package com.track_management.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class FragmentHome extends Fragment implements ItemListener {
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private SQLiteHelper sqLiteHelper;
    private TextView tvTong;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

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
        this.tvTong = view.findViewById(R.id.tvTong);
        this.adapter = new RecycleViewAdapter();
        this.sqLiteHelper = new SQLiteHelper(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(this.adapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        Item item = this.adapter.getItem(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("item", item);

        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        this.reRender();
    }

    private void reRender() {
        List<Item> list = sqLiteHelper.getAll();
        this.adapter.setList(list);
        this.tvTong.setText("Total: " + list.size());
    }
}
