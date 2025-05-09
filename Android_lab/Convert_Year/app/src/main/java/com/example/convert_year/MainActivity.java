package com.example.convert_year;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editTextYear;
    Button buttonConvert;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextYear = findViewById(R.id.editTextYear);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yearInput = editTextYear.getText().toString();
                if (!yearInput.isEmpty()) {
                    int year = Integer.parseInt(yearInput);
                    String lunarYear = convertToLunarYear(year);
                    textViewResult.setText("Năm Âm Lịch: " + lunarYear);
                }
            }
        });
    }

    // Chuyển năm Dương lịch thành năm Âm lịch
    private String convertToLunarYear(int year) {
        String[] can = {"Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ", "Canh", "Tân", "Nhâm", "Quý"};
        String[] chi = {"Tý", "Sửu", "Dần", "Mão", "Thìn", "Tỵ", "Ngọ", "Mùi", "Thân", "Dậu", "Tuất", "Hợi"};

        int canIndex = (year - 3) % 10; // Can bắt đầu từ Giáp
        int chiIndex = (year - 3) % 12; // Chi bắt đầu từ Tý

        return can[canIndex] + " " + chi[chiIndex];
    }
}
