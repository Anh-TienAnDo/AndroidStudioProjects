package com.demo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.demo.fragment.FragmentCafe;
import com.demo.fragment.FragmentHome;
import com.demo.fragment.FragmentNotification;
import com.demo.fragment.FragmentSearch;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int pageNum;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        this.pageNum = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new FragmentNotification();
            case 3:
                return new FragmentSearch();
            case 4:
                return new FragmentCafe();
            default:
                return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return pageNum;
    }
}
