package com.example.engjp_11;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class vpContainNavigationAdapter extends FragmentStateAdapter {
    public vpContainNavigationAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Video();
            case 1:
                return new Introductory();
            case 2:
                return new Home();
            case 3:
                return new Class();
            case 4:
                return new Review();
            default:
                return new Home();

        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
