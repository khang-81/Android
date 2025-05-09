package com.example.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;  // Thêm dòng này

public class MainActivity extends AppCompatActivity {
    Button btnChandoan;
    EditText editTen, editChieucao, editCannang, editBMI, editChandoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChandoan = findViewById(R.id.btnBMI);
        editTen = findViewById(R.id.edtten);
        editChieucao = findViewById(R.id.edtchieucao);
        editCannang = findViewById(R.id.edtcannang);
        editBMI = findViewById(R.id.edtBMI);
        editChandoan = findViewById(R.id.edtChuanDoan);

        btnChandoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double H = Double.parseDouble(editChieucao.getText().toString());
                    double W = Double.parseDouble(editCannang.getText().toString());
                    double BMI = W / Math.pow(H, 2);

                    String chandoan = "";
                    if (BMI < 18) {
                        chandoan = "Bạn gầy";
                    } else if (BMI <= 24.9) {
                        chandoan = "Bạn bình thường";
                    } else if (BMI <= 29.9) {
                        chandoan = "Bạn béo phì độ 1";
                    } else if (BMI <= 34.9) {
                        chandoan = "Bạn béo phì độ 2";
                    } else {
                        chandoan = "Bạn béo phì độ 3";
                    }

                    // Định dạng kết quả BMI và chẩn đoán
                    DecimalFormat dcf = new DecimalFormat("#.0");
                    editBMI.setText(dcf.format(BMI));
                    editChandoan.setText(chandoan);

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Nhập sai định dạng!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
