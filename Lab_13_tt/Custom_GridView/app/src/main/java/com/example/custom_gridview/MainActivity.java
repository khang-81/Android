package com.example.custom_gridview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String[] arrayName = {
            "Ảnh 1", "Ảnh 2", "Ảnh 3", "Ảnh 4", "Ảnh 5", "Ảnh 6",
            "Ảnh 7", "Ảnh 8", "Ảnh 9", "Ảnh 10", "Ảnh 11", "Ảnh 12"
    };

    public static int[] imageName = {
            R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_5, R.drawable.img_6, R.drawable.img_7, R.drawable.img_8,
            R.drawable.img_9, R.drawable.img_10, R.drawable.img_11, R.drawable.img_12
    };

    GridView gridViewDemo;
    MyArrayAdapter adapterDanhSach;
    ArrayList<Image> arrimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridViewDemo = findViewById(R.id.grid1);
        arrimage = new ArrayList<>();

        // Gắn dữ liệu vào danh sách
        for (int i = 0; i < imageName.length; i++) {
            Image myimage = new Image(imageName[i], arrayName[i]);
            arrimage.add(myimage);
        }

        // Khởi tạo và gán adapter
        adapterDanhSach = new MyArrayAdapter(MainActivity.this, R.layout.listitem, arrimage);
        gridViewDemo.setAdapter(adapterDanhSach);

        // Sự kiện click
        gridViewDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, subActivitty.class);
                Bundle bundle = new Bundle();
                bundle.putInt("TITLE", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
