package com.intent_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.intent_2.model.Account;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvUsername, tvPassword;
    private Button btLogin, btRegister;
    private final static int REQUEST_CODE = 123;
    private Account registeredAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.initView();
        this.btLogin.setOnClickListener(this);
        this.btRegister.setOnClickListener(this);
    }

    private void initView() {
        this.tvUsername = findViewById(R.id.txtUsername);
        this.tvPassword = findViewById(R.id.txtPassword);
        this.btLogin = findViewById(R.id.btnLogin);
        this.btRegister = findViewById(R.id.btnRegister);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                Account loginAccount = new Account(tvUsername.getText().toString(), tvPassword.getText().toString());

                loginIntent.putExtra("loginAccount", loginAccount);
                loginIntent.putExtra("registeredAccount", registeredAccount);

                startActivity(loginIntent);
                break;
            case R.id.btnRegister:
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(registerIntent, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "The user canceled the account registration", Toast.LENGTH_LONG).show();
            } else {
                registeredAccount = (Account) data.getSerializableExtra("data");

                tvUsername.setText(registeredAccount.getUsername());
                tvPassword.setText(registeredAccount.getPassword());
            }
        } else {
            registeredAccount = null;
        }
    }
}