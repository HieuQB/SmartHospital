package com.uit.tahitu.hci.smarthospital.customView;

public interface ICustomViewTopBar {

    void setTextTitle(String title);

    void setTextTitleVisible(int type);

    void setTextViewLeft(String text);

    void setTextViewRight(String text);

    void setTextAll(String textLeft, String title, String textRight);

    void setTextUserCount(int count);

    void setTextUserCountVisible(int visible);

    void setTextTwoCount(int count);

    void setTextTwoCountVisible(int visible);

    void setImageViewLeft(int type);

    void setImageViewRight(int one);

    void setImageViewRightTwo(int two);

    void setImageViewRightThree(int three);

    void setImageViewRight(int two, int one);

    void setImageViewRight(int three, int two, int one);

    void setImageViewRightVisible(int one);

    void setImageViewRightVisible(int two, int one);

    void setImageViewRightVisible(int three, int two, int one);

    void setImageViewRightSelected(boolean one);

    void setImageViewRightSelected(boolean two, boolean one);

    void setImageViewRightSelected(boolean three, boolean two, boolean one);

    void startAnimation(int resource);

    void showLoading();

    void hideLoading();
}
