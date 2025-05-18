package com.example.custom_listview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// ✅ Import đúng class Phone tự tạo trong project
import com.example.custom_listview.Phone;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Phone> mylist;
    MyArrayAdapter adapter;

    // Danh sách tên điện thoại
    String[] namephone = {
            "Điện thoại Huawei Mate XT",
            "Điện thoại iPhone 16 Pro Max",
            "Điện thoại Oppo Find N3",
            "Điện thoại Realme 11 Pro",
            "Điện thoại Samsung S25 Ultra",
            "Điện thoại Oppo Find X8"
    };

    // Danh sách ảnh (đảm bảo các ảnh này đã có trong res/drawable)
    int[] imagephone = {
            R.drawable.huawei_mate_xt,
            R.drawable.ip16prm,
            R.drawable.oppo_find_n3,
            R.drawable.realme_11pro,
            R.drawable.s25ultra,
            R.drawable.oppo_find_x8
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();

        // Tạo danh sách Phone
        for (int i = 0; i < namephone.length; i++) {
            mylist.add(new Phone(namephone[i], imagephone[i]));
        }

        // Gán adapter cho ListView
        adapter = new MyArrayAdapter(this, R.layout.layout_listview, mylist);
        lv.setAdapter(adapter);

        // Bắt sự kiện khi click item
        lv.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            intent.putExtra("name", namephone[position]);
            startActivity(intent);
        });
    }
}
