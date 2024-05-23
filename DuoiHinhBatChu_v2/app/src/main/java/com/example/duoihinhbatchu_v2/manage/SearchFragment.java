package com.example.duoihinhbatchu_v2.manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

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

public class SearchFragment extends Fragment implements View.OnClickListener, ItemListener {
    private RecyclerView recyclerView;
    private Button btnSearch;
    private SearchView searchView;
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
        this.catchEvent();
    }

    private void initView(View view) {
        this.recyclerView = view.findViewById(R.id.recycleView);
        this.btnSearch = view.findViewById(R.id.btnSearchId);
        this.searchView = view.findViewById(R.id.searchId);

        this.adapter = new RecycleViewAdapter();
        this.sqLiteHelper = new SQLiteHelper(requireContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(this.adapter);
    }

    private void catchEvent() {
        this.adapter.setItemListener(this);
        this.btnSearch.setOnClickListener(this);

        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Riddle> riddles = sqLiteHelper.getItemsByName(s);
                reRender(riddles);
                return true;
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        List<Riddle> riddles = sqLiteHelper.getAll();
        this.reRender(riddles);
    }
    private void reRender(List<Riddle> riddles) {
        this.adapter.setList(riddles);
    }

    @Override
    public void onClick(View view) {
        if (view == this.btnSearch) {
            String s = this.searchView.getQuery().toString();
            List<Riddle> riddles = sqLiteHelper.getItemsByResult(s);
            reRender(riddles);
            if(riddles == null){
                Toast.makeText(requireContext(), "Not Found", Toast.LENGTH_SHORT);
            }
        }
    }
    @Override
    public void onItemClick(View view, int position) {
        Riddle riddle = this.adapter.getItem(position);
        Intent intent = new Intent(requireContext(), UpdateDeleteActivity.class);
        intent.putExtra("item", riddle);
        startActivity(intent);
    }

}
