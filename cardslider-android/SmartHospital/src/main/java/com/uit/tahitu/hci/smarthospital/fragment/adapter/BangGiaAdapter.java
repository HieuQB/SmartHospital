package com.uit.tahitu.hci.smarthospital.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uit.tahitu.hci.smarthospital.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HieuMinh on 10/27/2017.
 */

public class BangGiaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    private ChungAdapter.OnItemClickListener mCallBack;

    public BangGiaAdapter(List<Object> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_header_banggia, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_detail_banggia, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                break;
            case TYPE_CELL:
                break;
        }
    }

//    class BangGiaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//
//        @BindView(R.id.tvLienHe)
//        TextView tvLienHe;
//
//        public BangGiaHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//            tvLienHe.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            if(v.getId() == tvLienHe.getId()){
//                mCallBack.onItemClick();
//            }
//        }
//    }
}
