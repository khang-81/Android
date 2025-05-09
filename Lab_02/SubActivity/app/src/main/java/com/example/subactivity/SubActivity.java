package com.example.subactivity;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {
    EditText edtAA, edtBB;
    Button btnSendTong, btnSendHieu;
    int a, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        edtAA = findViewById(R.id.edtAA);
        edtBB = findViewById(R.id.edtBB);
        btnSendTong = findViewById(R.id.btnSendTong);
        btnSendHieu = findViewById(R.id.btnSendHieu);

        Intent intent = getIntent();
        a = intent.getIntExtra("soa", 0);
        b = intent.getIntExtra("sob", 0);

        edtAA.setText(String.valueOf(a));
        edtBB.setText(String.valueOf(b));

        btnSendTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sum = a + b;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("kq", sum);
                setResult(33, resultIntent);
                finish();
            }
        });

        btnSendHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sub = a - b;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("kq", sub);
                setResult(34, resultIntent);
                finish();
            }
        });
    }
}
