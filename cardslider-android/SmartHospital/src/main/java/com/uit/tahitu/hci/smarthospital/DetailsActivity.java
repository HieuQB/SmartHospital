package com.uit.tahitu.hci.smarthospital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.uit.tahitu.hci.smarthospital.ResideMenu.ResideMenu;
import com.uit.tahitu.hci.smarthospital.ResideMenu.ResideMenuItem;
import com.uit.tahitu.hci.smarthospital.fragment.BangGiaFragment;
import com.uit.tahitu.hci.smarthospital.fragment.ChungFragment;
import com.uit.tahitu.hci.smarthospital.fragment.HinhAnhFragment;
import com.uit.tahitu.hci.smarthospital.fragment.TimeLineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {


    @BindView(R.id.materialViewPager)
    MaterialViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setTitle("");
        ButterKnife.bind(this);
        final Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        mViewPager.getHeaderBackgroundContainer().
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
//                    case 0:
//                        return ChungFragment.newInstance();
                    case 1:
                        return HinhAnhFragment.newInstance();
                    case 2:
                        return BangGiaFragment.newInstance();
                    case 3:
                        return TimeLineFragment.newInstance();
                    default:
                        return ChungFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }


            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "CHUNG";
                    case 1:
                        return "HÌNH ẢNH";
                    case 2:
                        return "BẢNG GIÁ";
                    case 3:
                        return "TIMELINE";
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.blue,
                                getResources().getDrawable(R.drawable.bvtudu_toancanh2));
                    case 1:
                        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.blue,
                                getResources().getDrawable(R.drawable.bvtudu_logo));
                    case 2:
                        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.blue,
                                getResources().getDrawable(R.drawable.bvtudu_nhanvien));
                    case 3:
                        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.blue,
                                getResources().getDrawable(R.drawable.bvtudu_gioithieu));
                    default:
                        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        final View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
