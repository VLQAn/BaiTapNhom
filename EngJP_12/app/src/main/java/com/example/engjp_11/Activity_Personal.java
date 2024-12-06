package com.example.engjp_11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_Personal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView personalNameTextView = findViewById(R.id.persional_name);

        // Lấy email từ Intent
        String userEmail = getIntent().getStringExtra("userName");

        ImageButton btn_prevent = findViewById(R.id.persional_prevent);
        ImageView img = findViewById(R.id.img_persional);

        img.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_Personal.this, ActivityInfomation.class);
            startActivity(intent);
        });
        // Thiết lập OnClickListener cho ImageButton
        btn_prevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đóng Activity này khi ImageButton được nhấn
                finish();
            }
        });

        ImageButton btnSetting = findViewById(R.id.img_btn_persional_setting);
        btnSetting.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_Personal.this, Activity_Login.class);
            startActivity(intent);
        });

        if (userEmail == null) {
            // Cập nhật personal_name bằng email đã nhập
            personalNameTextView.setText("Username");
        }else personalNameTextView.setText(userEmail);
    }
}