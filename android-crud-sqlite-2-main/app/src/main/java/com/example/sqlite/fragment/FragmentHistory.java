package com.example.sqlite.fragment;

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

import com.example.sqlite.R;
import com.example.sqlite.UpdateDeleteActivity;
import com.example.sqlite.adapter.RecycleViewAdapter;
import com.example.sqlite.dal.SQLiteHelper;
import com.example.sqlite.model.Item;
import com.example.sqlite.model.ItemListener;

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

        this.recyclerView = view.findViewById(R.id.recycleView);
        this.adapter = new RecycleViewAdapter();
        this.sqLiteHelper = new SQLiteHelper(getContext());

        List<Item> list = this.sqLiteHelper.getAll();
        this.adapter.setList(list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(this.adapter);

        this.adapter.setItemListener(this);
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

        List<Item> list = this.sqLiteHelper.getAll();
        this.adapter.setList(list);
    }
}
