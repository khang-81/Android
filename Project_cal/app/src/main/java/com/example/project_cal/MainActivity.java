package com.example.project_cal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    EditText edt1, edt2, edt3;
    Button btncong, btntru, btnnhan, btnchia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1 = findViewById(R.id.edta);
        edt2 = findViewById(R.id.edtb);
        edt3 = findViewById(R.id.edtc);

        btncong = findViewById(R.id.btncong);
        btntru = findViewById(R.id.btntru);
        btnnhan = findViewById(R.id.btnnhan);
        btnchia = findViewById(R.id.btnchia);

        btncong.setOnClickListener(v -> {
            int a = Integer.parseInt("0" + edt1.getText());
            int b = Integer.parseInt("0" + edt2.getText());
            edt3.setText("a + b = " + (a + b));
        });

        btntru.setOnClickListener(v -> {
            int a = Integer.parseInt("0" + edt1.getText());
            int b = Integer.parseInt("0" + edt2.getText());
            edt3.setText("a - b = " + (a - b));
        });

        btnnhan.setOnClickListener(v -> {
            int a = Integer.parseInt("0" + edt1.getText());
            int b = Integer.parseInt("0" + edt2.getText());
            edt3.setText("a * b = " + (a * b));
        });

        btnchia.setOnClickListener(v -> {
            int a = Integer.parseInt("0" + edt1.getText());
            int b = Integer.parseInt("0" + edt2.getText());
            if (b == 0) {
                edt3.setText("B phải khác 0");
            } else {
                edt3.setText("a / b = " + (a / b));
            }
        });
    }
}
