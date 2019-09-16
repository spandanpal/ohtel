package com.ohtel.ohtel.screens.screens.restaurantforsale;

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
import com.ohtel.ohtel.screens.screens.addrestaurant.AddRestaurant;
import com.ohtel.ohtel.screens.screens.restaurantforsale.adapter.RestaurantForSaleAdapter;
import com.ohtel.ohtel.screens.screens.restaurantforsale.model.RestaurantForSaleModel;
import com.ohtel.ohtel.utils.CommonUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RestaurantForSaleFragment extends BaseActivity implements IRestaurantView {

    @Bind(R.id.restaurant_recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.add_restaurant)
    AppCompatImageView btnAddRestaurant;
    @Bind(R.id.image_back_icon_id)
    AppCompatImageView backIcon;
    @Bind(R.id.toolbar_menu_left)
    AppCompatImageView toolBarMenu;
    private IRestaurantPresenter iRestaurantPresenter;
    private RestaurantForSaleAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.fragment_restaurant_for_sale,rl_base_container);
        setTitle("Restaurant For Sale");
        ButterKnife.bind(this);
        toolBarMenu.setVisibility(View.VISIBLE);
        backIcon.setVisibility(View.GONE);
        init();

    }

    private void init() {
        iRestaurantPresenter = new RestaurantPresenter(this);
        showProgressBat();
        iRestaurantPresenter.getRestaurantDetails();

        adapter = new RestaurantForSaleAdapter(this,iRestaurantPresenter);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }


    @OnClick(R.id.add_restaurant)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.add_restaurant:
                startActivity(new Intent(this, AddRestaurant.class));
                break;
        }
    }

    @Override
    public void showProgressBat() {
        CommonUtils.showProgressBar(this);
    }

    @Override
    public void hideProgressBAr() {
        CommonUtils.dismissProgressDialog();
    }

    @Override
    public void setRestaurantDetails(RestaurantForSaleModel model) {
        if (model!=null){
            adapter.setRestaurantData(model.getData());
        }
    }
}
