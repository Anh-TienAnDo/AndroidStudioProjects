package com.example.songmanager.fragment;

import android.annotation.SuppressLint;
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

import com.example.songmanager.ModifyActivity;
import com.example.songmanager.R;
import com.example.songmanager.adapter.SongAdapter;
import com.example.songmanager.entity.Song;
import com.example.songmanager.entity.SongListener;
import com.example.songmanager.helper.SongHelper;

import java.util.List;


public class FragmentLove extends Fragment implements SongListener {
    private RecyclerView recyclerView;
    private SongAdapter adapter;
    private SongHelper songHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_love_singer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new SongAdapter(getContext());
        songHelper = new SongHelper(getContext());
        List<Song> songList = songHelper.getAllSong();
        adapter.setSongList(songList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setSongListener(this);

    }

    @Override
    public void onSongClick(View view, int position) {
        Song song = adapter.getSongList().get(position);
        Intent intent = new Intent(getActivity(), ModifyActivity.class);
        intent.putExtra("song", song);
        startActivity(intent);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {
        super.onResume();
        List<Song> itemList = songHelper.getAllSong();
        adapter.setSongList(itemList);
        adapter.notifyDataSetChanged();
    }
}
