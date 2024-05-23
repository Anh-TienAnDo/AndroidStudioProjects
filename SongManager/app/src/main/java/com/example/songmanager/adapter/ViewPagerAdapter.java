package com.example.songmanager.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.songmanager.fragment.FragmentHome;
import com.example.songmanager.fragment.FragmentLove;
import com.example.songmanager.fragment.FragmentSearch;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) return new FragmentHome();
//        if (position == 1) return new FragmentLove();
        return new FragmentSearch();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
