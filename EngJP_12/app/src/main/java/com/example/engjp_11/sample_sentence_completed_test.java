package com.example.engjp_11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class sample_sentence_completed_test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sample_sentence_completed_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.act_completed_test), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        // Xử lý sự kiện cho nút check again
        Button checkAgain = findViewById(R.id.checkAgainButton);
        checkAgain.setOnClickListener(v -> {
            // Chuyển sang Review_Vocabulary khi nhấn check again
            Intent intent = new Intent(sample_sentence_completed_test.this, Review_Sample_Sentence.class);
            startActivity(intent);
            finish();
        });

        // Xử lý sự kiện cho nút finish
        Button finish = findViewById(R.id.finishButton);
        finish.setOnClickListener(v -> {
            // Chuyển sang Contain_Navigation khi nhấn finish
            Intent intent = new Intent(sample_sentence_completed_test.this, Contain_Navigation.class);
            startActivity(intent);
            finish();
        });
    }
}