package com.uit.tahitu.hci.smarthospital.cards;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.uit.tahitu.hci.smarthospital.R;
import com.uit.tahitu.hci.smarthospital.utils.DecodeBitmapTask;

public class SliderCard extends RecyclerView.ViewHolder {

    private static int viewWidth = 0;
    private static int viewHeight = 0;

    protected final ImageView imageView;

    private DecodeBitmapTask task;

    public SliderCard(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.image);
    }

    void clearContent() {
        if (task != null) {
            task.cancel(true);
        }
    }

}