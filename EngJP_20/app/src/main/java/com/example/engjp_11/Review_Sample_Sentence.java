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
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Review_Sample_Sentence extends AppCompatActivity {

    private int correctCount = 0; // Biến đếm số lần nhập đúng
    private int questionIndex = 0; // Biến đếm số câu hỏi
    private String[] questions = {
            "Thật vinh hạnh khi được gặp bạn.",
            "Kế hoạch cuối tuần của bạn là gì?",
            "Bạn thường làm gì vào ngày Chủ Nhật?",
            "Bạn có dự định đi đâu vào cuối tuần này không?",
            "Cuối tuần vui vẻ của bạn thường như thế nào?"
    };
    private String[] correctAnswers = {
            "It's a nice to meet you.",
            "What are your plans for the weekend?",
            "What do you usually do on Sundays?",
            "Do you have any plans to go anywhere this weekend?",
            "What does your happy weekend usually look like?"
    };

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
        Button okButton = findViewById(R.id.btn_sample_sententce_ok);
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
            if (correctAnswers[questionIndex].equals(inputText)) {
                correctCount++; // Tăng số lần nhập đúng

                // Hiển thị correct layout
                showLayout(R.layout.correct_layout);
            } else {
                // Hiển thị wrong layout
                showLayout(R.layout.wrong_layout);
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

    private void showLayout(int layoutId) {
        // Lấy FrameLayout chứa view động
        FrameLayout dynamicContainer = findViewById(R.id.dynamic_container);
        dynamicContainer.removeAllViews();  // Xóa các view trước đó nếu cần

        // Inflate layout mới vào FrameLayout
        View view = getLayoutInflater().inflate(layoutId, dynamicContainer, false);
        dynamicContainer.addView(view);

        // Xử lý sự kiện cho nút Next
        Button nextButton = view.findViewById(R.id.next_button);
        nextButton.setOnClickListener(view1 -> {
            if (questionIndex < questions.length - 1) {
                questionIndex++;
                updateQuestion();
            } else {
                // Chuyển sang CompletedTestActivity khi hết câu hỏi
                Intent intent = new Intent(Review_Sample_Sentence.this, sample_sentence_completed_test.class);
                intent.putExtra("correctCount", correctCount);
                startActivity(intent);
                finish(); // Kết thúc Review_Vocabulary để không quay lại Activity cũ
            }
        });
    }

    private void updateQuestion() {
        // Cập nhật câu hỏi và xóa nội dung EditText
        EditText editText = findViewById(R.id.editText);
        editText.setText("");

        // Cập nhật nội dung câu hỏi
        TextView questionText = findViewById(R.id.txt_title_sample_sentence_test);
        questionText.setText(questions[questionIndex]);

        // Cập nhật nội dung gợi ý
        TextView hintText = findViewById(R.id.txt_hint_sample_sentence_test);
        hintText.setText(getHintForQuestion(questionIndex));

        // Cập nhật số câu hỏi
        TextView countText = findViewById(R.id.txt_sample_sentence_test_count);
        countText.setText((questionIndex + 1) + "/5");

        // Reset giao diện FrameLayout
        FrameLayout dynamicContainer = findViewById(R.id.dynamic_container);
        dynamicContainer.removeAllViews();
    }

    // Phương thức trả về nội dung gợi ý cho từng câu hỏi
    private String getHintForQuestion(int index) {
        String[] hints = {
                "It’s a _______ to _____ _____.",
                "What are your ______ for the ______?",
                "What do you ______ do on ______?",
                "Do you have any ______ to go ______ this weekend?",
                "What does your ______ weekend usually ______ like?"
        };
        return hints[index];
    }

}