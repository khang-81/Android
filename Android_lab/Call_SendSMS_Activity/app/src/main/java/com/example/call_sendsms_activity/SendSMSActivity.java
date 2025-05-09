package com.example.call_sendsms_activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

public class SendSMSActivity extends AppCompatActivity {

    EditText edtsms;
    ImageButton btnsendsms;
    Button btnback2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_smsactivity);

        edtsms = findViewById(R.id.edtsms);
        btnsendsms = findViewById(R.id.btnsms);
        btnback2 = findViewById(R.id.btnback2);

        // Sự kiện nhấn nút "Send SMS"
        btnsendsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = edtsms.getText().toString().trim();
                if (!phoneNumber.isEmpty()) {
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));
                    startActivity(smsIntent); // Mở ứng dụng nhắn tin mặc định để gửi SMS
                } else {
                    Toast.makeText(SendSMSActivity.this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Sự kiện nhấn nút "Back"
        btnback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Quay lại màn hình chính
            }
        });
    }
}
