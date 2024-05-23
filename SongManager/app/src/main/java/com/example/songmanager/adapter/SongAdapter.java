package com.example.songmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.songmanager.R;
import com.example.songmanager.entity.Song;
import com.example.songmanager.entity.SongListener;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private Context context;
    private List<Song> songList;

    private SongListener songListener;

    public SongAdapter(Context context) {
        this.context = context;
        this.songList = new ArrayList<>();
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
        notifyDataSetChanged();
    }

    public void setSongListener(SongListener songListener) {
        this.songListener = songListener;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_item, parent, false);
        SongViewHolder viewHolder = new SongViewHolder(view);
        view.setOnClickListener(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.tvName.setText(song.getName());
        holder.tvSinger.setText(song.getSinger());
        holder.tvAlbum.setText(song.getAlbum());
        holder.tvType.setText(song.getType());
        holder.ckLove.setChecked(song.isLove());

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName, tvSinger, tvAlbum, tvType;
        private CheckBox ckLove;

        public SongViewHolder(@NonNull View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvSinger = view.findViewById(R.id.tvSinger);
            tvAlbum = view.findViewById(R.id.tvAlbum);
            tvType = view.findViewById(R.id.tvType);
            ckLove = view.findViewById(R.id.ckLove);
        }


        @Override
        public void onClick(View view) {
            songListener.onSongClick(view, getAdapterPosition());
        }
    }
}
