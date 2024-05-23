package com.example.view_pager_vs_tab_layout_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.view_pager_vs_tab_layout_2.R;

public class SpinnerAdapter extends BaseAdapter {
    private Context context;
    private int[] images;

    public SpinnerAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return this.images.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View viewItem = LayoutInflater.from(this.context).inflate(R.layout.item_spinner, viewGroup, false);

        ImageView imageView = viewItem.findViewById(R.id.image);
        imageView.setImageResource(this.images[position]);

        return viewItem;
    }
}
