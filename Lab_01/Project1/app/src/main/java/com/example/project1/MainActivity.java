package com.example.project1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtA, edtB, edtKQ;
    Button btncong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ ID
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtKQ = findViewById(R.id.edtKQ);
        btncong = findViewById(R.id.btntong);

        // Xử lý sự kiện nút Tổng
        btncong.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String strA = edtA.getText().toString().trim();
                String strB = edtB.getText().toString().trim();

                // Kiểm tra dữ liệu có bị rỗng không
                if (strA.isEmpty() || strB.isEmpty()) {
                    edtKQ.setText("Vui lòng nhập đủ A và B");
                    return;
                }

                try {
                    int a = Integer.parseInt(strA);
                    int b = Integer.parseInt(strB);
                    int tong = a + b;
                    edtKQ.setText(String.valueOf(tong));
                } catch (NumberFormatException e) {
                    edtKQ.setText("Sai định dạng số");
                }
            }
        });
    }
}
