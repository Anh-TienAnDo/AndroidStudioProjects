package com.example.duoihinhbatchu_v2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.duoihinhbatchu_v2.R;

public class SpinnerAdapter extends BaseAdapter {
    private int[] images = {R.drawable.vong_tuan_hoan, R.drawable.baovemoitruong_susongtruongton,
            R.drawable.langphi_ngheodoi, R.drawable.nonggian_nhansac,
            R.drawable.gieonhangatqua, R.drawable.lamnhieudieutot_duocnhieunguoiquy};
    private Context context;

    public SpinnerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.images.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View item = LayoutInflater.from(this.context).inflate(R.layout.image_spinner_item, viewGroup, false);
        ImageView img = item.findViewById(R.id.imgId);
        img.setImageResource(this.images[position]);
        return item;
    }
}
