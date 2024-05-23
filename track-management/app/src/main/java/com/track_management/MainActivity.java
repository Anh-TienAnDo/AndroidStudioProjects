package com.track_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.track_management.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
//    tham chiếu đến TabLayout trong layout.
    private TabLayout tabLayout;
//    tham chiếu đến BottomNavigationView trong layout
    private BottomNavigationView bottomNavigationView;
//    tham chiếu đến ViewPager trong layout.
//ViewPager được sử dụng để hiển thị các fragment khác nhau hoặc các view khác nhau bằng cách vuốt qua
    private ViewPager viewPager;
//    nút hành động nổi
    private FloatingActionButton floatingActionButton;

//    Phương thức onCreate() được gọi khi Activity được tạo ra
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        thiết lập layout cho Activity, sau đó gọi initView() và catchEvent() để khởi tạo các thành phần giao diện và bắt sự kiện.
        setContentView(R.layout.activity_main);

        this.initView();
        this.catchEvent();
    }

//    khởi tạo các thành phần giao diện, gán adapter cho ViewPager, cấu hình TabLayout và gắn icon cho từng tab
    private void initView() {
//        ánh xạ đến view để điều khiển các phàn tử
        this.tabLayout = findViewById(R.id.tabLayoutId);
        this.bottomNavigationView = findViewById(R.id.bottomNavigationViewId);
        this.viewPager = findViewById(R.id.viewPageId);
        this.floatingActionButton = findViewById(R.id.floatingActionButtonId);

//        khởi tạo Fragments
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        cung cấp các fragment để hiển thị trong ViewPager
        this.viewPager.setAdapter(adapter);

//        Liên kết TabLayout với ViewPager để đồng bộ hóa các tab với các trang của ViewPager.
        this.tabLayout.setupWithViewPager(viewPager);
//        Đặt biểu tượng (icon) cho tab
        this.tabLayout.getTabAt(0).setIcon(R.drawable.ic_today);
        this.tabLayout.getTabAt(1).setIcon(R.drawable.ic_history);
        this.tabLayout.getTabAt(2).setIcon(R.drawable.ic_statistic);
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
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.mHome).setChecked(true);
                        break;

                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.mHistory).setChecked(true);
                        break;

                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.mSearch).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        chọn các mục trong BottomNavigationView khi click
        this.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            Bắt sự kiện khi người dùng chọn các mục trong BottomNavigationView
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mHome:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.mHistory:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.mSearch:
                        viewPager.setCurrentItem(2);
                        break;
                }

                return true;
            }
        });
    }
}