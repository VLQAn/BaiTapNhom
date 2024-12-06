package com.example.engjp_11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_OpenApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_open_app);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnOpen = findViewById(R.id.btn_open);
        btnOpen.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_OpenApp.this, Activity_TestLevel1.class);
            startActivity(intent);
        });

        TextView checkTxt1 = findViewById(R.id.checkedTextView);
        btnOpen.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_OpenApp.this, Activity_TestLevel1.class);
            startActivity(intent);
        });

        CheckedTextView checkTxt2 = findViewById(R.id.checkedTextView2);
        btnOpen.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_OpenApp.this, Activity_TestLevel1.class);
            startActivity(intent);
        });

        CheckedTextView checkTxt3 = findViewById(R.id.checkedTextView3);
        btnOpen.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_OpenApp.this, Activity_TestLevel1.class);
            startActivity(intent);
        });

        CheckedTextView checkTxt4 = findViewById(R.id.checkedTextView4);
        btnOpen.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_OpenApp.this, Activity_TestLevel1.class);
            startActivity(intent);
        });
    }
}