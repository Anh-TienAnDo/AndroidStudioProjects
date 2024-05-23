package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.fragment.model.FragmentA;
import com.example.fragment.model.FragmentB;
import com.example.fragment.model.FragmentC;

public class MainActivity3 extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        this.initView();
    }

    private void initView() {
        this.fragmentManager = getSupportFragmentManager();
    }

    private void add(Fragment fragment, String tag, String name) {
        this.fragmentTransaction = this.fragmentManager.beginTransaction();
        this.fragmentTransaction.add(R.id.frame, fragment, tag);
        this.fragmentTransaction.addToBackStack(name);
        this.fragmentTransaction.commit();
    }

    private void remove(String tag) {
        this.fragmentTransaction = this.fragmentManager.beginTransaction();
        Fragment fragment = this.fragmentManager.findFragmentByTag(tag);

        if (fragment != null) {
            this.fragmentTransaction.remove(fragment);
            this.fragmentTransaction.commit();
        }

    }

    public void back(View view) {
        this.fragmentManager.popBackStack();
    }

    public void addA(View view) {
        FragmentA fragmentA = new FragmentA();
        this.add(fragmentA, "frag_a_tag", "fragmentA");
    }

    public void addB(View view) {
        FragmentB fragmentB = new FragmentB();
        this.add(fragmentB, "frag_b_tag", "fragmentB");
    }

    public void addC(View view) {
        FragmentC fragmentC = new FragmentC();
        this.add(fragmentC, "frag_c_tag", "fragmentC");
    }

    public void removeA(View view) {
        this.remove("frag_a_tag");
    }

    public void removeB(View view) {
        this.remove("frag_b_tag");
    }

    public void removeC(View view) {
        this.remove("frag_c_tag");
    }

    public void popA(View view) {
        this.fragmentManager.popBackStack("fragmentA", 0);
    }

    @Override
    public void onBackPressed() {
        if (this.fragmentManager.getBackStackEntryCount() > 0) {
            this.fragmentManager.popBackStack();
        }

        super.onBackPressed();
    }
}