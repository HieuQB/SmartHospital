package com.uit.tahitu.hci.smarthospital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.uit.tahitu.hci.smarthospital.customView.CustomViewTopBar;
import com.uit.tahitu.hci.smarthospital.fragment.adapter.QuanHuyenAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HieuMinh on 10/29/2017.
 */

public class FilterActivity extends AppCompatActivity implements View.OnClickListener {

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

    @BindView(R.id.imv_add_quan)
    ImageView imageAddQuan;

    @BindView(R.id.imv_add_khoa)
    ImageView imageAddKhoa;

    @BindView(R.id.rcv_khoa)
    RecyclerView rcvKhoa;

    @BindView(R.id.rcv_quanhuyen)
    RecyclerView rcvQuanHuyen;

    QuanHuyenAdapter adapterQuanHuyen;
    QuanHuyenAdapter adapterKhoa;
    private ArrayList<String> mListQuanHuyen;
    private ArrayList<String> mListKhoa;
    private int REQUEST_ADD_KHOA = 17;
    private int REQUEST_ADD_QUAN = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_activity);
        ButterKnife.bind(this);

        topBar.setImageViewLeft(CustomViewTopBar.DRAWABLE_BACK);
        topBar.setImageViewRight(CustomViewTopBar.DRAWABLE_FILTER);
        topBar.setTextTitle("Tìm kiếm Bệnh Viện");

        topBar.setOnLeftRightClickListener(new CustomViewTopBar.OnLeftRightClickListener() {
            @Override
            public void onLeftClicked() {
                finish();
            }

            @Override
            public void onRightClicked() {
                Toast.makeText(FilterActivity.this, "Lọc Thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        mListKhoa = new ArrayList<>();
        mListKhoa.add("Khoa Tai Mũi Họng");
        mListKhoa.add("Khoa Sản");
        mListQuanHuyen = new ArrayList<>();
        mListQuanHuyen.add("Quận Thủ Đức (127 Bệnh Viện)");
        mListQuanHuyen.add("Quận 7 (89 Bệnh Viện)");
        adapterKhoa = new QuanHuyenAdapter(this, mListKhoa, new QuanHuyenAdapter.OnItemClickListener() {
            @Override
            public void onItemSelect(int position) {
                mListKhoa.remove(position);
                adapterKhoa.notifyDataSetChanged();
            }
        });

        adapterQuanHuyen = new QuanHuyenAdapter(this, mListQuanHuyen, new QuanHuyenAdapter.OnItemClickListener() {
            @Override
            public void onItemSelect(int position) {
                mListQuanHuyen.remove(position);
                adapterQuanHuyen.notifyDataSetChanged();
            }
        });
        rcvKhoa.setAdapter(adapterKhoa);
        rcvKhoa.setLayoutManager(new LinearLayoutManager(this));

        rcvQuanHuyen.setAdapter(adapterQuanHuyen);
        rcvQuanHuyen.setLayoutManager(new LinearLayoutManager(this));


        rcvKhoa.setNestedScrollingEnabled(false);
        rcvQuanHuyen.setNestedScrollingEnabled(false);

        ln1.setOnClickListener(this);
        ln2.setOnClickListener(this);
        ln3.setOnClickListener(this);
        ln4.setOnClickListener(this);
        ln5.setOnClickListener(this);
        ln6.setOnClickListener(this);
        ln6.setOnClickListener(this);
        ln7.setOnClickListener(this);
        ln8.setOnClickListener(this);
        imageAddKhoa.setOnClickListener(this);
        imageAddQuan.setOnClickListener(this);
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
            case R.id.imv_add_khoa:
                Intent intent = new Intent(FilterActivity.this, ChoiceDepartmentActivity.class);
                startActivityForResult(intent, REQUEST_ADD_KHOA);
                break;
            case R.id.imv_add_quan:
                Intent intent1 = new Intent(FilterActivity.this, ChoiceDistrictActivity.class);
                startActivityForResult(intent1, REQUEST_ADD_QUAN);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_ADD_KHOA) {
                mListKhoa.clear();
                mListKhoa = data.getStringArrayListExtra("ListKhoa");
                adapterKhoa.setmList(mListKhoa);
                adapterKhoa.notifyDataSetChanged();
            } else if (requestCode == REQUEST_ADD_QUAN) {
                mListQuanHuyen.clear();
                mListQuanHuyen = data.getStringArrayListExtra("ListQuan");
                adapterQuanHuyen.setmList(mListQuanHuyen);
                adapterQuanHuyen.notifyDataSetChanged();
            }
        }
    }
}
