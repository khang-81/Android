package com.example.result_activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edta, edtb;
    Button btnkq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        btnkq = findViewById(R.id.btnkq);

        btnkq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a, b;
                try {
                    a = Integer.parseInt(edta.getText().toString());
                    b = Integer.parseInt(edtb.getText().toString());
                } catch (NumberFormatException e) {
                    edta.setError("Vui lòng nhập số hợp lệ");
                    edtb.setError("Vui lòng nhập số hợp lệ");
                    return;
                }

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("soa", a);
                bundle.putInt("sob", b);
                intent.putExtra("mybackage", bundle);
                startActivity(intent);
            }
        });
    }
}
