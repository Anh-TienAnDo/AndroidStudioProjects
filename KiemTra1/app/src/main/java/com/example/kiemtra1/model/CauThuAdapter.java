package com.example.kiemtra1.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kiemtra1.R;

import java.util.ArrayList;
import java.util.List;

public class CauThuAdapter extends RecyclerView.Adapter<CauThuAdapter.CatViewHolder>{

    private Context context;
    private List<CauThu> cats;
    private List<CauThu> backupCats;
    private CatItemListener catItemListener;

    public CauThuAdapter(Context context) {
        this.context = context;
        if (cats == null) {
            this.cats = new ArrayList<>();
            this.backupCats = new ArrayList<>();
        }
    }

    public CauThuAdapter(Context context, List<CauThu> cats) {
        this.context = context;
        this.cats = cats;
    }

    public void setClickListener(CatItemListener catItemListener) {
        this.catItemListener = catItemListener;
    }

    public List<CauThu> getCats() {
        return this.cats;
    }

    public List<CauThu> getBackupCats() {
        return this.backupCats;
    }

    //    search
    public void filterCats(List<CauThu> filteredCats) {
        this.cats = filteredCats;
        notifyDataSetChanged();
    }

    //    get to update
    public CauThu getCat(int position) {
        return this.cats.get(position);
    }

    //    select view
    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.item_work, parent, false);
        return new CatViewHolder(itemView);
    }

    //    show data theo view
    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        CauThu cat = this.cats.get(position);
        holder.cb1.setChecked(false);
        holder.cb2.setChecked(false);
        holder.cb3.setChecked(false);

        if (cat == null) return;

        if(cat.getGt().equals("man")){
            holder.imgIV.setImageResource(R.drawable.man);
        }
        else{
            holder.imgIV.setImageResource(R.drawable.woman);
        }

        holder.nameTV.setText(cat.getName());
        if(cat.getVt().indexOf("Hau ve")>=0){
            holder.cb1.setChecked(true);
        }
        if(cat.getVt().indexOf("Tien ve")>=0){
            holder.cb2.setChecked(true);
        }
        if(cat.getVt().indexOf("Tien dao")>=0){
            holder.cb3.setChecked(true);
        }
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thong bao xoa");
                builder.setMessage("Ban co chac muon xoa " + cat.getName() + " khong?");
                builder.setIcon(R.drawable.ic_launcher_background);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cats.remove(holder.getAdapterPosition());
                        backupCats.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();
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
        if (this.cats != null) {
            return this.cats.size();
        }
        return 0;
    }

    public void add(CauThu cat) {
        this.cats.add(cat);
        this.backupCats.add(cat);
        notifyDataSetChanged();
    }

    public void update(int position, CauThu cat) {
        this.cats.set(position, cat);
        this.backupCats.set(position, cat);
        notifyDataSetChanged(); // refreshing the RecycleView
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView catCV;
        private ImageView imgIV;
        private TextView nameTV;
        private CheckBox cb1, cb2,cb3;
        private Button deleteBtn;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);

            this.initView(itemView);

            itemView.setOnClickListener(this);
        }

        private void initView(View view) {
            this.catCV = view.findViewById(R.id.catCardViewId);
            this.imgIV = view.findViewById(R.id.imgId);
            this.nameTV = view.findViewById(R.id.nameId);
            this.cb1 = view.findViewById(R.id.cbhv);
            this.cb2 = view.findViewById(R.id.cbtv);
            this.cb3 = view.findViewById(R.id.cbtd);
            this.deleteBtn = view.findViewById(R.id.deleteBtnId);
        }

        @Override
        public void onClick(View view) {
            if (catItemListener != null) {
                catItemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface CatItemListener {
        void onItemClick(View view, int position);
    }
}
