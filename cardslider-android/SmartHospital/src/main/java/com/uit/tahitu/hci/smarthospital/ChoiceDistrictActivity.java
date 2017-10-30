package com.uit.tahitu.hci.smarthospital;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uit.tahitu.hci.smarthospital.customView.CustomViewTopBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HieuMinh on 10/30/2017.
 */

public class ChoiceDistrictActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.top_bar)
    CustomViewTopBar topBar;

    @BindView(R.id.ln_1)
    LinearLayout ln1;

    @BindView(R.id.ln_2)
    LinearLayout ln2;

    @BindView(R.id.ln_3)
    LinearLayout ln3;

    @BindView(R.id.ln_4)
    LinearLayout ln4;

    @BindView(R.id.ln_5)
    LinearLayout ln5;

    @BindView(R.id.ln_6)
    LinearLayout ln6;

    @BindView(R.id.ln_7)
    LinearLayout ln7;

    @BindView(R.id.ln_8)
    LinearLayout ln8;

    @BindView(R.id.ln_9)
    LinearLayout ln9;

    @BindView(R.id.ln_10)
    LinearLayout ln10;

    @BindView(R.id.cb_1)
    CheckBox cb1;

    @BindView(R.id.cb_2)
    CheckBox cb2;

    @BindView(R.id.cb_3)
    CheckBox cb3;

    @BindView(R.id.cb_4)
    CheckBox cb4;

    @BindView(R.id.cb_5)
    CheckBox cb5;

    @BindView(R.id.cb_6)
    CheckBox cb6;

    @BindView(R.id.cb_7)
    CheckBox cb7;

    @BindView(R.id.cb_8)
    CheckBox cb8;

    @BindView(R.id.cb_9)
    CheckBox cb9;

    @BindView(R.id.cb_10)
    CheckBox cb10;

    @BindView(R.id.tv_name_1)
    TextView tvName1;

    @BindView(R.id.tv_name_2)
    TextView tvName2;

    @BindView(R.id.tv_name_3)
    TextView tvName3;

    @BindView(R.id.tv_name_4)
    TextView tvName4;

    @BindView(R.id.tv_name_5)
    TextView tvName5;

    @BindView(R.id.tv_name_6)
    TextView tvName6;

    @BindView(R.id.tv_name_7)
    TextView tvName7;

    @BindView(R.id.tv_name_8)
    TextView tvName8;

    @BindView(R.id.tv_name_9)
    TextView tvName9;

    @BindView(R.id.tv_name_10)
    TextView tvName10;

    private ArrayList<String> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choise_district_activity);
        ButterKnife.bind(this);

        topBar.setImageViewLeft(CustomViewTopBar.DRAWABLE_BACK);
        topBar.setImageViewRight(CustomViewTopBar.DRAWABLE_SEARCH);
        topBar.setTextTitle("Chọn Quận - Huyện");
        topBar.setOnLeftRightClickListener(new CustomViewTopBar.OnLeftRightClickListener() {
            @Override
            public void onLeftClicked() {
                finish();
            }

            @Override
            public void onRightClicked() {
                mList = new ArrayList<>();
                if(cb1.isChecked()){
                    mList.add(tvName1.getText().toString());
                }
                if(cb2.isChecked()){
                    mList.add(tvName2.getText().toString());
                }
                if(cb3.isChecked()){
                    mList.add(tvName3.getText().toString());
                }
                if(cb4.isChecked()){
                    mList.add(tvName4.getText().toString());
                }
                if(cb5.isChecked()){
                    mList.add(tvName5.getText().toString());
                }
                if(cb6.isChecked()){
                    mList.add(tvName6.getText().toString());
                }
                if(cb7.isChecked()){
                    mList.add(tvName7.getText().toString());
                }
                if(cb8.isChecked()){
                    mList.add(tvName8.getText().toString());
                }
                if(cb9.isChecked()){
                    mList.add(tvName9.getText().toString());
                }
                if(cb10.isChecked()){
                    mList.add(tvName10.getText().toString());
                }
                getIntent().putStringArrayListExtra("ListQuan",mList);
                setResult(RESULT_OK,getIntent());
                finish();
            }
        });

        ln1.setOnClickListener(this);
        ln2.setOnClickListener(this);
        ln3.setOnClickListener(this);
        ln4.setOnClickListener(this);
        ln5.setOnClickListener(this);
        ln6.setOnClickListener(this);
        ln7.setOnClickListener(this);
        ln8.setOnClickListener(this);
        ln9.setOnClickListener(this);
        ln10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ln_1:
                cb1.setChecked(!cb1.isChecked());
                break;
            case R.id.ln_2:
                cb2.setChecked(!cb2.isChecked());
                break;
            case R.id.ln_3:
                cb3.setChecked(!cb3.isChecked());
                break;
            case R.id.ln_4:
                cb4.setChecked(!cb4.isChecked());
                break;
            case R.id.ln_5:
                cb5.setChecked(!cb5.isChecked());
                break;
            case R.id.ln_6:
                cb6.setChecked(!cb6.isChecked());
                break;
            case R.id.ln_7:
                cb7.setChecked(!cb7.isChecked());
                break;
            case R.id.ln_8:
                cb8.setChecked(!cb8.isChecked());
                break;
            case R.id.ln_9:
                cb9.setChecked(!cb9.isChecked());
                break;
            case R.id.ln_10:
                cb10.setChecked(!cb10.isChecked());
                break;
        }
    }
}

