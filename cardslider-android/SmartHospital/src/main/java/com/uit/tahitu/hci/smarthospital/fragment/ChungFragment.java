package com.uit.tahitu.hci.smarthospital.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.uit.tahitu.hci.smarthospital.CommentActivity;
import com.uit.tahitu.hci.smarthospital.R;
import com.uit.tahitu.hci.smarthospital.fragment.adapter.ChungAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChungFragment extends Fragment {

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 2;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static ChungFragment newInstance() {
        return new ChungFragment();
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
        mRecyclerView.setAdapter(new ChungAdapter(items, new ChungAdapter.OnItemClickListener() {
            @Override
            public void onItemClick() {
                String phone = "028 5404 2829";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }

            @Override
            public void onLikeClick() {
                Toast.makeText(getActivity(), "Bạn đã ấn like", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShareClick() {
//                ShareLinkContent content = new ShareLinkContent.Builder()
//                        .setContentUrl(Uri.parse("https://developers.facebook.com"))
//                        .setShareHashtag(new ShareHashtag.Builder()
//                                .setHashtag("#ConnectTheWorld")
//                                .build())
//                .build();
//                ShareDialog.show(getActivity(), content);
            }

            @Override
            public void onCommentClick() {
                startActivity(new Intent(getActivity(), CommentActivity.class));
            }
        }));
    }
}
