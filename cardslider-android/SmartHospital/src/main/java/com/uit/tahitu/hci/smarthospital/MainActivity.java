package com.uit.tahitu.hci.smarthospital;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.uit.tahitu.CardSliderLayoutManager;
import com.uit.tahitu.CardSnapHelper;
import com.uit.tahitu.hci.smarthospital.ResideMenu.ResideMenu;
import com.uit.tahitu.hci.smarthospital.ResideMenu.ResideMenuItem;
import com.uit.tahitu.hci.smarthospital.cards.SliderAdapter;
import com.uit.tahitu.hci.smarthospital.customView.CustomViewTopBar;
import com.uit.tahitu.hci.smarthospital.customView.DialogPositiveNegative;
import com.uit.tahitu.hci.smarthospital.utils.DecodeBitmapTask;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ResideMenu resideMenu;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemLogin;
    private ResideMenuItem itemShare;
    private ResideMenuItem itemCalendar;
    private ResideMenuItem itemSettings;

    @BindView(R.id.tvLienHe)
    TextView tvLienHe;

    private final int[] pics = {R.drawable.p1, R.drawable.bvtudu_toancanh2, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6};
    private final int[] maps = {R.drawable.map1, R.drawable.map2, R.drawable.map3, R.drawable.map4, R.drawable.map5,R.drawable.map6};
    private final int[] descriptions = {R.string.text1, R.string.text2, R.string.text3, R.string.text4, R.string.text5};
    private final String[] countries = {"Bệnh viện Quận Thủ Đức", "Bệnh viện Phụ Sản Từ Dũ", "Phòng Khám Đa Khoa Quốc Tế Sài Gòn", "Bệnh viện Nhiệt Đới", "Bệnh Viện Đa Khoa Vinmec Hồ Chí Minh", "Bệnh viện Đa Khoa Đồng Nai"};
    private final String[] places = {"29 Phú Châu, Tam Phú, Hồ Chí Minh, Linh Trung, Thủ Đức", "Số 284 Cống Quỳnh, Phạm Ngũ Lão, Quận 1, Thành phố Hồ Chí Minh", "6 Trịnh Văn Cấn, Cầu Ông Lãnh, Quận 1, Thành phố Hồ Chí Minh", "764 Võ Văn Kiệt, phường 1, Thành phố Hồ Chí Minh", "Số 2-2 bis Trần Cao Vân, Phường Đakao, Quận 1","1B Đường Hoàng Hữu Nam, Long Thạnh Mỹ, Quận 9"};
    private final String[] temperatures = {"15 KM", "14.5 KM", "20 KM", "6 KM", "2.7 KM"};
    private final String[] times = {"Thứ 2 - Thứ 7    7:30-16:30", "Thứ 2 - Chủ Nhật    Mở cửa cả ngày", "Thứ 2 - Thứ 6    7:30-22:00"};

    private final SliderAdapter sliderAdapter = new SliderAdapter(this ,pics, 20, new OnCardClickListener());

    private CardSliderLayoutManager layoutManger;
    private RecyclerView recyclerView;
    private TextSwitcher temperatureSwitcher;
    private TextSwitcher placeSwitcher;
    private TextSwitcher clockSwitcher;
    private TextSwitcher descriptionsSwitcher;

    private TextView country1TextView;
    private TextView country2TextView;
    private int countryOffset1;
    private int countryOffset2;
    private long countryAnimDuration;
    private int currentPosition;

    private DecodeBitmapTask decodeMapBitmapTask;

    @BindView(R.id.top_bar)
    CustomViewTopBar topBar;
    private int REQUEST_LOGIN = 27;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        topBar.setTextTitle("Smart Hospital");
        topBar.setImageViewLeft(CustomViewTopBar.LEFT_MENU);
        topBar.setImageViewRight(CustomViewTopBar.DRAWABLE_SEARCH, CustomViewTopBar.DRAWABLE_MY_RECRUITMENT);
        topBar.setImageViewRightVisible(View.VISIBLE,View.GONE);
        topBar.setOnClickListener(new CustomViewTopBar.OnItemClickListener() {
            @Override
            public void onLeftClicked() {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }

            @Override
            public void onRightButtonOneClicked() {
                Toast.makeText(MainActivity.this, "Open Profile User - Comming Soon", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightButtonTwoClicked() {
                startActivity(new Intent(MainActivity.this, FilterActivity.class));
            }

            @Override
            public void onRightButtonThreeClicked() {

            }
        });
        setUpMenu();
        initRecyclerView();
        initCountryText();
        initSwitchers();
        tvLienHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", 10.8268560, 106.8350591, "Bệnh viện Từ Dũ");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                try
                {
                    startActivity(intent);
                }
                catch(ActivityNotFoundException ex)
                {
                    try
                    {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(unrestrictedIntent);
                    }
                    catch(ActivityNotFoundException innerEx)
                    {
                        Toast.makeText(MainActivity.this, "Please install a maps application", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void setUpMenu() {
        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setUse3D(true);
        resideMenu.setBackground(R.drawable.bg_intro);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.icon_home,     "Trang Chủ");
        itemLogin  = new ResideMenuItem(this, R.drawable.icon_profile,  "Đăng Nhập");
        itemCalendar = new ResideMenuItem(this, R.drawable.icon_calendar, "Tác Giả");
        itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, "Cài Đặt");
        itemShare = new ResideMenuItem(this, R.drawable.ic_share_black_24dp, "Chia Sẻ");
        itemHome.setOnClickListener(this);
        itemLogin.setOnClickListener(this);
        itemCalendar.setOnClickListener(this);
        itemSettings.setOnClickListener(this);
        itemShare.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemLogin, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemShare, ResideMenu.DIRECTION_LEFT);

//         You can disable a direction by setting ->
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);

    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(sliderAdapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    onActiveCardChange();
                }
            }
        });

        layoutManger = (CardSliderLayoutManager) recyclerView.getLayoutManager();

        new CardSnapHelper().attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (decodeMapBitmapTask != null) {
            decodeMapBitmapTask.cancel(true);
        }
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
        }

        @Override
        public void closeMenu() {
        }
    };

    private void initSwitchers() {
        temperatureSwitcher = (TextSwitcher) findViewById(R.id.ts_temperature);
        temperatureSwitcher.setFactory(new TextViewFactory(R.style.TemperatureTextView, true));
        temperatureSwitcher.setCurrentText(temperatures[0]);

        placeSwitcher = (TextSwitcher) findViewById(R.id.ts_place);
        placeSwitcher.setFactory(new TextViewFactory(R.style.PlaceTextView, false));
        placeSwitcher.setCurrentText(places[0]);

        clockSwitcher = (TextSwitcher) findViewById(R.id.ts_clock);
        clockSwitcher.setFactory(new TextViewFactory(R.style.ClockTextView, false));
        clockSwitcher.setCurrentText(times[0]);

        descriptionsSwitcher = (TextSwitcher) findViewById(R.id.ts_description);
        descriptionsSwitcher.setInAnimation(this, android.R.anim.fade_in);
        descriptionsSwitcher.setOutAnimation(this, android.R.anim.fade_out);
        descriptionsSwitcher.setFactory(new TextViewFactory(R.style.DescriptionTextView, false));
        descriptionsSwitcher.setCurrentText(getString(descriptions[0]));
    }

    private void initCountryText() {
        countryAnimDuration = getResources().getInteger(R.integer.labels_animation_duration);
        countryOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
        countryOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);
        country1TextView = (TextView) findViewById(R.id.tv_country_1);
        country2TextView = (TextView) findViewById(R.id.tv_country_2);

        country1TextView.setX(countryOffset1);
        country2TextView.setX(countryOffset2);
        country1TextView.setText(countries[0]);
        country2TextView.setAlpha(0f);
        country1TextView.setSelected(true);
        country2TextView.setSelected(true);
        country1TextView.setTypeface(Typeface.createFromAsset(getAssets(), "font/open-sans-extrabold.ttf"));
        country2TextView.setTypeface(Typeface.createFromAsset(getAssets(), "font/open-sans-extrabold.ttf"));
    }

    private void setCountryText(String text, boolean left2right) {
        final TextView invisibleText;
        final TextView visibleText;
        if (country1TextView.getAlpha() > country2TextView.getAlpha()) {
            visibleText = country1TextView;
            invisibleText = country2TextView;
        } else {
            visibleText = country2TextView;
            invisibleText = country1TextView;
        }

        final int vOffset;
        if (left2right) {
            invisibleText.setX(0);
            vOffset = countryOffset2;
        } else {
            invisibleText.setX(countryOffset2);
            vOffset = 0;
        }

        invisibleText.setText(text);

        final ObjectAnimator iAlpha = ObjectAnimator.ofFloat(invisibleText, "alpha", 1f);
        final ObjectAnimator vAlpha = ObjectAnimator.ofFloat(visibleText, "alpha", 0f);
        final ObjectAnimator iX = ObjectAnimator.ofFloat(invisibleText, "x", countryOffset1);
        final ObjectAnimator vX = ObjectAnimator.ofFloat(visibleText, "x", vOffset);

        final AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(iAlpha, vAlpha, iX, vX);
        animSet.setDuration(countryAnimDuration);
        animSet.start();
    }

    private void onActiveCardChange() {
        final int pos = layoutManger.getActiveCardPosition();
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return;
        }

        onActiveCardChange(pos);
    }

    private void onActiveCardChange(int pos) {
        int animH[] = new int[] {R.anim.slide_in_right, R.anim.slide_out_left};
        int animV[] = new int[] {R.anim.slide_in_top, R.anim.slide_out_bottom};

        final boolean left2right = pos < currentPosition;
        if (left2right) {
            animH[0] = R.anim.slide_in_left;
            animH[1] = R.anim.slide_out_right;

            animV[0] = R.anim.slide_in_bottom;
            animV[1] = R.anim.slide_out_top;
        }

        setCountryText(countries[pos % countries.length], left2right);

        temperatureSwitcher.setInAnimation(MainActivity.this, animH[0]);
        temperatureSwitcher.setOutAnimation(MainActivity.this, animH[1]);
        temperatureSwitcher.setText(temperatures[pos % temperatures.length]);

        placeSwitcher.setInAnimation(MainActivity.this, animV[0]);
        placeSwitcher.setOutAnimation(MainActivity.this, animV[1]);
        placeSwitcher.setText(places[pos % places.length]);

        clockSwitcher.setInAnimation(MainActivity.this, animV[0]);
        clockSwitcher.setOutAnimation(MainActivity.this, animV[1]);
        clockSwitcher.setText(times[pos % times.length]);

        descriptionsSwitcher.setText(getString(descriptions[pos % descriptions.length]));

        currentPosition = pos;
    }

    @Override
    public void onClick(View view) {
        if (view == itemHome){
        }else if (view == itemLogin){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);

            startActivityForResult(intent,REQUEST_LOGIN);

        }else if (view == itemCalendar){
            Intent intent = new Intent(this,AuthorInformationActivity.class);
            startActivity(intent);
        }else if (view == itemSettings){
            Toast.makeText(this, "♥ Function Coming Soon ♥", Toast.LENGTH_SHORT).show();
        } else if(view == itemShare){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Xài cái ứng dụng Smart Hospital đi bạn eei ! Đảm bảo xài là ghiền! Quẹo lựa qoẹo lựa bạn eei :D  \n https://www.uit.edu.vn/");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
        }

        resideMenu.closeMenu();
    }

    private class TextViewFactory implements  ViewSwitcher.ViewFactory {

        @StyleRes final int styleId;
        final boolean center;

        TextViewFactory(@StyleRes int styleId, boolean center) {
            this.styleId = styleId;
            this.center = center;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View makeView() {
            final TextView textView = new TextView(MainActivity.this);

            if (center) {
                textView.setGravity(Gravity.CENTER);
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                textView.setTextAppearance(MainActivity.this, styleId);
            } else {
                textView.setTextAppearance(styleId);
            }

            return textView;
        }

    }

    private class ImageViewFactory implements ViewSwitcher.ViewFactory {
        @Override
        public View makeView() {
            final ImageView imageView = new ImageView(MainActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            final LayoutParams lp = new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(lp);

            return imageView;
        }
    }

    private class OnCardClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            final CardSliderLayoutManager lm =  (CardSliderLayoutManager) recyclerView.getLayoutManager();

            if (lm.isSmoothScrolling()) {
                return;
            }

            final int activeCardPosition = lm.getActiveCardPosition();
            if (activeCardPosition == RecyclerView.NO_POSITION) {
                return;
            }
            final int clickedPosition = recyclerView.getChildAdapterPosition(view);
            if (clickedPosition == activeCardPosition) {
                final Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent);
                } else {
                    final CardView cardView = (CardView) view;
                    final View sharedView = cardView.getChildAt(cardView.getChildCount() - 1);
                    final ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(MainActivity.this, sharedView, "shared");
                    startActivity(intent, options.toBundle());
                }
            } else if (clickedPosition > activeCardPosition) {
                recyclerView.smoothScrollToPosition(clickedPosition);
                onActiveCardChange(clickedPosition);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_LOGIN ){
                topBar.setImageViewRightVisible(View.VISIBLE,View.VISIBLE);
                itemLogin.setTitle("Đăng Xuất");
                itemLogin.setIcon(R.drawable.ic_user_sign_out);
                itemLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogPositiveNegative dialog = new DialogPositiveNegative(MainActivity.this, "Smart Hospital", "Bạn có chắc chắn muốn đăng xuất?", "OK","Cancel");
                        dialog.show();
                        dialog.setOnIPositiveNegativeDialogListener(new DialogPositiveNegative.IPositiveNegativeDialogListener() {
                            @Override
                            public void onIPositiveNegativeDialogAnswerPositive(DialogPositiveNegative dialog) {
                                topBar.setImageViewRightVisible(View.VISIBLE,View.GONE);
                                itemLogin.setTitle("Đăng Nhập");
                                itemLogin.setIcon(R.drawable.icon_profile);
                                dialog.dismiss();
                                resideMenu.closeMenu();
                                itemLogin.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                                        startActivityForResult(intent,REQUEST_LOGIN);
                                    }
                                });
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
}
