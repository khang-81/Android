package com.example.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChildActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        Button btn = findViewById(R.id.button1);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(ChildActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
