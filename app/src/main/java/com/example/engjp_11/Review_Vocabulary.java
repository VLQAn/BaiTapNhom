package com.example.engjp_11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
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
        setContentView(R.layout.activity_review_vocabulary);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom);
            return insets;
        });

        // Tạo danh sách button
        List<String> buttonList = Arrays.asList("Serendipity", "Random");

        // Thiết lập RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rcv_choose);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Gán adapter vào RecyclerView với OnItemClickListener
        ButtonAdapter adapter = new ButtonAdapter(this, buttonList, this::showCorrectView);
        recyclerView.setAdapter(adapter);

        // Tìm Button đóng Activity
        Button closeButton = findViewById(R.id.btn_review_vocab_close);
        closeButton.setOnClickListener(v -> finish());
    }

    private void showCorrectView(String item) {
        // Kiểm tra nếu item là "Serendipity" thì mới hiển thị correct_layout
        if ("Serendipity".equals(item)) {
            // Lấy FrameLayout chứa view động
            FrameLayout dynamicContainer = findViewById(R.id.dynamic_container);
            dynamicContainer.removeAllViews();  // Xóa các view trước đó nếu cần

            // Inflate layout mới vào FrameLayout
            View view = getLayoutInflater().inflate(R.layout.correct_layout, dynamicContainer, false);
            dynamicContainer.addView(view);

            // Xử lý sự kiện cho nút Next
            Button nextButton = view.findViewById(R.id.next_button);
            nextButton.setOnClickListener(v -> {
                // Chuyển sang CompletedTestActivity khi nhấn Next
                Intent intent = new Intent(Review_Vocabulary.this, vocabulary_completed_test.class);
                startActivity(intent);
                finish(); // Kết thúc Review_Vocabulary để không quay lại Activity cũ
            });
        }else {
            // Lấy FrameLayout chứa view động
            FrameLayout dynamicContainer = findViewById(R.id.dynamic_container);
            dynamicContainer.removeAllViews();  // Xóa các view trước đó nếu cần

            // Inflate layout mới vào FrameLayout
            View view = getLayoutInflater().inflate(R.layout.wrong_layout, dynamicContainer, false);
            dynamicContainer.addView(view);

            // Xử lý sự kiện cho nút Next
            Button nextButton = view.findViewById(R.id.next_button);
            nextButton.setOnClickListener(v -> {
                // Chuyển sang CompletedTestActivity khi nhấn Next
                Intent intent = new Intent(Review_Vocabulary.this, vocabulary_completed_test.class);
                startActivity(intent);
                finish(); // Kết thúc Review_Vocabulary để không quay lại Activity cũ
            });
        }
    }
}
