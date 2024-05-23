package com.example.songmanager.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.songmanager.R;
import com.example.songmanager.adapter.SongAdapter;
import com.example.songmanager.entity.Song;
import com.example.songmanager.helper.SongHelper;

import java.util.List;

public class FragmentSearch extends Fragment {
    private RecyclerView recyclerView;
    private TextView tvSum, tvCountry, tvBlues, tvRock, tvPop;
    private SearchView searchView;
    private SongAdapter songAdapter;
    private SongHelper songHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        songAdapter = new SongAdapter(getContext());
        songHelper = new SongHelper(getContext());
        List<Song> allSong = songHelper.getAllSong();
        songAdapter.setSongList(allSong);
        tvSum.setText("Tổng số bài hát: " + songAdapter.getSongList().size());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(songAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (!s.isEmpty()) {
                    List<Song> searchList = songHelper.getSongByName(s);
                    tvSum.setText("Tổng số bài hát: " + songAdapter.getSongList().size());
                    tvCountry.setText("Country: " + songHelper.getCountSongByType("Country") + " bài hát");
                    tvBlues.setText("Blues: " + songHelper.getCountSongByType("Blues") + " bài hát");
                    tvRock.setText("Rock: " + songHelper.getCountSongByType("Rock") + " bài hát");
                    tvPop.setText("Pop: " + songHelper.getCountSongByType("Pop") + " bài hát");

                    songAdapter.setSongList(searchList);
                }

                return true;
            }
        });
    }


    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        tvSum = view.findViewById(R.id.tvSum);
        tvBlues = view.findViewById(R.id.tvBlues);
        tvCountry = view.findViewById(R.id.tvCountry);
        tvRock = view.findViewById(R.id.tvRock);
        tvPop = view.findViewById(R.id.tvPop);

        searchView = view.findViewById(R.id.search);
        String[] albums = getResources().getStringArray(R.array.album);
        String[] newAlbums = new String[albums.length + 1];
        newAlbums[0] = "Tất cả";
        for (int i = 0; i < albums.length; i++) {
            newAlbums[i + 1] = albums[i];
        }

    }


}
