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
 * Created by florentchampigny on 24/04/15.
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    private TestRecyclerViewAdapter.OnItemClickListener mCallBack;

    public interface OnItemClickListener {
        void onItemClick();
    }
    public TestRecyclerViewAdapter(List<Object> contents,OnItemClickListener mCallBack) {
        this.contents = contents;
        this.mCallBack = mCallBack;
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
                        .inflate(R.layout.list_item_card_big, parent, false);
                return new HomePageHeaderHolder(view) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
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

    class HomePageHeaderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.tvLienHe)
        TextView tvLienHe;

        public HomePageHeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvLienHe.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == tvLienHe.getId()){
                mCallBack.onItemClick();
            }
        }
    }
}