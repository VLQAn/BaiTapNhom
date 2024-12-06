package com.example.engjp_11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activity_Personal extends AppCompatActivity {

    private TextView nameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal);

        nameTextView = findViewById(R.id.persional_name);
        ImageView img = findViewById(R.id.img_persional);

        img.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_Personal.this, ActivityInfomation.class);
            startActivity(intent);
        });

        // Nhận tên từ Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("EXTRA_NAME")) {
            String name = intent.getStringExtra("EXTRA_NAME");
            nameTextView.setText(name); // Hiển thị tên người dùng
        } else {
            nameTextView.setText("Tên không xác định");
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton btn_prevent = findViewById(R.id.persional_prevent);
        // Thiết lập OnClickListener cho ImageButton
        btn_prevent.setOnClickListener(v -> {
            Intent intentt = new Intent(Activity_Personal.this, Contain_Navigation.class);
            startActivity(intentt);
        });

        ImageButton btnSetting = findViewById(R.id.img_btn_persional_setting);
        btnSetting.setOnClickListener(v -> {
            Intent intent1 = new Intent(Activity_Personal.this, Activity_Login.class);
            startActivity(intent1);
        });
    }

}