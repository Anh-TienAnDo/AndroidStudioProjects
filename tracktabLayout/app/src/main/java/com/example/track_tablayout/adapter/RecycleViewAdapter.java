package com.example.track_tablayout.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.track_tablayout.R;
import com.example.track_tablayout.model.Item;
import com.example.track_tablayout.model.ItemListener;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {
    private List<Item> items;
    private ItemListener itemListener;

    public RecycleViewAdapter() {
        this.items = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public Item getItem(int position) {
        return this.items.get(position);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Item item = this.getItem(position);

        holder.name.setText(item.getName());
        holder.singer.setText(item.getSinger());
        holder.album.setText(item.getAlbum());
        holder.type.setText(item.getType());

        if (item.isFavourite()) {
            holder.isFavourite.setText("Favourite");
        } else {
            holder.isFavourite.setText("Don't like");
        }
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, singer, album, type, isFavourite;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            this.initView(itemView);

            this.itemView.setOnClickListener(this);
        }

        private void initView(View view) {
            this.name = view.findViewById(R.id.tvNameId);
            this.singer = view.findViewById(R.id.tvSingerNameId);
            this.album = view.findViewById(R.id.tvAlbumId);
            this.type = view.findViewById(R.id.tvTypeId);
            this.isFavourite = view.findViewById(R.id.tvIsFavouriteId);
        }

        @Override
        public void onClick(View view) {
            if (itemListener != null) {
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
}
