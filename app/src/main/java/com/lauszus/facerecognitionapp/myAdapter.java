package com.lauszus.facerecognitionapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class myAdapter extends BaseAdapter {
    Context context;
    ArrayList<SingleRow> singleRowArrayList;

    public myAdapter(Context context, ArrayList<SingleRow> singleRowArrayList) {
        this.context = context;
        this.singleRowArrayList = singleRowArrayList;
    }

    @Override
    public int getCount() {
        return singleRowArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return singleRowArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Most Important Method
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Getting the view
        LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inf.inflate(R.layout.singlerow,parent,false);

        TextView txtCourse= view.findViewById(R.id.txtCourse);
        TextView txttiming = view.findViewById(R.id.txttiming);

        //getting the data from the data source
        SingleRow s = singleRowArrayList.get(position);

        //loading the data to view
        txtCourse.setText(s.getcoursename());
        txttiming.setText(s.gettiming());

        return view;
    }
}
