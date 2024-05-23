package com.example.duoihinhbatchu_v2.manage;

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

import com.example.duoihinhbatchu_v2.R;
import com.example.duoihinhbatchu_v2.UpdateDeleteActivity;
import com.example.duoihinhbatchu_v2.adapters.RecycleViewAdapter;
import com.example.duoihinhbatchu_v2.dao.SQLiteHelper;
import com.example.duoihinhbatchu_v2.models.ItemListener;
import com.example.duoihinhbatchu_v2.models.Riddle;

import java.util.List;

public class RiddlesFragment extends Fragment implements ItemListener {
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private SQLiteHelper sqLiteHelper;
    private TextView txtTong;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_riddles, container, false);
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
        this.recyclerView = view.findViewById(R.id.recycleViewId);
        this.txtTong = view.findViewById(R.id.txtTongId);
        this.adapter = new RecycleViewAdapter();
        this.sqLiteHelper = new SQLiteHelper(requireContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(this.adapter);
    }
    @Override
    public void onItemClick(View view, int position) {
        Riddle riddle = this.adapter.getItem(position);
        Intent intent = new Intent(requireContext(), UpdateDeleteActivity.class);
        intent.putExtra("item", riddle);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.reRender();
    }

    private void reRender() {
        List<Riddle> riddles = sqLiteHelper.getAll();
        this.adapter.setList(riddles);
        this.txtTong.setText("Total: " + riddles.size());
    }
}
