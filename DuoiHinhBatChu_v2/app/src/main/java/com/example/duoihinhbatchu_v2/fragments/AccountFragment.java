package com.example.duoihinhbatchu_v2.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duoihinhbatchu_v2.LoginActivity;
import com.example.duoihinhbatchu_v2.MainActivity;
import com.example.duoihinhbatchu_v2.R;
import com.example.duoihinhbatchu_v2.models.Account;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountFragment extends Fragment implements View.OnClickListener {
    private TextView txtUsername, txtEmail, txtPhone;
    private Button btnLogout;
    private Account account;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.initView(view);
        this.catchEvent();
    }

    private void initView(View view) {
        this.txtUsername = view.findViewById(R.id.txtUsername);
        this.txtEmail = view.findViewById(R.id.txtEmail);
        this.txtPhone = view.findViewById(R.id.txtPhone);
        this.btnLogout = view.findViewById(R.id.btnLogout);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        String email = user.getEmail();
        String username = user.getDisplayName();
        String id = user.getUid();
        String phone = user.getPhoneNumber();
        account = new Account(id, username, phone, email);
        txtUsername.setText(account.getUsername());
        txtEmail.setText(account.getEmail());
        txtPhone.setText(account.getPhone());
    }

    private void catchEvent() {
        this.btnLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id==R.id.btnLogout){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

}
