package com.example.duoihinhbatchu_v2.manage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.duoihinhbatchu_v2.fragments.AccountFragment;
import com.example.duoihinhbatchu_v2.fragments.HomeFragment;
import com.example.duoihinhbatchu_v2.fragments.ManageFragment;
import com.example.duoihinhbatchu_v2.fragments.PlayFragment;

public class ManageViewPagerAdapter extends FragmentStatePagerAdapter {
    private final int FRAGMENT_NUMBER = 4;

    public ManageViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new SearchFragment();
            default:
                return new RiddlesFragment();
        }
    }

    @Override
    public int getCount() {
        return this.FRAGMENT_NUMBER;
    }
}
