package com.example.customlayout_homework1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String namephone[] = {"Điện thoại Realme", "Điện thoại SamSung", "Điện thoại IPhone",
            "Điện thoại Redmi", "Điện thoại Huawei", "Điện thoại Oppo"};
    int imagephone[] = {R.drawable.realme, R.drawable.samsung, R.drawable.ip,
            R.drawable.redmi, R.drawable.huawei, R.drawable.oppo};
    String giaban[] = {"5000000", "10000000", "20000000", "8000000", "8500000", "6000000"};

    ArrayList<phone> mylist;
    MyArrayAdapter myadapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();

        for (int i = 0; i < namephone.length; i++) {
            mylist.add(new phone(namephone[i], imagephone[i], giaban[i]));
        }

        myadapter = new MyArrayAdapter(this, R.layout.layout_listview, mylist);
        lv.setAdapter(myadapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myintent = new Intent(MainActivity.this, SubActivity.class);
                myintent.putExtra("name", namephone[position]);
                startActivity(myintent);
            }
        });
    }
}
