package com.uit.tahitu.hci.smarthospital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.uit.tahitu.hci.smarthospital.customView.CustomEditText;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HieuMinh on 10/27/2017.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_btn_back)
    ImageButton btnBack;

    @BindView(R.id.login_btn_login)
    Button btnLogin;

    @BindView(R.id.login_edt_email)
    CustomEditText edtEmail;

    @BindView(R.id.login_edt_password)
    CustomEditText edtPassword;

    @BindView(R.id.login_checkbox)
    CheckBox cbRemember;

    @BindView(R.id.login_tv_sign_up_here)
    TextView tvSignUpHere;

    @BindView(R.id.intro_tv_forgot)
    TextView mTvForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
        
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckLogin()){
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Sai tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        tvSignUpHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        
    }
    
    private boolean CheckLogin(){
        return Objects.equals(edtEmail.getText().toString(), "hieuit275@gmail.com") && (Objects.equals(edtPassword.getText().toString().trim(), "123456"));
    }
}
