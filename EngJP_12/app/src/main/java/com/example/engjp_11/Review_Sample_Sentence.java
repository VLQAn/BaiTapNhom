package com.example.engjp_11;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Review_Sample_Sentence extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_review_sample_sentence);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Tìm ImageButton theo ID
        Button closeButton = findViewById(R.id.btn_review_sentence_close);
        // Tìm Button và EditText theo ID
        Button okButton = findViewById(R.id.button2);
        EditText editText = findViewById(R.id.editText);

        // Thiết lập TextWatcher cho EditText
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Không cần sử dụng
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Kiểm tra nếu EditText có dữ liệu thì đổi màu Button
                if (s.length() > 0) {
                    okButton.setBackgroundColor(Color.parseColor("#033495")); // Màu khi có dữ liệu
                    okButton.setTextColor(Color.parseColor("#ffffff"));
                } else {
                    okButton.setBackgroundColor(Color.parseColor("#9E9E9E")); // Màu mặc định khi không có dữ liệu
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Không cần sử dụng
            }
        });

        // Xử lý sự kiện khi nhấn nút "OK"
        okButton.setOnClickListener(v -> {
            String inputText = editText.getText().toString().trim();
            if ("It's a nice to meet you.".equals(inputText)) {
                // Lấy FrameLayout chứa view động
                FrameLayout dynamicContainer = findViewById(R.id.dynamic_container);
                dynamicContainer.removeAllViews();  // Xóa các view trước đó nếu cần

                // Inflate layout mới vào FrameLayout
                View view = getLayoutInflater().inflate(R.layout.correct_layout, dynamicContainer, false);
                dynamicContainer.addView(view);

                // Xử lý sự kiện cho nút Next
                Button nextButton = view.findViewById(R.id.next_button);
                nextButton.setOnClickListener(view1 -> {
                    // Chuyển sang CompletedTestActivity khi nhấn Next
                    Intent intent = new Intent(Review_Sample_Sentence.this, sample_sentence_completed_test.class);
                    startActivity(intent);
                    finish(); // Kết thúc Review_Vocabulary để không quay lại Activity cũ
                });
            } else {
                // Lấy FrameLayout chứa view động
                FrameLayout dynamicContainer = findViewById(R.id.dynamic_container);
                dynamicContainer.removeAllViews();  // Xóa các view trước đó nếu cần

                // Inflate layout mới vào FrameLayout
                View view = getLayoutInflater().inflate(R.layout.wrong_layout, dynamicContainer, false);
                dynamicContainer.addView(view);

                // Xử lý sự kiện cho nút Next
                Button nextButton = view.findViewById(R.id.next_button);
                nextButton.setOnClickListener(view1 -> {
                    // Chuyển sang CompletedTestActivity khi nhấn Next
                    Intent intent = new Intent(Review_Sample_Sentence.this, sample_sentence_completed_test.class);
                    startActivity(intent);
                    finish(); // Kết thúc Review_Vocabulary để không quay lại Activity cũ
                });
            }
        });


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