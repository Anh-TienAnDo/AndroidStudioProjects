package com.example.myapplication.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class TechnologyAdapter extends ArrayAdapter<Technology> {

    private Context context; // truy cập thông tin app và system
    private Technology[] technologyArrayList; // save all data to show
    // Phương thức khởi tạo TechnologyAdapter
    public TechnologyAdapter(@NonNull Context context, Technology[] technologyArrayList) {
        super(context, R.layout.item_technology, technologyArrayList);
        this.context=context;
        this.technologyArrayList=technologyArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, View converView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        if(converView == null) {
            converView = inflater.inflate(R.layout.item_technology, null, false);
        }
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.img = (ImageView) converView.findViewById(R.id.img);
        viewHolder.name = (TextView) converView.findViewById(R.id.txvName);
        viewHolder.describe = (TextView) converView.findViewById(R.id.txvDescribe);
        viewHolder.sub = (TextView) converView.findViewById(R.id.txvSub);

        converView.setTag(viewHolder);

        ImageView img = ((ViewHolder)converView.getTag()).img;
        TextView name = ((ViewHolder)converView.getTag()).name;
        TextView describe = ((ViewHolder)converView.getTag()).describe;
        TextView sub = ((ViewHolder)converView.getTag()).sub;

        Technology tech = technologyArrayList[position]; // get data by position
        img.setImageResource(tech.getImg()); // đưa dữ liệu vào giao diện
        name.setText(tech.getName());
        describe.setText(tech.getDescribe());
        sub.setText(tech.getSub());

//        View view = inflater.inflate(R.layout.item_technology, null, true);
//        ImageView img = view.findViewById(R.id.img);
//        TextView name = view.findViewById(R.id.txvName);
//        TextView describe = view.findViewById(R.id.txvDescribe);
//        TextView sub = view.findViewById(R.id.txvSub);
//
//        Technology tech = technologyArrayList[position]; // get data by position
//        img.setImageResource(tech.getImg()); // đưa dữ liệu vào giao diện
//        name.setText(tech.getName());
//        describe.setText(tech.getDescribe());
//        sub.setText(tech.getSub());
//        return view; // trả về biến view để hiển thị
        return converView;

    }
    public Technology getItem(int position){
        return technologyArrayList[position];
    }

    static class ViewHolder{
        ImageView img ;
        TextView name ;
        TextView describe;
        TextView sub;
    }

}
