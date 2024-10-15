package com.example.engjp_10;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Buttom Navigation Bar

        viewPager = findViewById(R.id.view_page);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Use FragmentStateAdapter with ViewPager2
        viewPagerAdapter vAdapter = new viewPagerAdapter(this);
        viewPager.setAdapter(vAdapter);

        // Đặt trang Home (position 2) làm trang mặc định
        viewPager.setCurrentItem(2, false);  // false để tránh animation

        // Đặt item "Home" trong BottomNavigationView được chọn mặc định
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Su kien luot mang hinh de duy chuyen trang
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Handle the page scrolling event here
            }

            @Override
            public void onPageSelected(int position) {
                // Handle the page selected event here
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.video).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.nhapmom).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.lop).setChecked(true);
                        break;
                    case 4:
                        bottomNavigationView.getMenu().findItem(R.id.review).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Handle changes in scroll state here
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.video) {
                    // Handle Home navigation
                    viewPager.setCurrentItem(0);
                    return true;
                } else if (itemId == R.id.nhapmom) {
                    // Handle Profile navigation
                    viewPager.setCurrentItem(1);
                    return true;
                } else if (itemId == R.id.home) {
                    viewPager.setCurrentItem(2);
                    // Handle Settings navigation
                    return true;
                }else if (itemId == R.id.lop) {
                    // Handle Settings navigation
                    viewPager.setCurrentItem(3);
                    return true;
                }else if (itemId == R.id.review) {
                    // Handle Settings navigation
                    viewPager.setCurrentItem(4);
                    return true;
                }
                return false;
            }
        });

        // Gọi trang cá nhân

        ImageView imgvPersional = findViewById(R.id.img_vw_persional);

        imgvPersional.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Activity_Personal.class);
            startActivity(intent);
        });

        // Gọi trang thong bao

        ImageView imgvNotice = findViewById(R.id.img_vw_notice);

        imgvNotice.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Activity_Notice.class);
            startActivity(intent);
        });

    }
}