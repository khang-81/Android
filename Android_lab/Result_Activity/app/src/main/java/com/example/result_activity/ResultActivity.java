package com.example.result_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    EditText edtkq;
    Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        edtkq = findViewById(R.id.edtkq);
        btnback = findViewById(R.id.btnback);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("mybackage");

        int a = bundle.getInt("soa");
        int b = bundle.getInt("sob");

        String kq = "";

        if (a == 0 && b == 0) {
            kq = "Vô số nghiệm";
        } else if (a == 0 && b != 0) {
            kq = "Vô nghiệm";
        } else {
            DecimalFormat dcf = new DecimalFormat("0.##");
            kq = "x = " + dcf.format(-1.0 * b / a);
        }

        edtkq.setText(kq);

        // Xử lý nút trở về
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Quay lại MainActivity
            }
        });
    }
}
