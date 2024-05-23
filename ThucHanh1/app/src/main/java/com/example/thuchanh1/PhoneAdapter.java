package com.example.thuchanh1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder> {

    private List<Phone> phoneList;
    private List<Phone> originalList;

    public PhoneAdapter(List<Phone> phoneList) {
        this.phoneList = phoneList;
        this.originalList = new ArrayList<>(phoneList);
    }

    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phone, parent, false);

        return new PhoneViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
        Phone phone = phoneList.get(position);
        holder.name.setText(phone.getName());
        holder.price.setText(phone.getPrice());
        holder.image.setImageResource(phone.getDrawableId());
        holder.origin.setText(phone.getOrigin());
        holder.remove.setOnClickListener(v -> {
            phoneList.remove(holder.getAdapterPosition());
            originalList.remove(holder.getAdapterPosition());
            notifyDataSetChanged();
        });
        holder.itemView.setOnClickListener(v -> ((MainActivity) v.getContext()).setInputFromAdapter(phone, holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return phoneList.size();
    }

    public static class PhoneViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView price;
        public TextView remove;
        public TextView origin;
        public ImageView image;

        public PhoneViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tvName);
            price = view.findViewById(R.id.tvPrice);
            remove = view.findViewById(R.id.tvRemove);
            image = view.findViewById(R.id.imgName);
            origin = view.findViewById(R.id.tvOrigin);
        }
    }

    public void addPhone(Phone phone) {
        phoneList.add(phone);
        originalList.add(phone);
        notifyDataSetChanged();
    }

    public void updatePhone(int position, Phone phone) {
        phoneList.set(position, phone);
        originalList.set(position, phone);
        notifyDataSetChanged();
    }

    public void searchPhone(String text) {
        if (text.isEmpty()) {
            phoneList.clear();
            phoneList.addAll(originalList);
            notifyDataSetChanged();
            return;
        }
        List<Phone> filteredList = new ArrayList<>();
        for (Phone phone : originalList) {
            if (phone.getName().toLowerCase().contains(text.toLowerCase()) || phone.getPrice().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(phone);
            }
        }
        phoneList.clear();
        phoneList.addAll(filteredList);
        notifyDataSetChanged();
    }

}
