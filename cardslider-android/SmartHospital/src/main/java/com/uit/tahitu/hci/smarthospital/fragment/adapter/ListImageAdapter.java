package com.uit.tahitu.hci.smarthospital.fragment.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.uit.tahitu.hci.smarthospital.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HieuMinh on 10/23/2017.
 */

public class ListImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    private OnItemClickListener mCallBack;

    public interface OnItemClickListener {
        void onItemSelect(int position);
    }

    public ListImageAdapter(List<Object> contents,OnItemClickListener mCallBack) {
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
                        .inflate(R.layout.list_image_fragment, parent, false);
                return new ListImageHolder(view) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_hinhanhnguoidung, parent, false);
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

    class ListImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.pic1)
        ImageView pic1;

        @BindView(R.id.pic2)
        ImageView pic2;

        @BindView(R.id.pic3)
        ImageView pic3;

        @BindView(R.id.pic4)
        ImageView pic4;

        @BindView(R.id.pic5)
        ImageView pic5;

        @BindView(R.id.pic6)
        ImageView pic6;

        @BindView(R.id.pic7)
        ImageView pic7;

        @BindView(R.id.pic8)
        ImageView pic8;

        @BindView(R.id.pic9)
        ImageView pic9;

        @BindView(R.id.pic10)
        ImageView pic10;

        @BindView(R.id.pic11)
        ImageView pic11;

        @BindView(R.id.pic12)
        ImageView pic12;

        public ListImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            pic1.setOnClickListener(this);
            pic2.setOnClickListener(this);
            pic3.setOnClickListener(this);
            pic4.setOnClickListener(this);
            pic5.setOnClickListener(this);
            pic6.setOnClickListener(this);
            pic7.setOnClickListener(this);
            pic8.setOnClickListener(this);
            pic9.setOnClickListener(this);
            pic10.setOnClickListener(this);
            pic11.setOnClickListener(this);
            pic12.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == pic1.getId()){
                mCallBack.onItemSelect(1);
            } else if(v.getId() == pic2.getId()){
                mCallBack.onItemSelect(2);
            } else if(v.getId() == pic3.getId()){
                mCallBack.onItemSelect(3);
            } else if(v.getId() == pic4.getId()){
                mCallBack.onItemSelect(4);
            } else if(v.getId() == pic5.getId()){
                mCallBack.onItemSelect(5);
            } else if(v.getId() == pic6.getId()){
                mCallBack.onItemSelect(6);
            } else if(v.getId() == pic7.getId()){
                mCallBack.onItemSelect(7);
            } else if(v.getId() == pic8.getId()){
                mCallBack.onItemSelect(8);
            } else if(v.getId() == pic9.getId()){
                mCallBack.onItemSelect(9);
            } else if(v.getId() == pic10.getId()){
                mCallBack.onItemSelect(10);
            } else if(v.getId() == pic11.getId()){
                mCallBack.onItemSelect(11);
            } else if(v.getId() == pic12.getId()){
                mCallBack.onItemSelect(12);
            }
        }
    }
}
