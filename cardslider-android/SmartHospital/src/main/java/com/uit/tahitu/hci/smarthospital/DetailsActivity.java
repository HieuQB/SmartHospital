package com.uit.tahitu.hci.smarthospital;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.uit.tahitu.hci.smarthospital.fragment.ListImageFragment;
import com.uit.tahitu.hci.smarthospital.fragment.RecyclerViewFragment;
import com.uit.tahitu.hci.smarthospital.fragment.TimeLineHospitalFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends ActionBarActivity  {

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
                    //case 0:
                    //    return RecyclerViewFragment.newInstance();
                    case 1:
                        return ListImageFragment.newInstance();
                    //case 2:
                    //    return WebViewFragment.newInstance();
                    case 3:
                        return TimeLineHospitalFragment.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
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
                        return "NHÂN SỰ";
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
                        getWindow().setStatusBarColor(getResources().getColor(R.color.green));
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.green,
                                getResources().getDrawable(R.drawable.bvtudu_toancanh2));
                    case 1:
                        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.blue,
                                getResources().getDrawable(R.drawable.bvtudu_gioithieu));
                    case 2:
                        getWindow().setStatusBarColor(getResources().getColor(R.color.purple));
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.purple,
                                getResources().getDrawable(R.drawable.bvtudu_nhanvien));
                    case 3:
                        getWindow().setStatusBarColor(getResources().getColor(R.color.red));
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.red,
                                getResources().getDrawable(R.drawable.bvtudu_logo));
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
