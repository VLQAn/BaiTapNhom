package com.example.engjp_11;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class Review_Vocabulary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_review_vocabulary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Tạo danh sách button
        List<String> buttonList = Arrays.asList("Serendipity", "Random");

        // Thiết lập RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rcv_choose);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set LayoutManager để hiển thị danh sách button theo chiều ngang
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Gán adapter vào RecyclerView
        ButtonAdapter adapter = new ButtonAdapter(this, buttonList);
        recyclerView.setAdapter(adapter);

        // Tìm ImageButton theo ID
        Button closeButton = findViewById(R.id.btn_review_vocab_close);

        // Thiết lập OnClickListener cho ImageButton
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đóng Activity này khi ImageButton được nhấn
                finish();
            }
        });
    }
}