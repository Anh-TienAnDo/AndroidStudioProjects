package com.example.duoihinhbatchu_v2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.duoihinhbatchu_v2.AddActivity;
import com.example.duoihinhbatchu_v2.MainActivity;
import com.example.duoihinhbatchu_v2.R;
import com.example.duoihinhbatchu_v2.manage.ManageViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class ManageFragment extends Fragment {
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private FloatingActionButton floatingActionButton;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.initView(view);
        this.catchEvent();
    }

    private void initView(@NonNull View view) {
        this.bottomNavigationView = view.findViewById(R.id.bottomNavigationViewId);
        this.floatingActionButton = view.findViewById(R.id.floatingActionButtonId);
        this.viewPager = view.findViewById(R.id.viewPagerId);
        ManageViewPagerAdapter adapter = new ManageViewPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.viewPager.setAdapter(adapter);

    }

    private void catchEvent() {
        // bắt các sự kiện khi nhấn vào Floating Action Button
        this.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            // một Intent được tạo để chuyển đến Activity AddActivity.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), AddActivity.class);
                startActivity(intent);

            }
        });
//        sự kiện khi trượt giữa các trang trên ViewPager
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.mRiddles).setChecked(true);
                        break;

                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.mSearch).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        chọn các mục trong BottomNavigationView khi click
        this.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.mRiddles) {
                    viewPager.setCurrentItem(0);
                } else if (id == R.id.mSearch) {
                    viewPager.setCurrentItem(1);
                } else {
                    viewPager.setCurrentItem(0);
                }
                return true;
            }
        });
    }

}
