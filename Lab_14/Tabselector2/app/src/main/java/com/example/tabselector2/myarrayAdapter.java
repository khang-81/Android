package com.example.tabselector2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class myarrayAdapter extends ArrayAdapter<Item> {
    private final Activity context;
    private final ArrayList<Item> myArray;
    private final int layoutId;

    public myarrayAdapter(Activity context, int layoutId, ArrayList<Item> arr) {
        super(context, layoutId, arr);
        this.context = context;
        this.layoutId = layoutId;
        this.myArray = arr;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(layoutId, parent, false);
        }

        Item item = myArray.get(position);

        ImageView btnlike = convertView.findViewById(R.id.btnlike);
        if (item.getThich() == 1) {
            btnlike.setImageResource(R.drawable.like);
        } else {
            btnlike.setImageResource(R.drawable.unlike); // ⚠ phải có unlike trong drawable
        }

        TextView tieude = convertView.findViewById(R.id.txttieude);
        tieude.setText(item.getTieude());

        TextView maso = convertView.findViewById(R.id.txtmaso);
        maso.setText(item.getMaso());

        return convertView;
    }
}
