package com.example.customlayout_homework2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private Activity context;
    private int resource;
    private ArrayList<Contact> contacts;

    public ContactAdapter(Activity context, int resource, ArrayList<Contact> contacts) {
        super(context, resource, contacts);
        this.context = context;
        this.resource = resource;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(resource, null);

        TextView txtName = convertView.findViewById(R.id.txt_name);
        TextView txtPhone = convertView.findViewById(R.id.txt_phone);
        ImageView imgCall = convertView.findViewById(R.id.img_call);
        ImageView imgSMS = convertView.findViewById(R.id.img_sms);
        ImageView imgInfo = convertView.findViewById(R.id.img_info);

        Contact contact = contacts.get(position);
        txtName.setText((position + 1) + "-" + contact.getName());
        txtPhone.setText(contact.getPhone());

        // Bạn có thể thêm chức năng cho các nút ở đây nếu muốn (Call/SMS/Info)
        return convertView;
    }
}
