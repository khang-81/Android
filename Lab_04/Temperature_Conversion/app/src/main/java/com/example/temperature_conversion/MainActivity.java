package com.example.temperature_conversion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;  // Thêm dòng này
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText edtdoC, edtdoF;
    Button btncf, btnfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtdoC = findViewById(R.id.edtdoC);
        edtdoF = findViewById(R.id.edtdoF);
        btncf = findViewById(R.id.btncf);
        btnfc = findViewById(R.id.btnfc);

        DecimalFormat dcf = new DecimalFormat("#.00");

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (v == btncf) {
                        double C = Double.parseDouble(edtdoC.getText().toString());
                        double F = C * 1.8 + 32;
                        edtdoF.setText(dcf.format(F));
                    } else if (v == btnfc) {
                        double F = Double.parseDouble(edtdoF.getText().toString());
                        double C = (F - 32) / 1.8;
                        edtdoC.setText(dcf.format(C));
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Nhập sai định dạng!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btncf.setOnClickListener(listener);
        btnfc.setOnClickListener(listener);
    }
}
