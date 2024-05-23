package com.example.duoihinhbatchu_v2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duoihinhbatchu_v2.R;
import com.example.duoihinhbatchu_v2.models.ItemListener;
import com.example.duoihinhbatchu_v2.models.Riddle;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder>{
    private int[] images = {R.drawable.vong_tuan_hoan, R.drawable.baovemoitruong_susongtruongton,
            R.drawable.langphi_ngheodoi, R.drawable.nonggian_nhansac,
            R.drawable.gieonhangatqua, R.drawable.lamnhieudieutot_duocnhieunguoiquy};
    private List<Riddle> items;
    private ItemListener itemListener;
    public RecycleViewAdapter() {
        this.items = new ArrayList<>();
    }
    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<Riddle> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public Riddle getItem(int position) {
        return this.items.get(position);
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.riddle, parent, false);

        return new HomeViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Riddle item = this.getItem(position);

        holder.nameId.setText(item.getName());
        holder.resultId.setText(item.getResult());
        for (int i = 0; i < images.length; i++) {
            if(item.getImage()==i){
                holder.imgId.setImageResource(images[i]);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (this.items != null) {
            return this.items.size();
        }
        return 0;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameId, resultId;
        private ImageView imgId;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            this.initView(itemView);

            this.itemView.setOnClickListener(this);
        }

        private void initView(View view) {
            this.nameId = view.findViewById(R.id.nameId);
            this.resultId = view.findViewById(R.id.resultId);
            this.imgId = view.findViewById(R.id.imgId);
        }

        @Override
        public void onClick(View view) {
            if (itemListener != null) {
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

}
