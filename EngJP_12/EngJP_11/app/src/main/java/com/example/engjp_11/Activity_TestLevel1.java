package com.example.engjp_11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_TestLevel1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_level1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnOk = findViewById(R.id.btn_test_lv1_ok);
        btnOk.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_TestLevel1.this, Contain_Navigation.class);
            startActivity(intent);
        });

        FrameLayout btnSkip = findViewById(R.id.btn_test_lv1_skip);
        btnSkip.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_TestLevel1.this, Contain_Navigation.class);
            startActivity(intent);
        });
    }
}