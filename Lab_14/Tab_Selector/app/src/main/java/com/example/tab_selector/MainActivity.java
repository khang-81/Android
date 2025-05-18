package com.example.tab_selector;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends Activity {

    EditText edta, edtb;
    Button btncong;
    ListView lv1;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inflate layout cho từng tab
        LayoutInflater inflater = getLayoutInflater();
        inflater.inflate(R.layout.tab1, findViewById(R.id.tab1), true);
        inflater.inflate(R.layout.tab2, findViewById(R.id.tab2), true);

        addControl();
        addEvent();
    }

    private void addControl() {
        TabHost tab = findViewById(R.id.tabhost);
        tab.setup();

        // Tab 1
        TabHost.TabSpec spec1 = tab.newTabSpec("tab1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("", ContextCompat.getDrawable(this, R.drawable.cong));
        tab.addTab(spec1);

        // Tab 2
        TabHost.TabSpec spec2 = tab.newTabSpec("tab2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("", ContextCompat.getDrawable(this, R.drawable.lichsu));
        tab.addTab(spec2);

        // Ánh xạ view
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        btncong = findViewById(R.id.btncong);
        lv1 = findViewById(R.id.lv1);

        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv1.setAdapter(adapter);
    }

    private void addEvent() {
        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int a = Integer.parseInt(edta.getText().toString());
                    int b = Integer.parseInt(edtb.getText().toString());
                    String result = a + " + " + b + " = " + (a + b);
                    list.add(result);
                    adapter.notifyDataSetChanged();
                    edta.setText("");
                    edtb.setText("");
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
