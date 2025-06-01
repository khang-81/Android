package com.example.internetworking;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.internetworking.PizzaXmlParser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView pizzaListView = findViewById(R.id.pizzaListView);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        // Thực hiện phân tích XML và hiển thị dữ liệu
        new PizzaXmlParser(pizzaListView, progressBar).execute();
    }
}