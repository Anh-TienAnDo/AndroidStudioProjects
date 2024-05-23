package com.example.track_tablayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.track_tablayout.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
//    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initView();
        this.catchEvent();
    }

    private void initView() {
        this.tabLayout = findViewById(R.id.tabLayoutId);
        this.viewPager = findViewById(R.id.viewPageId);
//        this.bottomNavigationView = findViewById(R.id.bottomNavigationViewId);
        this.floatingActionButton = findViewById(R.id.floatingActionButtonId);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.viewPager.setAdapter(adapter);

        this.tabLayout.setupWithViewPager(viewPager);
        this.tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        this.tabLayout.getTabAt(1).setIcon(android.R.drawable.btn_star);
        this.tabLayout.getTabAt(2).setIcon(android.R.drawable.ic_menu_search);
    }

    //    bắt các sự kiện, bao gồm sự kiện nhấn vào Floating Action Button và sự kiện khi trượt giữa các trang trên ViewPager hoặc chọn các mục trong BottomNavigationView.
    private void catchEvent() {
//        bắt các sự kiện khi nhấn vào Floating Action Button
        this.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            //            một Intent được tạo để chuyển đến Activity AddActivity.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
//                khởi chạy AddActivity.
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
//                switch (position) {
//                    case 0:
//                        bottomNavigationView.getMenu().findItem(R.id.mHome).setChecked(true);
//                        break;
//
//                    case 1:
//                        bottomNavigationView.getMenu().findItem(R.id.mHistory).setChecked(true);
//                        break;
//
//                    case 2:
//                        bottomNavigationView.getMenu().findItem(R.id.mSearch).setChecked(true);
//                        break;
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        chọn các mục trong BottomNavigationView khi click
//        this.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                if (id == R.id.mHome) {
//                    viewPager.setCurrentItem(0);
//                } else if (id == R.id.mHistory) {
//                    viewPager.setCurrentItem(1);
//                } else if (id == R.id.mSearch) {
//                    viewPager.setCurrentItem(2);
//                } else {
//                    viewPager.setCurrentItem(0);
//                }
//                return true;
//            }
//        });
    }
}