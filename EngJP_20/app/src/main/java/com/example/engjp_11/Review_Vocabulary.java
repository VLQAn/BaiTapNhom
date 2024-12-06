package com.example.engjp_11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class Review_Vocabulary extends AppCompatActivity {

    private int k = 0;
    // Tạo danh sách button
    List<String> buttonList1 = Arrays.asList("Serendipity", "Random");
    List<String> buttonList2 = Arrays.asList("Contentment", "Euphoria", "Cheer");
    List<String> buttonList3 = Arrays.asList("Jump", "Joy", "Journey");
    List<String> buttonList4 = Arrays.asList("Euphoric", "Ecstatic ", "Elated");
    List<String> buttonList5 = Arrays.asList("Satisfied", "Radiant", "Merry ");

    RecyclerView recyclerView;

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



        // Thiết lập RecyclerView
        recyclerView = findViewById(R.id.rcv_choose);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Gán adapter vào RecyclerView với OnItemClickListener
        ButtonAdapter adapter = new ButtonAdapter(this, buttonList1, this::showCorrectView);
        recyclerView.setAdapter(adapter);

        // Tìm Button đóng Activity
        Button closeButton = findViewById(R.id.btn_review_vocab_close);
        closeButton.setOnClickListener(v -> finish());

    }

    private void showCorrectView(String item) {
        FrameLayout dynamicContainer = findViewById(R.id.dynamic_container);
        dynamicContainer.removeAllViews(); // Xóa các view trước đó nếu cần

        // Nếu đúng, hiển thị đúng layout
        if ("Serendipity".equals(item)) {
            k+=1;
            showCorrectLayout("Sự mãn nguyện", "Sự mãn nguyện, hài lòng hoặc thỏa mãn với những gì mình đang có.", buttonList2);
        } else if ("Contentment".equals(item)) {
            k+=1;
            showCorrectLayout("Niềm vui", "Cảm giác hạnh phúc, vui sướng sâu sắc, thường là niềm vui thật sự xuất phát từ trái tim.", buttonList3);
        } else if ("Joy".equals(item)) {
            k+=1;
            showCorrectLayout("Phấn khởi", "Rất vui mừng hoặc rất hạnh phúc, thường là do một sự kiện tích cực hoặc thành công lớn.", buttonList4);
        } else if ("Elated".equals(item)) {
            k+=1;
            showCorrectLayout("Hài lòng", "Cảm thấy thoải mái hoặc hài lòng với điều gì đó vì nó đáp ứng được kỳ vọng hoặc nhu cầu.", buttonList5);
        } else if ("Satisfied".equals(item)) {
            k +=1;
            showCorrectLayout("Hài lòng", "Cảm thấy thoải mái hoặc hài lòng với điều gì đó vì nó đáp ứng được kỳ vọng hoặc nhu cầu.", null);
        } else {
            // Nếu sai, hiển thị wrong_layout và chuyển sang bước tiếp theo khi nhấn next
            showWrongLayout(item);
        }
    }

    private void showCorrectLayout(String title, String description, List<String> nextButtonList) {
        FrameLayout dynamicContainer = findViewById(R.id.dynamic_container);
        View view = getLayoutInflater().inflate(R.layout.correct_layout, dynamicContainer, false);
        dynamicContainer.addView(view);

        Button nextButton = view.findViewById(R.id.next_button);
        nextButton.setOnClickListener(v -> {
            if (nextButtonList != null) {
                updateRecyclerView(nextButtonList);
                updateNoiDung(title, description);
                dynamicContainer.removeView(view);
            }else {
                Intent intent = new Intent(Review_Vocabulary.this, vocabulary_completed_test.class);
                intent.putExtra("numbercorrect", k);
                startActivity(intent);
                finish(); // Kết thúc Review_Vocabulary để không quay lại Activity cũ
            }

        });
    }

    private void showWrongLayout(String currentItem) {
        FrameLayout dynamicContainer = findViewById(R.id.dynamic_container);
        View view = getLayoutInflater().inflate(R.layout.wrong_layout, dynamicContainer, false);
        dynamicContainer.addView(view);

        Button nextButton = view.findViewById(R.id.next_button);
        nextButton.setOnClickListener(v -> {
            switch (currentItem) {
                case "Random":
                    updateRecyclerView(buttonList2);
                    updateNoiDung("Sự mãn nguyện", "Sự mãn nguyện, hài lòng hoặc thỏa mãn với những gì mình đang có.");
                    break;

                case "Euphoria":
                    updateRecyclerView(buttonList3);
                    updateNoiDung("Niềm vui", "Cảm giác hạnh phúc, vui sướng sâu sắc, thường là niềm vui thật sự xuất phát từ trái tim.");
                    break;

                case "Cheer":
                    updateRecyclerView(buttonList3);
                    updateNoiDung("Niềm vui", "Cảm giác hạnh phúc, vui sướng sâu sắc, thường là niềm vui thật sự xuất phát từ trái tim.");
                    break;

                case "Jump":
                    updateRecyclerView(buttonList4);
                    updateNoiDung("Phấn khởi", "Rất vui mừng hoặc rất hạnh phúc, thường là do một sự kiện tích cực hoặc thành công lớn.");
                    break;

                case "Journey":
                    updateRecyclerView(buttonList4);
                    updateNoiDung("Phấn khởi", "Rất vui mừng hoặc rất hạnh phúc, thường là do một sự kiện tích cực hoặc thành công lớn.");
                    break;

                case "Euphoric":
                    updateRecyclerView(buttonList5);
                    updateNoiDung("Hài lòng", "Cảm thấy thoải mái hoặc hài lòng với điều gì đó vì nó đáp ứng được kỳ vọng hoặc nhu cầu.");
                    break;

                case "Ecstatic":
                    updateRecyclerView(buttonList5);
                    updateNoiDung("Hài lòng", "Cảm thấy thoải mái hoặc hài lòng với điều gì đó vì nó đáp ứng được kỳ vọng hoặc nhu cầu.");
                    break;

                case "Merry":
                    Intent intent1 = new Intent(Review_Vocabulary.this, vocabulary_completed_test.class);
                    startActivity(intent1);
                    finish(); // Kết thúc Review_Vocabulary để không quay lại Activity cũ
                    return; // Thoát để không tiếp tục xử lý các bước sau

                case "Radiant":
                    Intent intent2 = new Intent(Review_Vocabulary.this, vocabulary_completed_test.class);
                    startActivity(intent2);
                    finish(); // Kết thúc Review_Vocabulary để không quay lại Activity cũ
                    return; // Thoát để không tiếp tục xử lý các bước sau
            }
            dynamicContainer.removeView(view);
        });
    }

    private void updateRecyclerView(List<String> newButtonList) {
        ButtonAdapter adapter = new ButtonAdapter(this, newButtonList, this::showCorrectView);
        recyclerView.setAdapter(adapter);
    }

    private void updateNoiDung(String title, String description) {
        TextView socau = findViewById(R.id.socauhoanthanh);
        TextView txtTitle = findViewById(R.id.txtMeaning);
        TextView txtDecrip = findViewById(R.id.txtDecription);

        // Giả sử số câu hoàn thành theo từng bước
        int currentStep = Integer.parseInt(socau.getText().toString().split("/")[0]) + 1; // Cập nhật số câu
        socau.setText(currentStep + "/5");
        txtTitle.setText(title);
        txtDecrip.setText(description);
    }

}
