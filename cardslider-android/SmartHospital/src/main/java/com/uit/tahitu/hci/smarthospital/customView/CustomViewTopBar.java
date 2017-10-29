package com.uit.tahitu.hci.smarthospital.customView;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.uit.tahitu.hci.smarthospital.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomViewTopBar
        extends ConstraintLayout
        implements View.OnClickListener, ICustomViewTopBar {

    public static final int LEFT_MENU = 0;
    public static final int LEFT_BACK = 1;

    public static final int DRAWABLE_FILTER = R.drawable.ic_filter;
    public static final int DRAWABLE_ADD_TRANSPARENT = R.drawable.ic_add_transparent;
    public static final int DRAWABLE_BACK = R.drawable.ic_back_white;
    public static final int DRAWABLE_MY_RECRUITMENT = R.drawable.ic_user_confirm;

    @BindView(R.id.ln_left)
    LinearLayout lnLeft;

    @BindView(R.id.imv_left)
    ImageView imvLeft;

    @BindView(R.id.tv_screen_title)
    TextView tvScreenTitle;

    @BindView(R.id.tv_left)
    TextView tvLeft;

    @BindView(R.id.tv_right)
    TextView tvRight;

    @BindView(R.id.tv_status_user_count)
    TextView tvStatusUserCount;

    @BindView(R.id.ln_content_status_user_count)
    LinearLayout lnStatusUserCount;

    @BindView(R.id.tv_status_two_count)
    TextView tvStatusTwoCount;

    @BindView(R.id.ln_content_status_two_count)
    LinearLayout lnStatusTwoCount;

    @BindView(R.id.imv_right_button_one)
    ImageView imvRightButtonOne;

    @BindView(R.id.imv_right_button_two)
    ImageView imvRightButtonTwo;

    @BindView(R.id.imv_right_button_three)
    ImageView imvRightButtonThree;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    OnItemClickListener onItemClickListener;
    OnLeftRightClickListener onLeftRightClickListener;
    private ObjectAnimator animation;
    private boolean isDoneStartProgress;

    public void setOnClickListener(OnItemClickListener callback) {
        onItemClickListener = callback;
    }

    public void setOnLeftRightClickListener(OnLeftRightClickListener callback) {
        onLeftRightClickListener = callback;
    }

    public interface OnLeftRightClickListener {

        void onLeftClicked();

        void onRightClicked();
    }

    /**
     * Left : Menu or Back
     * Right flow: [Three] -> [Two] -> [One] -> [End of Screen]
     */
    public interface OnItemClickListener {

        void onLeftClicked();

        void onRightButtonOneClicked();

        void onRightButtonTwoClicked();

        void onRightButtonThreeClicked();
    }

    public CustomViewTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_topbar_base_activity,
                this,
                false
        );
        ButterKnife.bind(this, view);

        lnLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);

        imvRightButtonOne.setOnClickListener(this);
        imvRightButtonTwo.setOnClickListener(this);
        imvRightButtonThree.setOnClickListener(this);
        this.addView(view);
    }

    @Override
    public void setTextTitle(String title) {
        tvScreenTitle.setText(title);
        tvScreenTitle.setSelected(true);
    }

    @Override
    public void setTextTitleVisible(int type) {
        tvScreenTitle.setVisibility(type);
    }

    @Override
    public void setTextViewLeft(String text) {

        tvLeft.setVisibility(VISIBLE);
        tvLeft.setText(text);
        if (text.isEmpty()) {
            imvLeft.setVisibility(VISIBLE);
        } else {
            imvLeft.setVisibility(INVISIBLE);
        }
    }

    @Override
    public void setTextViewRight(String text) {
        tvRight.setVisibility(TextUtils.isEmpty(text) ? GONE : VISIBLE);
        tvRight.setText(text);
    }

    /**
     * 0 : Menu
     * 1 : Back
     *
     * @param type Type Resource ImageView Left
     */
    public void setImageViewLeft(int type) {
        if (type == LEFT_MENU) {
            imvLeft.setImageResource(R.drawable.ic_menu);
        } else {
            imvLeft.setImageResource(R.drawable.ic_back_white);
        }
        imvLeft.setVisibility(VISIBLE);
        tvLeft.setVisibility(INVISIBLE);
    }

    public void setImageViewLeftVisibility(int type) {
        imvLeft.setVisibility(type);
    }

    /**
     * @param one Type Resource ImageView
     */
    @Override
    public void setImageViewRight(int one) {
        imvRightButtonOne.setVisibility(VISIBLE);
        imvRightButtonOne.setImageResource(one);
    }

    @Override
    public void setImageViewRightTwo(int two) {
        imvRightButtonTwo.setVisibility(VISIBLE);
        imvRightButtonTwo.setImageResource(two);
    }

    @Override
    public void setImageViewRightThree(int three) {
        imvRightButtonThree.setVisibility(VISIBLE);
        imvRightButtonThree.setImageResource(three);
    }

    /**
     * @param two Type Resource ImageView
     * @param one Type Resource ImageView
     */
    @Override
    public void setImageViewRight(int two, int one) {
        setImageViewRight(one);
        setImageViewRightTwo(two);
    }

    /**
     * @param three Type Resource ImageView
     * @param two   Type Resource ImageView
     * @param one   Type Resource ImageView
     */
    @Override
    public void setImageViewRight(int three, int two, int one) {
        // Three
        setImageViewRightThree(three);
        setImageViewRight(two, one);

        imvRightButtonTwo.setVisibility(VISIBLE);
        imvRightButtonThree.setVisibility(VISIBLE);
    }

    /**
     * @param one GONE or VISIBLE or INVISIBLE
     */
    @Override
    public void setImageViewRightVisible(int one) {
        imvRightButtonOne.setVisibility(one);
    }

    /**
     * @param two GONE or VISIBLE or INVISIBLE
     * @param one GONE or VISIBLE or INVISIBLE
     */
    @Override
    public void setImageViewRightVisible(int two, int one) {
        setImageViewRightVisible(one);
        if (Integer.valueOf(tvStatusTwoCount.getText().toString()) == 0) {
            lnStatusTwoCount.setVisibility(GONE);
        } else {
            lnStatusTwoCount.setVisibility(two);
        }
        imvRightButtonTwo.setVisibility(two);
    }

    /**
     * @param three GONE or VISIBLE or INVISIBLE
     * @param two   GONE or VISIBLE or INVISIBLE
     * @param one   GONE or VISIBLE or INVISIBLE
     */
    @Override
    public void setImageViewRightVisible(int three, int two, int one) {
        setImageViewRightVisible(two, one);
        if (Integer.valueOf(tvStatusUserCount.getText().toString()) == 0) {
            lnStatusUserCount.setVisibility(GONE);
        } else {
            lnStatusUserCount.setVisibility(three);
        }
        imvRightButtonThree.setVisibility(three);
    }

    @Override
    public void setImageViewRightSelected(boolean one) {
        imvRightButtonOne.setSelected(one);
    }

    @Override
    public void setImageViewRightSelected(boolean two, boolean one) {
        setImageViewRightSelected(one);
        imvRightButtonTwo.setSelected(two);
    }

    @Override
    public void setImageViewRightSelected(boolean three, boolean two, boolean one) {
        setImageViewRightSelected(two, one);
        imvRightButtonThree.setSelected(three);
    }

    @Override
    public void startAnimation(int resource) {
        imvRightButtonOne.startAnimation(AnimationUtils.loadAnimation(getContext(), resource));
        imvRightButtonTwo.startAnimation(AnimationUtils.loadAnimation(getContext(), resource));
        imvRightButtonThree.startAnimation(AnimationUtils.loadAnimation(getContext(), resource));
    }

    private void setEnabledViewAllView(boolean isAble) {
        if (tvLeft.getText().toString().equalsIgnoreCase(getContext().getString(R.string.cancel)))
            lnLeft.setEnabled(isAble);

        if (tvRight.getText().toString().equalsIgnoreCase(getContext().getString(R.string.save)))
            tvRight.setEnabled(isAble);
//        imvRightButtonOne.setEnabled(isAble);
//        imvRightButtonTwo.setEnabled(isAble);
//        imvRightButtonThree.setEnabled(isAble);
    }

    /**
     * Process loading bar
     */

    public boolean isLoading() {
        return progressBar.getVisibility() == View.VISIBLE;
    }

    @Override
    public void showLoading() {
        setEnabledViewAllView(false);
        startProgress();
    }

    @Override
    public void hideLoading() {
        setEnabledViewAllView(true);
        if (isDoneStartProgress) {
            finishProgress();
        } else {
            delayCheckToFinishProgress();
        }
    }

    private int firstProcessPercent;
    private static final int TOTAL_MILLISECONDS = 500;
    private static final int TOTAL_PERCENT = 100;
    private static final int MIN_PERCENT = 75;
    private static final int MAX_PERCENT = 95;

    private void startProgress() {
        Random rn = new Random();
        firstProcessPercent = rn.nextInt((MAX_PERCENT - MIN_PERCENT) + 1) + MIN_PERCENT;
        int firstProcessTime = (int) (((float) firstProcessPercent) / TOTAL_PERCENT * TOTAL_MILLISECONDS);
        smoothProgress(firstProcessPercent, firstProcessTime, new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
                isDoneStartProgress = false;
            }
        }, new Runnable() {
            @Override
            public void run() {
                isDoneStartProgress = true;
            }
        });
    }

    private void finishProgress() {
        int finishProcessTime = (int) (((float) (TOTAL_PERCENT - firstProcessPercent)) / TOTAL_PERCENT * TOTAL_MILLISECONDS);
        smoothProgress(TOTAL_PERCENT, finishProcessTime, null, new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                progressBar.setProgress(0);
                firstProcessPercent = 0;
                isDoneStartProgress = false;
            }
        });
    }

    private void smoothProgress(int toProgress, int inMilliseconds, final Runnable startRunnable, final Runnable endRunnable) {
        animation = ObjectAnimator.ofInt(progressBar, "progress", toProgress);
        animation.setDuration(inMilliseconds);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.removeAllListeners();
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (startRunnable != null) {
                    startRunnable.run();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (endRunnable != null) {
                    endRunnable.run();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animation.start();
    }

    private void delayCheckToFinishProgress() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoading();
            }
        }, 100);
    }

    /**
     * Use Only Leaving Activity
     *
     * @param count User confirm count
     */
    @Override
    public void setTextUserCount(int count) {
        if (count == 0) {
            lnStatusUserCount.setVisibility(GONE);
        } else {
            lnStatusUserCount.setVisibility(VISIBLE);
            tvStatusUserCount.setText(String.valueOf(count));
        }
    }

    @Override
    public void setTextUserCountVisible(int visible) {
        lnStatusUserCount.setVisibility(visible);
    }

    /**
     * Use Only Salary Staff Activity
     *
     * @param count User confirm count
     */
    @Override
    public void setTextTwoCount(int count) {
        if (count == 0) {
            lnStatusTwoCount.setVisibility(GONE);
        } else {
            lnStatusTwoCount.setVisibility(VISIBLE);
            tvStatusTwoCount.setText(String.valueOf(count));
        }
    }

    @Override
    public void setTextTwoCountVisible(int visible) {
        lnStatusTwoCount.setVisibility(visible);
    }

    /**
     * User For Dialog
     *
     * @param textLeft  Cancel
     * @param title     Title Dialog
     * @param textRight Save or Done
     */
    public void setTextAll(String textLeft, String title, String textRight) {
        setTextTitle(title);
        setTextViewLeft(textLeft);
        setTextViewRight(textRight);
    }

    public void setGoneButtonRight(int key) {
        switch (key) {
            case 1:
                imvRightButtonOne.setVisibility(GONE);
                break;
            case 2:
                imvRightButtonTwo.setVisibility(GONE);
                break;
            case 3:
                imvRightButtonThree.setVisibility(GONE);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == lnLeft.getId()) {
            if (onLeftRightClickListener != null) onLeftRightClickListener.onLeftClicked();
            if (onItemClickListener != null) onItemClickListener.onLeftClicked();
        } else if (id == tvRight.getId()) {
            if (onLeftRightClickListener != null) onLeftRightClickListener.onRightClicked();
        } else if (id == imvRightButtonOne.getId()) {
            if (onItemClickListener != null) {
                onItemClickListener.onRightButtonOneClicked();
            }
            if (onLeftRightClickListener != null) {
                onLeftRightClickListener.onRightClicked();
            }
        } else if (id == imvRightButtonTwo.getId()) {
            if (onItemClickListener != null) onItemClickListener.onRightButtonTwoClicked();
        } else if (id == imvRightButtonThree.getId()) {
            if (onItemClickListener != null) onItemClickListener.onRightButtonThreeClicked();
        }
    }
}
