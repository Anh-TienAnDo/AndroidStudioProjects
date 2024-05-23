package com.example.duoihinhbatchu_v2.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duoihinhbatchu_v2.MainActivity;
import com.example.duoihinhbatchu_v2.R;
import com.example.duoihinhbatchu_v2.models.Account;
import com.example.duoihinhbatchu_v2.services.MusicService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private TextView txtUser;
    private Button btnSocial, btnMessenge, btnPhone, btnPlayMusic, btnStopMusic;
    private Account account;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.initView(view);
        this.catchEvent();
    }

    private void initView(View view) {
        this.txtUser = view.findViewById(R.id.txtUser);
        this.btnSocial = view.findViewById(R.id.btnSocial);
        this.btnMessenge = view.findViewById(R.id.btnMessenge);
        this.btnPhone = view.findViewById(R.id.btnPhone);
        this.btnPlayMusic = view.findViewById(R.id.btnPlayMusic);
        this.btnStopMusic = view.findViewById(R.id.btnStopMusic);
        this.intent = new Intent(requireContext(), MusicService.class);

        this.mAuth = FirebaseAuth.getInstance();
        this.user = this.mAuth.getCurrentUser();
        String email = this.user.getEmail();
//        String username = user.getDisplayName();
//        String id = user.getUid();
//        String phone = user.getPhoneNumber();
//        account = new Account(id, username, phone, email);

        this.txtUser.setText(email);

    }

    private void catchEvent() {
        this.btnSocial.setOnClickListener(this);
        this.btnMessenge.setOnClickListener(this);
        this.btnPhone.setOnClickListener(this);
        this.btnPlayMusic.setOnClickListener(this);
        this.btnStopMusic.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id==R.id.btnSocial){
            Intent social = new Intent(Intent.ACTION_VIEW);
            social.setData(Uri.parse("https://www.youtube.com/@trinhthivananhtrinhblog6313"));
            startActivity(social);
        }else if(id==R.id.btnMessenge){
            Intent messenge = new Intent(Intent.ACTION_VIEW);
            messenge.setData(Uri.parse("sms:0912345678"));
            messenge.putExtra("sms_body", "GDT");
            startActivity(messenge);
        }else if(id==R.id.btnPhone){
            Intent phone = new Intent(Intent.ACTION_DIAL);
            phone.setData(Uri.parse("tel:0912345678"));
            startActivity(phone);
        }else if(id==R.id.btnPlayMusic){
            requireContext().startService(intent);
        }else if(id==R.id.btnStopMusic){
            requireContext().stopService(intent);
        }
    }


}
