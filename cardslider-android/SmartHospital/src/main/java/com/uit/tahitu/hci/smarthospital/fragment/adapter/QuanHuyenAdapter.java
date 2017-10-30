package com.uit.tahitu.hci.smarthospital.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uit.tahitu.hci.smarthospital.LoginActivity;
import com.uit.tahitu.hci.smarthospital.MainActivity;
import com.uit.tahitu.hci.smarthospital.R;
import com.uit.tahitu.hci.smarthospital.customView.DialogPositiveNegative;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HieuMinh on 10/30/2017.
 */

public class QuanHuyenAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<String> mList;
    private OnItemClickListener mCallBack;

    public void setmList(ArrayList<String> mList) {
        this.mList = mList;
    }

    public interface OnItemClickListener {
        void onItemSelect(int position);
    }

    public QuanHuyenAdapter(Context mContext, ArrayList<String> mList, OnItemClickListener mCallBack) {
        this.mContext = mContext;
        this.mList = mList;
        this.mCallBack = mCallBack;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.quan_huyen_adapter, parent,false);
        return new QuanHuyenHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final QuanHuyenHolder vHolder = (QuanHuyenHolder) holder;

        vHolder.tvName.setText(mList.get(position));

    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    class QuanHuyenHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.imv_delete)
        ImageView imvDelete;

        public QuanHuyenHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogPositiveNegative dialog = new DialogPositiveNegative(mContext, "Smart Hospital", "Bạn muốn xóa lựa chọn "+mList.get(getAdapterPosition())+" ?", "OK","Cancel");
                    dialog.show();
                    dialog.setOnIPositiveNegativeDialogListener(new DialogPositiveNegative.IPositiveNegativeDialogListener() {
                        @Override
                        public void onIPositiveNegativeDialogAnswerPositive(DialogPositiveNegative dialog) {
                            mCallBack.onItemSelect(getAdapterPosition());
                            dialog.dismiss();
                        }

                        @Override
                        public void onIPositiveNegativeDialogAnswerNegative(DialogPositiveNegative dialog) {
                            dialog.dismiss();
                        }
                    });
                }
            });
        }
    }
}
