package com.uit.tahitu.hci.smarthospital;

/**
 * Created by HieuMinh on 10/23/2017.
 */

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;
import com.uit.tahitu.hci.smarthospital.customView.CustomViewTopBar;
import com.uit.tahitu.hci.smarthospital.utils.DecodeBitmapTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewImageActivity extends AppCompatActivity {
    private GyroscopeObserver gyroscopeObserver;
    public static final String BUNDLE_IMAGE_ID = "BUNDLE_IMAGE_ID";

    private PanoramaImageView imageView;
    @BindView(R.id.top_bar)
    CustomViewTopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_image_activity);
        ButterKnife.bind(this);
        topBar.setImageViewLeft(CustomViewTopBar.DRAWABLE_BACK);
        topBar.setTextTitle("Bệnh viện Từ Dữ");
        topBar.setOnLeftRightClickListener(new CustomViewTopBar.OnLeftRightClickListener() {
            @Override
            public void onLeftClicked() {
                finish();
            }

            @Override
            public void onRightClicked() {

            }
        });

        final int smallResId = getIntent().getIntExtra(BUNDLE_IMAGE_ID, -1);
        if (smallResId == -1) {
            finish();
            return;
        }


        gyroscopeObserver = new GyroscopeObserver();

        imageView = (PanoramaImageView) findViewById(R.id.panorama_image_view);

        switch (smallResId) {
            case 1:
                imageView.setImageResource(R.drawable.bvtudu_bando);
                break;
            case 2:
                imageView.setImageResource(R.drawable.bvtudu_sodobenhvien);
                break;
            case 3:
                imageView.setImageResource(R.drawable.bvtudu_huongdan);
                break;
            case 4:
                imageView.setImageResource(R.drawable.bvtudu_mo);
                break;
            case 5:
                imageView.setImageResource(R.drawable.bvtudu_nhanvien);
                break;
            case 6:
                imageView.setImageResource(R.drawable.bvtudu_sodo);
                break;
            case 7:
                imageView.setImageResource(R.drawable.bvtudu_sodobomay);
                break;
            case 8:
                imageView.setImageResource(R.drawable.bvtudu_toancanh);
                break;
            case 9:
                imageView.setImageResource(R.drawable.bvtudu_quytrinhhiemmuon);
                break;
            case 10:
                imageView.setImageResource(R.drawable.bvtudu_toancanh2);
                break;
            case 11:
                imageView.setImageResource(R.drawable.bvtudu_thoigiantramau);
                break;
            case 12:
                imageView.setImageResource(R.drawable.khamthai);
                break;
            default:
                break;
        }


        imageView.setGyroscopeObserver(gyroscopeObserver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gyroscopeObserver.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gyroscopeObserver.unregister();
    }

}

