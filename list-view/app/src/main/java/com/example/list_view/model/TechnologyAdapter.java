package com.example.list_view.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.list_view.R;

public class TechnologyAdapter extends ArrayAdapter<Technology> {
    private Context context;
    private Technology[] technologies;

    public TechnologyAdapter(@NonNull Context context, Technology[] technologies) {
        super(context, R.layout.technology_item, technologies);
        this.context = context;
        this.technologies = technologies;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        View view = layoutInflater.inflate(R.layout.technology_item, null, true);
        ImageView img = view.findViewById(R.id.imgId);
        TextView name = view.findViewById(R.id.nameId);
        TextView sub = view.findViewById(R.id.subId);
        TextView desc = view.findViewById(R.id.descId);

        Technology technology = this.technologies[position];
        img.setImageResource(technology.getImg());
        name.setText(technology.getName());
        sub.setText(technology.getSub());
        desc.setText(technology.getDescription());

        return view;
    }

    public Technology getItem(int position) {
        return this.technologies[position];
    }
}
