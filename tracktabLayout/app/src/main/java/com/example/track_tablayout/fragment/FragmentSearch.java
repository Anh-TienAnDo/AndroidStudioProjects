package com.example.track_tablayout.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.track_tablayout.R;
import com.example.track_tablayout.adapter.RecycleViewAdapter;
import com.example.track_tablayout.dao.SQLiteHelper;
import com.example.track_tablayout.model.Item;

import java.util.List;
import java.util.Set;

public class FragmentSearch extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private Button btnSearch;
    private SearchView searchView;
    private Spinner spAlbum, spType;
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

        List<Item> list = this.sqLiteHelper.getAll();
        this.reRender(list);

        this.catchEvent();
    }

    private void reRender(List<Item> list) {
        this.adapter.setList(list);
    }


    private void catchEvent() {
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Item> list = sqLiteHelper.getItemsByName(s);
                reRender(list);
                return true;
            }
        });

        this.btnSearch.setOnClickListener(this);

//        this.spAlbum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String selectedItem = spAlbum.getItemAtPosition(position).toString();
//
//                List<Item> list;
//                if (!selectedItem.equalsIgnoreCase("all")) {
//                    list = sqLiteHelper.getItemsByAlbum(selectedItem);
//                } else {
//                    list = sqLiteHelper.getAll();
//                }
//
//                reRender(list);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

//        this.spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String selectedItem = spType.getItemAtPosition(position).toString();
//
//                List<Item> list;
//                if (!selectedItem.equalsIgnoreCase("all")) {
//                    list = sqLiteHelper.getItemsByType(selectedItem);
//                } else {
//                    list = sqLiteHelper.getAll();
//                }
//
//                reRender(list);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
    }

    private void initView(View view) {
        this.recyclerView = view.findViewById(R.id.recycleView);
        this.btnSearch = view.findViewById(R.id.btnSearchId);
        this.searchView = view.findViewById(R.id.searchId);
        this.spAlbum = view.findViewById(R.id.spAlbumId);
//        this.spType = view.findViewById(R.id.spTypeId);

        this.addAllToSpinner(this.spAlbum, getResources().getStringArray(R.array.album));
//        this.addAllToSpinner(this.spType, getResources().getStringArray(R.array.type));

        this.adapter = new RecycleViewAdapter();
        this.sqLiteHelper = new SQLiteHelper(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(this.adapter);
    }

    private void addAllToSpinner(Spinner spinner, String[] originArray) {
        String[] arrayContainsAll = new String[originArray.length + 1];
        arrayContainsAll[0] = "All";
        for (int i = 0; i < originArray.length; i++) {
            arrayContainsAll[i + 1] = originArray[i];
        }
        spinner.setAdapter(new ArrayAdapter<>(getContext(), R.layout.item_spinner, arrayContainsAll));
    }

    @Override
    public void onClick(View view) {
        if (view == this.btnSearch) {

        }
    }
}
