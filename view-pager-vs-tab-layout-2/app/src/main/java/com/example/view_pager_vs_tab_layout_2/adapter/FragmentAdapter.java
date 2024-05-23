package com.example.view_pager_vs_tab_layout_2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.view_pager_vs_tab_layout_2.fragment.FragmentAdd;
import com.example.view_pager_vs_tab_layout_2.fragment.FragmentSearch;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private int numPage =2;

    public FragmentAdapter(@NonNull FragmentManager fm, int numPage) {
        super(fm, numPage);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:return new FragmentSearch();
            default:return new FragmentAdd();
        }
    }

    @Override
    public int getCount() {
        return this.numPage;
    }
}
