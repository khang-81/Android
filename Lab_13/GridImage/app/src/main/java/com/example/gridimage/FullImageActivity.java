package com.example.gridimage;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class FullImageActivity extends Activity {

    ImageView imageView;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        imageView = findViewById(R.id.fullImage);
        btnBack = findViewById(R.id.btnBack);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int imageResId = extras.getInt("image");
            imageView.setImageResource(imageResId);
        }

        btnBack.setOnClickListener(v -> finish());
    }
}
