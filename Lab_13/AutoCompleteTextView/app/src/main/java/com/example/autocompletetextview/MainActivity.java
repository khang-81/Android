package com.example.autocompletetextview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class MainActivity extends Activity {

    String[] provinces = {
            "Hà Nội", "Hà Giang", "Hà Nam", "Huế", "Hải Phòng", "Hòa Bình",
            "TP. Hồ Chí Minh", "Đà Nẵng", "Nghệ An", "Quảng Ninh", "Nam Định", "Ninh Bình"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtSelection = findViewById(R.id.selection);
        AutoCompleteTextView auto = findViewById(R.id.editauto);
        MultiAutoCompleteTextView multi = findViewById(R.id.multiAutoCompleteTextView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, provinces);

        auto.setAdapter(adapter);

        multi.setAdapter(adapter);
        multi.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
