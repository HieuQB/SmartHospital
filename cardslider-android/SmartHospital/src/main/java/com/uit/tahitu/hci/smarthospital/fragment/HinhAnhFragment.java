package com.uit.tahitu.hci.smarthospital.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.uit.tahitu.hci.smarthospital.R;
import com.uit.tahitu.hci.smarthospital.ViewImageActivity;
import com.uit.tahitu.hci.smarthospital.fragment.adapter.HinhAnhAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.uit.tahitu.hci.smarthospital.ViewImageActivity.BUNDLE_IMAGE_ID;

/**
 * Created by HieuMinh on 10/23/2017.
 */

public class HinhAnhFragment extends Fragment {

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 2;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static HinhAnhFragment newInstance() {
        return new HinhAnhFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        final List<Object> items = new ArrayList<>();

        for (int i = 0; i < ITEM_COUNT; ++i) {
            items.add(new Object());
        }


        //setup materialviewpager

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(new HinhAnhAdapter(items, new HinhAnhAdapter.OnItemClickListener() {
            @Override
            public void onItemSelect(int position) {
                Intent intent = new Intent(getActivity(),ViewImageActivity.class);
                intent.putExtra(BUNDLE_IMAGE_ID,position);
                startActivity(intent);
            }
        }));
    }
}
