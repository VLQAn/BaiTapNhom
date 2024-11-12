package com.example.engjp_11;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class viewPagerAdapter extends FragmentStateAdapter {
    public viewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new VideoDetail();
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
