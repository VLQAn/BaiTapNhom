package com.example.engjp_10;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity class for displaying video details.
 */
public class Activity_Video_Detail extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this activity
        setContentView(R.layout.activity_video_detail);

        // Tìm ListView từ layout
        listView = findViewById(R.id.listView);

        // Thiết lập adapter cho ListView (CustomAdapter là adapter tùy chỉnh của bạn)
        CustomAdapterVideo adapter = new CustomAdapterVideo(this); // Truyền context (this) vào CustomAdapter
        listView.setAdapter(adapter);
    }
}
