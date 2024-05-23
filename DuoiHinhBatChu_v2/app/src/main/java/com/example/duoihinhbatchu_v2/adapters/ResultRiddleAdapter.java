package com.example.duoihinhbatchu_v2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.duoihinhbatchu_v2.R;

import java.util.ArrayList;
import java.util.List;

public class ResultRiddleAdapter extends ArrayAdapter<String> {
    private Context my_context;
    private ArrayList<String> arr;
    public ResultRiddleAdapter(@NonNull Context context, int resource, @NonNull List<String> result_riddle) {
        super(context, resource, result_riddle);
        this.my_context = context;
        this.arr = new ArrayList<>(result_riddle);
    }
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent){
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) my_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_text, null, true);
        }
        TextView txvAnwser = view.findViewById(R.id.txvAnwserId);
        txvAnwser.setText(this.arr.get(position));
        return view;
    }
}
