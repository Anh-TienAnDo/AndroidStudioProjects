package com.example.track_tablayout.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.track_tablayout.fragment.FragmentHistory;
import com.example.track_tablayout.fragment.FragmentHome;
import com.example.track_tablayout.fragment.FragmentSearch;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
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
