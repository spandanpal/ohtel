package com.ohtel.ohtel.screens.screens.hotelforsale;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ohtel.ohtel.BaseActivity;
import com.ohtel.ohtel.R;
import com.ohtel.ohtel.screens.screens.addhotel.AddHotelActivity;
import com.ohtel.ohtel.screens.screens.hotelforsale.adapter.HotelForSaleAdapter;
import com.ohtel.ohtel.screens.screens.hotelforsale.model.HotelForSaleModel;
import com.ohtel.ohtel.utils.CommonUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HotelForSaleActivity extends BaseActivity implements IHotelForSaleView{

    @Bind(R.id.image_back_icon_id)
    AppCompatImageView backIcon;
    @Bind(R.id.toolbar_menu_left)
    AppCompatImageView toolBarMenu;
    @Bind(R.id.hotel_recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.add_hotel)
    AppCompatImageView addHotel;
    private HotelForSaleAdapter adapter;
    private IHotelForSalePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_hotel_for_sale,rl_base_container);
        setTitle("Hotel For Sale");
        ButterKnife.bind(this);
        toolBarMenu.setVisibility(View.VISIBLE);
        backIcon.setVisibility(View.GONE);
        init();
    }

    private void init() {
        presenter = new HotelForSalePresenter(this);
        showProgressBar();
        presenter.getHotelData();

        adapter = new HotelForSaleAdapter(this,presenter);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.add_hotel)
    public void onClick(View v){
        switch (v.getId()){
            case R.id.add_hotel:
                startActivity(new Intent(this,AddHotelActivity.class));
                break;
        }
    }

    @Override
    public void showProgressBar() {
        CommonUtils.showProgressBar(this);
    }

    @Override
    public void hideProgressBar() {
        CommonUtils.dismissProgressDialog();
    }

    @Override
    public void setHotelData(HotelForSaleModel model) {
        if (model!=null){
            adapter.setData(model.getData());
        }
    }
}
