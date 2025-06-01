package com.example.vnexpressnet;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo dữ liệu mẫu
        List<NewsItem> newsItems = new ArrayList<>();
        newsItems.add(new NewsItem(
                "Phần Lan 0-1 Nga: Siêu phẩm của Miranchuk",
                "Bàn thẳng duy nhất của Miranchuk giúp Nga hạ Phần Lan ở lượt trận thứ hai bảng B Euro 2021."));
        newsItems.add(new NewsItem(
                "Thợ lặn thoát chết khi bị cá voi nuốt",
                "Cá voi lưng gù nuốt thợ lặn nhưng sau đó phải nhả ra bởi cổ họng của chúng quá nhỏ so với con người."));
        newsItems.add(new NewsItem(
                "Trung Quốc mỉa mai Mỹ tặng 80 lọ vaccine",
                "Trung Quốc chỉ trích Mỹ về việc tặng vaccine với số lượng nhỏ trong khi nhu cầu thế giới rất lớn."));

        // Thiết lập RecyclerView
        RecyclerView recyclerView = findViewById(R.id.newsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NewsAdapter(newsItems));
    }
}