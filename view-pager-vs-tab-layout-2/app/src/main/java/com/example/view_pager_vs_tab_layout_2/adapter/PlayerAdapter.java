package com.example.view_pager_vs_tab_layout_2.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.view_pager_vs_tab_layout_2.MainActivity;
import com.example.view_pager_vs_tab_layout_2.R;
import com.example.view_pager_vs_tab_layout_2.model.Player;
import com.example.view_pager_vs_tab_layout_2.model.PlayerItemListener;

import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    private List<Player> players;
    private PlayerItemListener playerItemListener;
    private MainActivity mainActivity;

    public PlayerAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.players = new ArrayList<>();
    }

    public void setPlayerItemListener(PlayerItemListener playerItemListener) {
        this.playerItemListener = playerItemListener;
    }

    public Player getItem(int position) {
        return this.players.get(position);
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void add(Player player) {
        this.players.add(player);
        this.mainActivity.players = this.players;
        notifyDataSetChanged();
    }

    public void update(int position, Player player) {
        this.players.set(position, player);
        this.mainActivity.players = this.players;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = this.players.get(position);

        holder.avatar.setImageResource(player.getAvatar());
        holder.name.setText(player.getName());
        holder.price.setText("" + player.getPrice());
        holder.description.setText(player.getDescription());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle("Thong bao xoa");
                builder.setMessage("Ban co chac chan xoa " + player.getName() + " nay khong?");
                builder.setIcon(R.drawable.ic_remove);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        players.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();
                        mainActivity.players = players;
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.players.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView avatar;
        TextView name, price, description;
        Button deleteBtn;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);

            this.initView(itemView);
            this.catchEvents();
        }

        private void catchEvents() {
            this.deleteBtn.setOnClickListener(this);
        }

        private void initView(View view) {
            this.avatar = view.findViewById(R.id.item_img);
            this.name = view.findViewById(R.id.item_name);
            this.price = view.findViewById(R.id.item_price);
            this.description = view.findViewById(R.id.item_desc);
            this.deleteBtn = view.findViewById(R.id.item_remove);

            this.deleteBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (playerItemListener != null) {
                playerItemListener.onPlayerItemClick(view, getAdapterPosition());
            }
        }
    }
}
