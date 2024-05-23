package com.demo.cafe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class CafeViewPagerAdapter extends FragmentStatePagerAdapter {
    private int numPage;

    public CafeViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numPage = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentCuli();
            case 1:
                return new FragmentMoka();
            default:
                return new FragmentRobusta();
        }
    }

    @Override
    public int getCount() {
        return numPage;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "CULI";
            case 1:
                return "MOKA";
            default:
                return "ROBUSTA";
        }
    }
}
