package com.example.btgk;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.Toast;

public class VideoPlayerActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        videoView = findViewById(R.id.videoView);

        // Lấy URL video từ Intent
        String videoUrl = getIntent().getStringExtra("videoUrl");
        if (videoUrl != null) {
            Uri videoUri = Uri.parse(videoUrl);
            videoView.setVideoURI(videoUri);


            MediaController mediaController = new MediaController(this);
            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);

            videoView.setOnPreparedListener(mp -> videoView.start());
        } else {
            Toast.makeText(this, "URL video không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }
}
