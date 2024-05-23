package com.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button prevBtn, nextBtn;
    private FragmentManager fragmentManager;
    private FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initView();
        this.catchEvents();
    }

    private void catchEvents() {
//        trình lắng nghe sự kiện cho TabLayout
        this.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.pink));
//                        set background cho button
                        prevBtn.setBackgroundColor(getResources().getColor(R.color.pink));
                        nextBtn.setBackgroundColor(getResources().getColor(R.color.pink));
                        break;
                    case 1:
                        tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.green));
                        prevBtn.setBackgroundColor(getResources().getColor(R.color.green));
                        nextBtn.setBackgroundColor(getResources().getColor(R.color.green));
                        break;
                    case 2:
                        tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.blue));
                        prevBtn.setBackgroundColor(getResources().getColor(R.color.blue));
                        nextBtn.setBackgroundColor(getResources().getColor(R.color.blue));
                        break;
                }
            }

//            khi một tab đã được chọn và sau đó chuyển sang tab khác
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

//            khi người dùng chọn một tab đã được chọn trước đó một lần nữa
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        this.prevBtn.setOnClickListener(this);
        this.nextBtn.setOnClickListener(this);
    }

    private void initView() {
        this.viewPager = findViewById(R.id.viewPager);
        this.tabLayout = findViewById(R.id.tabLayout);
        this.prevBtn = findViewById(R.id.prevBtn);
        this.nextBtn = findViewById(R.id.nextBtn);

//        FragmentManager để quản lý các fragment trong activity.
//        Phương thức getSupportFragmentManager() được sử dụng trong AppCompatActivity để có được FragmentManager hỗ trợ.
        this.fragmentManager = getSupportFragmentManager();
//        được sử dụng để cung cấp các fragment cho ViewPager
        this.fragmentAdapter = new FragmentAdapter(this.fragmentManager, 3);

//        cấu hình trình chuyển trang
//        this.viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
//            private static final float MIN_SCALE = 0.75f;
//
//            @Override
//            public void transformPage(@NonNull View page, float position) {
//                if (position < -1) { // Trang đã được vuốt hết sang trái (nằm ngoài phạm vi hiển thị)
//                    page.setAlpha(0f);
//                } else if (position <= 0) { // Trang đang xuất hiện hoặc biến mất khi vuốt qua
//                    page.setAlpha(1f);
//                    page.setTranslationX(0f);
//                    page.setScaleX(1f);
//                    page.setScaleY(1f);
//                } else if (position <= 1) { // Trang sẽ được vuốt tới
//                    // Đặt alpha cho trang để xuất hiện mượt mà
//                    page.setAlpha(1 - position);
//
//                    // Xoay trang về phía dương 20 độ
//                    page.setRotation(20 * position);
//
//                    // Phóng to trang từ kích thước nhỏ nhất đến kích thước ban đầu
//                    float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
//                    page.setScaleX(scaleFactor);
//                    page.setScaleY(scaleFactor);
//                } else { // Trang đã được vuốt hết sang phải (nằm ngoài phạm vi hiển thị)
//                    page.setAlpha(0f);
//                }
//            }
//        });
//        Gắn FragmentAdapter với ViewPager để cung cấp dữ liệu cho ViewPager hiển thị.
        this.viewPager.setAdapter(this.fragmentAdapter);
//        liên kết ViewPager với TabLayout để đồng bộ hóa các tab với các trang của ViewPager.
        this.tabLayout.setupWithViewPager(this.viewPager);

//        thiết lập màu sắc cho tiêu đề của TabLayout tương ứng với trang hiện tại của ViewPager
        this.setTabLayoutTitleColor();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextBtn:
                if (this.viewPager.getCurrentItem() == 2) {
                    return;
                } else {
                    this.viewPager.setCurrentItem(this.viewPager.getCurrentItem() + 1);
                    this.setTabLayoutTitleColor();
                }

                break;

            case R.id.prevBtn:
                if (this.viewPager.getCurrentItem() == 0) {
                    return;
                } else {
                    this.viewPager.setCurrentItem(this.viewPager.getCurrentItem() - 1);
                    this.setTabLayoutTitleColor();
                }

                break;
        }
    }

//    thiết lập màu sắc cho tiêu đề của TabLayout tương ứng với trang hiện tại của ViewPager
    private void setTabLayoutTitleColor() {
//        lấy index fram hiện tại
        switch (this.viewPager.getCurrentItem()) {
            case 0:
//                Màu chữ được thiết lập thành màu đen, trong khi màu nền của tab sẽ được thiết lập thành màu hồng được xác định từ tài nguyên màu trong tệp colors.xml của ứng dụng.
                this.tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.pink));
                break;
            case 1:
                this.tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.green));
                break;
            case 2:
                this.tabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.blue));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (this.viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            this.viewPager.setCurrentItem(this.viewPager.getCurrentItem() - 1);
        }
    }
}