package com.uit.tahitu.hci.smarthospital.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.uit.tahitu.hci.smarthospital.R;
import com.uit.tahitu.hci.smarthospital.timeLineView.fragment.TimeLineAdapter;
import com.uit.tahitu.hci.smarthospital.timeLineView.model.OrderStatus;
import com.uit.tahitu.hci.smarthospital.timeLineView.model.Orientation;
import com.uit.tahitu.hci.smarthospital.timeLineView.model.TimeLineModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimeLineFragment extends Fragment {

    private List<TimeLineModel> mDataList = new ArrayList<>();
    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 2;
    private TimeLineAdapter mTimeLineAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static TimeLineFragment newInstance() {
        return new TimeLineFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_time_line_hospital, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        final List<Object> items = new ArrayList<>();

        for (int i = 0; i < ITEM_COUNT; ++i) {
            items.add(new Object());
        }


        //setup materialviewpager

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

//        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        setDataListItems();
        mTimeLineAdapter = new TimeLineAdapter(mDataList,Orientation.VERTICAL, false);
        mRecyclerView.setAdapter(mTimeLineAdapter);
    }
    private void setDataListItems(){
        mDataList.add(new TimeLineModel("Tiền thân của bệnh viện Từ Dũ là một khu chuyên khoa sản trực thuộc Bệnh viện Lalung Bonnaire (nay là Bệnh viện Chợ Rẫy) ra đời vào năm 1923", "1923-02-11 18:00", OrderStatus.INACTIVE));
        mDataList.add(new TimeLineModel("Maternité Indochinoise (Bảo sanh viện Đông Dương) ra đời", "1937-02-11 18:00", OrderStatus.ACTIVE));
        mDataList.add(new TimeLineModel("Bảo sanh viện Đông Dương chính thức hoạt động với khoảng 100 giường bệnh.", "1943-09-11 21:00", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Bệnh viện đổi tên thành Việt Nam Bảo sanh viện", "1944-02-11 18:00", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Bệnh viện được mang tên của thái hậu triều Nguyễn Từ Dụ, tuy nhiên đọc chệch là Từ Dũ và được đổi tên thành Bảo sanh viện Từ Dũ cho đến ngày miền Nam hoàn toàn giải phóng.", "1948-02-11 09:30", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Bệnh viện có 300 giường, đến năm 1972 là 506 giường", "1962-02-11 08:00", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Bệnh viện được đổi tên thành Bệnh viện Từ Dũ với tổng số giường là 1.000 giường.", "2004-02-08 15:00", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Chương trình phẫu thuật tạo hình thẩm mỹ đi vào hoạt động", "2009-02-10 14:30", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Phát triển các chuyên khoa điều trị trong lĩnh vực sơ sinh, phụ khoa như : Sơ sinh cực non, Niệu phụ khoa và bệnh lí sàng chậu", "2010-02-08 15:00", OrderStatus.COMPLETED));
    }
}
