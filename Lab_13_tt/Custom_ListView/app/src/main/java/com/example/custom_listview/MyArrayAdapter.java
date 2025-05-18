package com.example.custom_listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class MyArrayAdapter extends ArrayAdapter<Phone> {
    Activity context;
    int layoutId;
    ArrayList<Phone> mylist;

    public MyArrayAdapter(Activity context, int layoutId, ArrayList<Phone> mylist) {
        super(context, layoutId, mylist);
        this.context = context;
        this.layoutId = layoutId;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);


        Phone phone = mylist.get(position);


        ImageView img = convertView.findViewById(R.id.imgphone);
        TextView txt = convertView.findViewById(R.id.txtnamephone);


        img.setImageResource(phone.getImagephone());
        txt.setText(phone.getNamephone());

        return convertView;
    }
}
