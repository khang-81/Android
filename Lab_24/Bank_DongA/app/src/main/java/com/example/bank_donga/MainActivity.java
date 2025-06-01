package com.example.bank_donga;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo dữ liệu với mã quốc kỳ
        List<ExchangeRate> exchangeRates = new ArrayList<>();
        exchangeRates.add(new ExchangeRate("USD", "23.510", "23.890", "24.10", "29.20", R.drawable.flag_us));
        exchangeRates.add(new ExchangeRate("EUR", "29.70", "30.10", "20.2", "20.6", R.drawable.flag_eu));
        exchangeRates.add(new ExchangeRate("GBP", "20.4", "20.59", "", "", R.drawable.flag_uk));
        exchangeRates.add(new ExchangeRate("JPY", "15.790", "16.080", "3.476.000", "3.526.000", R.drawable.flag_jp));
        exchangeRates.add(new ExchangeRate("AUD", "3.476.000", "3.526.000", "16.530", "17.030", R.drawable.flag_au));
        exchangeRates.add(new ExchangeRate("CHF", "16.860", "17.020", "630", "730", R.drawable.flag_ch));
        exchangeRates.add(new ExchangeRate("CAD", "690", "720", "", "", R.drawable.flag_ca));
        exchangeRates.add(new ExchangeRate("SGD", "23.300", "23.370", "", "", R.drawable.flag_sg));
        exchangeRates.add(new ExchangeRate("THB", "3.643.000", "3.643.000", "3.630.000", "3.646.000", R.drawable.flag_th));

        // Thêm tiêu đề bảng
        TableLayout tableLayout = findViewById(R.id.exchangeTable);
        addTableHeader(tableLayout);

        // Thêm dữ liệu vào bảng
        for (ExchangeRate rate : exchangeRates) {
            addTableRow(tableLayout, rate);
        }
    }

    private void addTableHeader(TableLayout tableLayout) {
        TableRow headerRow = new TableRow(this);
        headerRow.setBackgroundColor(getResources().getColor(R.color.purple_200));

        addHeaderCell(headerRow, "Loại");
        addHeaderCell(headerRow, "Quốc kỳ");
        addHeaderCell(headerRow, "Mua CK");
        addHeaderCell(headerRow, "Bán CK");
        addHeaderCell(headerRow, "Mua TM");
        addHeaderCell(headerRow, "Bán TM");

        tableLayout.addView(headerRow);
    }

    private void addHeaderCell(TableRow row, String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(8, 8, 8, 8);
        textView.setTextColor(getResources().getColor(android.R.color.white));
        textView.setTextSize(16);
        textView.setBackgroundColor(getResources().getColor(R.color.purple_500));
        row.addView(textView);
    }

    private void addTableRow(TableLayout tableLayout, ExchangeRate rate) {
        TableRow row = new TableRow(this);

        // Thêm cột tiền tệ
        addCell(row, rate.getCurrency());

        // Thêm cột quốc kỳ
        ImageView flagImageView = new ImageView(this);
        flagImageView.setImageResource(rate.getFlagResId());
        flagImageView.setPadding(8, 12, 8, 12);
        flagImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        flagImageView.setAdjustViewBounds(true);
        flagImageView.setMaxHeight(40);
        flagImageView.setMaxWidth(60);
        flagImageView.setBackgroundResource(R.drawable.cell_border);
        row.addView(flagImageView);

        // Thêm các cột tỉ giá
        addCell(row, rate.getBuyTransfer());
        addCell(row, rate.getSellTransfer());
        addCell(row, rate.getBuyCash());
        addCell(row, rate.getSellCash());

        tableLayout.addView(row);
    }

    private void addCell(TableRow row, String text) {
        TextView textView = new TextView(this);
        textView.setText(text.isEmpty() ? "-" : text);
        textView.setPadding(8, 12, 8, 12);
        textView.setTextSize(14);
        textView.setBackgroundResource(R.drawable.cell_border);
        row.addView(textView);
    }
}