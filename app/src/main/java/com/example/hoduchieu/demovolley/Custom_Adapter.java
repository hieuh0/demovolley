package com.example.hoduchieu.demovolley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hoduchieu on 11/25/16.
 */

public class Custom_Adapter extends BaseAdapter{
    Context context;
    List<SinhVien> list;

    public Custom_Adapter(Context context,List<SinhVien> list){

        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.custom,null);
        TextView id = (TextView) view.findViewById(R.id.textView);
        TextView ten = (TextView) view.findViewById(R.id.textView2);
        TextView emai = (TextView) view.findViewById(R.id.textView3);

        SinhVien s = list.get(i);
        id.setText(s.getID()+"");
        ten.setText(s.getTen());
        emai.setText(s.getEmail());
        return view;
    }
}
