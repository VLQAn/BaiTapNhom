package com.example.engjp_11;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class subLangAdapter extends FragmentStateAdapter {

    public subLangAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Sub();
            case 1:
                return new VideoTestPractice();
            case 2:
                return new TestPractice();
//            case 3:
//                return new Class();
//            case 4:
//                return new Review();
            default:
                return new Sub();

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
