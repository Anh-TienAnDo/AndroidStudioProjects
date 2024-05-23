package com.track_management.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.track_management.fragment.FragmentHistory;
import com.track_management.fragment.FragmentHome;
import com.track_management.fragment.FragmentSearch;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
//    xác định số lượng Fragment trong ViewPager.
    private final int FRAGMENT_NUMBER = 3;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new FragmentHistory();
            case 2:
                return new FragmentSearch();
            default:
                return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return this.FRAGMENT_NUMBER;
    }
}
