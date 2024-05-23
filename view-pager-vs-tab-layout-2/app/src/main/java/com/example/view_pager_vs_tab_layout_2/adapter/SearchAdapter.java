package com.example.view_pager_vs_tab_layout_2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view_pager_vs_tab_layout_2.R;
import com.example.view_pager_vs_tab_layout_2.model.Player;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private List<Player> players;

    public SearchAdapter() {
        players = new ArrayList<>();
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    public List<Player> getPlayers() {
        return players;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Player player = this.players.get(position);

        holder.avatar.setImageResource(player.getAvatar());
        holder.name.setText(player.getName());
        holder.price.setText(player.getPrice() + "");
        holder.description.setText(player.getDescription());
    }

    @Override
    public int getItemCount() {
        return this.players.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name, price, description;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            this.initView(itemView);
        }

        private void initView(View view) {
            this.avatar = view.findViewById(R.id.item_img);
            this.name = view.findViewById(R.id.item_name);
            this.price = view.findViewById(R.id.item_price);
            this.description = view.findViewById(R.id.item_desc);
        }
    }
}
