package com.example.solve_equation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button btnTieptuc, btnGiai, btnThoat;
    EditText edita, editb, editc;
    TextView txtkq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTieptuc = findViewById(R.id.btnTieptuc);
        btnGiai = findViewById(R.id.btnGiai);
        btnThoat = findViewById(R.id.btnThoat);
        edita = findViewById(R.id.edta);
        editb = findViewById(R.id.edtb);
        editc = findViewById(R.id.edtc);
        txtkq = findViewById(R.id.txtkq);

        btnGiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sa = edita.getText().toString();
                String sb = editb.getText().toString();
                String sc = editc.getText().toString();

                if (!sa.isEmpty() && !sb.isEmpty() && !sc.isEmpty()) {
                    int a = Integer.parseInt(sa);
                    int b = Integer.parseInt(sb);
                    int c = Integer.parseInt(sc);
                    String kq = solveQuadraticEquation(a, b, c);
                    txtkq.setText(kq);
                }
            }
        });

        btnTieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edita.setText("");
                editb.setText("");
                editc.setText("");
                edita.requestFocus();
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Giải phương trình bậc 2
    private String solveQuadraticEquation(int a, int b, int c) {
        DecimalFormat dcf = new DecimalFormat("0.00");
        String result = "";

        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    result = "PT vô số nghiệm";
                } else {
                    result = "PT vô nghiệm";
                }
            } else {
                result = "PT có 1 nghiệm x = " + dcf.format(-c / (float) b);
            }
        } else {
            double delta = b * b - 4 * a * c;
            if (delta < 0) {
                result = "PT vô nghiệm";
            } else if (delta == 0) {
                result = "PT có nghiệm kép x1 = x2 = " + dcf.format(-b / (2 * (float) a));
            } else {
                result = "PT có 2 nghiệm: x1 = " + dcf.format((-b + Math.sqrt(delta)) / (2 * (float) a)) +
                        "; x2 = " + dcf.format((-b - Math.sqrt(delta)) / (2 * (float) a));
            }
        }
        return result;
    }
}
