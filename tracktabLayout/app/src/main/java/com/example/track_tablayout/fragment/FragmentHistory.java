package com.example.track_tablayout.fragment;

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

import com.example.track_tablayout.R;
import com.example.track_tablayout.UpdateDeleteActivity;
import com.example.track_tablayout.adapter.RecycleViewAdapter;
import com.example.track_tablayout.dao.SQLiteHelper;
import com.example.track_tablayout.model.Item;
import com.example.track_tablayout.model.ItemListener;

import java.util.List;

public class FragmentHistory extends Fragment implements ItemListener {
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper sqLiteHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
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
        List<Item> list = this.sqLiteHelper.getByIsFavourite(true);
        this.adapter.setList(list);
    }

}
