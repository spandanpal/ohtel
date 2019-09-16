package com.ohtel.ohtel.screens.screens.grocery;

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
import com.ohtel.ohtel.screens.screens.addgrocery.AddGroceryActivity;
import com.ohtel.ohtel.screens.screens.grocery.adapter.GroceryAdapter;
import com.ohtel.ohtel.screens.screens.grocery.model.GroceryModel;
import com.ohtel.ohtel.utils.CommonUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GroceryActivity extends BaseActivity implements IGroceryView{

    @Bind(R.id.image_back_icon_id)
    AppCompatImageView backIcon;
    @Bind(R.id.toolbar_menu_left)
    AppCompatImageView toolBarMenu;
    @Bind(R.id.grocery_recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.add_grocery)
    AppCompatImageView addGrocery;
    private IGroceryPresenter iGroceryPresenter;
    private GroceryAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.grocery_activity,rl_base_container);
        setTitle("Grocery");
        ButterKnife.bind(this);
        toolBarMenu.setVisibility(View.VISIBLE);
        backIcon.setVisibility(View.GONE);
        init();

        addGrocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GroceryActivity.this,AddGroceryActivity.class));
            }
        });
    }

    private void init() {
        iGroceryPresenter = new GroceryPresenter(this);
        showProgressBar();
        iGroceryPresenter.getGroceryDetails();

        adapter = new GroceryAdapter(this,iGroceryPresenter);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
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
    public void setData(GroceryModel model) {
        if (model!=null){
            adapter.setData(model.getData());
        }
    }
}
