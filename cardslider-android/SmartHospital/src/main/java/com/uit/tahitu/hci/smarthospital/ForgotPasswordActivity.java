package com.uit.tahitu.hci.smarthospital;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HieuMinh on 10/28/2017.
 */

public class ForgotPasswordActivity  extends AppCompatActivity{

    @BindView(R.id.forgot_btn_back)
    ImageView btnBack;

    @BindView(R.id.forgot_btn_send)
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);
        ButterKnife.bind(this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
