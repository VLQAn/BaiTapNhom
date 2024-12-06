package com.example.engjp_11;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Activity_VideoDetail extends AppCompatActivity {

    private ViewPager2 viewPagerVideo;
    private ViewPager2 viewPagerSubLang;
    private ImageButton imageButtonMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewPagerVideo = findViewById(R.id.video_view_page);
        viewPagerSubLang = findViewById(R.id.sub_view_page);
        imageButtonMore = findViewById(R.id.imgbtnmore);

        // Use FragmentStateAdapter with ViewPager2
        viewPagerAdapter videoAdapter = new viewPagerAdapter(this);
        viewPagerVideo.setAdapter(videoAdapter);

        subLangAdapter subLangAdapter = new subLangAdapter(this);
        viewPagerSubLang.setAdapter(subLangAdapter);

        // Sự kiện click vào imageButtonMore để hiển thị BottomSheetDialog
        imageButtonMore.setOnClickListener(v -> showBottomSheetDialog());

    }

    private void showBottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View bottomSheetView = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog, null);

        bottomSheetDialog.setContentView(bottomSheetView);

        // Thiết lập sự kiện click cho các TextView trong BottomSheet
        bottomSheetView.findViewById(R.id.textView_origin_video).setOnClickListener(v -> {
            // URL của video YouTube
            String youtubeUrl = "https://www.youtube.com/watch?v=3AD5SC9Vawo"; // Thay bằng video của bạn

            // Tạo Intent để mở video
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
            intent.setPackage("com.google.android.youtube"); // Chỉ định mở bằng ứng dụng YouTube

            // Kiểm tra nếu ứng dụng YouTube có thể xử lý Intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                // Nếu không, mở bằng trình duyệt
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
                startActivity(webIntent);
            }
        });

        bottomSheetView.findViewById(R.id.textView7).setOnClickListener(v -> {
            // Xử lý sự kiện "Báo lỗi"
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.show();
    }
}