package com.example.songmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.songmanager.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) navigationView.getMenu().findItem(R.id.menuList).setChecked(true);
//                if (position == 1) navigationView.getMenu().findItem(R.id.menuLoveSong).setChecked(true);
                if (position == 1) navigationView.getMenu().findItem(R.id.menuSearch).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigationView.setOnNavigationItemSelectedListener(menu -> {
            if (menu.getItemId() == R.id.menuList) viewPager.setCurrentItem(0);
//            if (menu.getItemId() == R.id.menuLoveSong) viewPager.setCurrentItem(1);
            if (menu.getItemId() == R.id.menuSearch) viewPager.setCurrentItem(1);
            return true;
        });
    }

    private void initView(){
        fab = findViewById(R.id.fab);
        navigationView = findViewById(R.id.bottom_nav);
        viewPager = findViewById(R.id.viewPager);
    }
}