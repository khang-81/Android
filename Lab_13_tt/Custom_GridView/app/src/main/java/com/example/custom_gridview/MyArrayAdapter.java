package com.example.custom_gridview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

// Tên class phải khớp với tên file: MyArrayAdapter.java
public class MyArrayAdapter extends ArrayAdapter<Image> {
    Activity context;
    int layoutId;
    ArrayList<Image> myArray;

    // Constructor nhận context, layout custom, và danh sách dữ liệu
    public MyArrayAdapter(Activity context, int layoutId, ArrayList<Image> arr) {
        super(context, layoutId, arr);
        this.context = context;
        this.layoutId = layoutId;
        this.myArray = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);

        Image myImage = myArray.get(position);

        ImageView imgItem = convertView.findViewById(R.id.imageView1);
        imgItem.setImageResource(myImage.getImg());

        TextView txtName = convertView.findViewById(R.id.textView1);
        txtName.setText(myImage.getName());

        return convertView;
    }
}
