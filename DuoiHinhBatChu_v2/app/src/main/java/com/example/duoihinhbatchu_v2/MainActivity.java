package com.example.duoihinhbatchu_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.duoihinhbatchu_v2.adapters.ViewPagerAdapter;
import com.example.duoihinhbatchu_v2.fragments.AccountFragment;
import com.example.duoihinhbatchu_v2.fragments.HomeFragment;
import com.example.duoihinhbatchu_v2.models.Account;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    public Account account;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initView();
        this.catchEvent();

    }

    private void initView() {
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if(user == null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

        String email = user.getEmail();
        String username = user.getDisplayName();
        String id = user.getUid();
        String phone = user.getPhoneNumber();
        account = new Account(id, username, phone, email);

        this.tabLayout = findViewById(R.id.tabLayoutId);
        this.viewPager = findViewById(R.id.viewPageId);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.viewPager.setAdapter(adapter);
        this.tabLayout.setupWithViewPager(viewPager);
        this.tabLayout.getTabAt(0).setIcon(R.drawable.home);
        this.tabLayout.getTabAt(1).setIcon(R.drawable.playgame);
        this.tabLayout.getTabAt(2).setIcon(R.drawable.management);
        this.tabLayout.getTabAt(3).setIcon(R.drawable.user);

    }

    private void catchEvent() {

//        sự kiện khi trượt giữa các trang trên ViewPager
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void chuyen_sang_login() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}