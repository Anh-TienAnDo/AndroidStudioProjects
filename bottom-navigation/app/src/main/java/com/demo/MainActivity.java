package com.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.demo.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
//    nút trôi
    private FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ánh xạ
        viewPager = findViewById(R.id.viewPager);
        navigationView = findViewById(R.id.navigation);
        button = findViewById(R.id.fab);

//        bắt sự kiện khi bấm nút trôi
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Test", Toast.LENGTH_SHORT);
            }
        });

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), 5);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                System.out.println(position);
                switch (position) {
                    case 1:
                        navigationView.getMenu().findItem(R.id.mNoti).setCheckable(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.mSearch).setCheckable(true);
                        break;
                    case 4:
                        navigationView.getMenu().findItem(R.id.mCafe).setCheckable(true);
                        break;
                    default:
                        navigationView.getMenu().findItem(R.id.mHome).setCheckable(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mNoti:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.mSearch:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.mCafe:
                        viewPager.setCurrentItem(4);
                        break;
                    default:
                        viewPager.setCurrentItem(0);
                        break;
                }

                return true;
            }
        });
    }
}