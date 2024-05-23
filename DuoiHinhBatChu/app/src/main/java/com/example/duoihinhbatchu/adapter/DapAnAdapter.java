package com.example.duoihinhbatchu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duoihinhbatchu.R;

import java.util.ArrayList;
import java.util.List;

public class DapAnAdapter extends ArrayAdapter<String> {
    private Context my_context;
    private ArrayList<String> arr;
    public DapAnAdapter(@NonNull Context context, int resource, @NonNull List<String> datas) {
        super(context, resource, datas);
        this.my_context = context;
        this.arr = new ArrayList<>(datas);
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) my_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_text, null, true);
        }
        TextView txvAnwser = convertView.findViewById(R.id.txvAnwser);
        txvAnwser.setText(this.arr.get(position));
        return convertView;
    }
}
