package com.example.engjp_11;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class classViewPagerAdapter extends FragmentStateAdapter {
    public classViewPagerAdapter(@NonNull Class fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Class_Course();
            case 1:
                return new Introductory_Listening();
            default:
                return new Home();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
