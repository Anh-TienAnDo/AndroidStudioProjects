package com.intent_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.intent_2.model.Account;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvUsername, tvPassword;
    private Button btCancel, btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.initView();
        this.btCancel.setOnClickListener(this);
        this.btRegister.setOnClickListener(this);
    }

    private void initView() {
        this.tvUsername = findViewById(R.id.txtUsername);
        this.tvPassword = findViewById(R.id.txtPassword);
        this.btCancel = findViewById(R.id.btnCancel);
        this.btRegister = findViewById(R.id.btnRegister);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                Account account = new Account(tvUsername.getText().toString(), tvPassword.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("data", account);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btnCancel:
                setResult(RESULT_CANCELED, null);
                finish();
                break;
        }
    }
}