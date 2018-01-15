package com.uit.tahitu.hci.smarthospital.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uit.tahitu.hci.smarthospital.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class ChungAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    static boolean like = true;
    static boolean evaluate = true;
    private ChungAdapter.OnItemClickListener mCallBack;

    public interface OnItemClickListener {
        void onItemClick();
        void onLikeClick();
    }
    public ChungAdapter(List<Object> contents, OnItemClickListener mCallBack) {
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
                return new HomePageDetailHolder(view) {
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

    class HomePageDetailHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.btnLike_1)
        LinearLayout btnLike1;

        @BindView(R.id.iv_like_1)
        ImageView ivLike1;

        public HomePageDetailHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            btnLike1.setOnClickListener(this);
            if (!like) {
                ivLike1.setImageResource(R.drawable.comment);
            }
            else {
                ivLike1.setImageResource(R.drawable.heart);
            }
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == btnLike1.getId()){
                if (like) {
                    ivLike1.setImageResource(R.drawable.comment);
                    like = !like;
                }
                else {
                    ivLike1.setImageResource(R.drawable.heart);
                    like = !like;
                }
//                mCallBack.onLikeClick();
            }
        }
    }
    class HomePageHeaderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_1)
        ImageView iv1;

        @BindView(R.id.tv_1)
        TextView tv1;

        @BindView(R.id.tv_evaluate)
        TextView tvEvaluate;

        @BindView(R.id.tvLienHe)
        TextView tvLienHe;

        public HomePageHeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvLienHe.setOnClickListener(this);
            iv1.setOnClickListener(this);

            if (evaluate) {
                tvEvaluate.setText("54 lượt đánh giá");
                tv1.setText("Tuyệt vời 30");
            }
            else {
                tvEvaluate.setText("55 lượt đánh giá");
                tv1.setText("Tuyệt vời 31");
            }
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == tvLienHe.getId()){
                mCallBack.onItemClick();
            }

            if(v.getId() == iv1.getId()){
                if (evaluate) {
                    tvEvaluate.setText("55 lượt đánh giá");
                    tv1.setText("Tuyệt vời 31");
                    evaluate = !evaluate;
                }
                else {
                    tvEvaluate.setText("54 lượt đánh giá");
                    tv1.setText("Tuyệt vời 30");
                    evaluate = !evaluate;
                }
            }
        }
    }
}