package com.example.bgk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


// Tìm item ic_home trong menu
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View homeItem = menuView.getChildAt(2);  // index của item 'ic_home' (dựa vào vị trí)

// Thay đổi kích thước của ic_home
        homeItem.setScaleX(1.5f);  // Tăng kích thước chiều ngang
        homeItem.setScaleY(1.5f);  // Tăng kích thước chiều dọc

        // Mặc định hiển thị Fragment Home
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        // Xử lý sự kiện khi người dùng chọn item trên BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.menu_home) {
                    selectedFragment = new HomeFragment();
                } else if (item.getItemId() == R.id.menu_video) {
                    selectedFragment = new VideoFragment();
                } else if (item.getItemId() == R.id.menu_intro) {
                    selectedFragment = new IntroductionFragment();
                } else if (item.getItemId() == R.id.menu_class) {
                    selectedFragment = new ClassFragment();
                } else if (item.getItemId() == R.id.menu_review) {
                    selectedFragment = new ReviewFragment();
                }


                // Thay thế fragment hiện tại bằng fragment được chọn
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            }
        });
    }
}
