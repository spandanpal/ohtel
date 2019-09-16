package com.ohtel.ohtel.screens.screens.grocerydetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.ohtel.ohtel.BaseActivity;
import com.ohtel.ohtel.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GroceryDetailsActivity extends BaseActivity {

    @Bind(R.id.image_back_icon_id)
    AppCompatImageView backIcon;
    @Bind(R.id.toolbar_menu_left)
    AppCompatImageView toolBarMenu;

    @Bind(R.id.grocery_shop_name)
    AppCompatTextView groceryShopName;
    @Bind(R.id.grocery_contact_no)
    AppCompatTextView grocery_contact_no;
    @Bind(R.id.grocery_items)
    AppCompatTextView grocery_items;
    @Bind(R.id.grocery_address)
    AppCompatTextView grocery_address;
    @Bind(R.id.grocery_land_mark)
    AppCompatTextView grocery_land_mark;
    @Bind(R.id.grocery_city)
    AppCompatTextView grocery_city;
    @Bind(R.id.grocery_country)
    AppCompatTextView grocery_country;
    @Bind(R.id.home_delivery)
    AppCompatTextView home_delivery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.grocery_details,rl_base_container);
        setTitle("Grocery Details");
        ButterKnife.bind(this);
        toolBarMenu.setVisibility(View.GONE);
        backIcon.setVisibility(View.VISIBLE);
        if (getIntent()!=null) {
            groceryShopName.setText(getIntent().getStringExtra("shop_name"));
            grocery_items.setText(getIntent().getStringExtra("items"));
            grocery_land_mark.setText(getIntent().getStringExtra("land_mark"));
            grocery_address.setText(getIntent().getStringExtra("address"));
            grocery_city.setText(getIntent().getStringExtra("city"));
            grocery_contact_no.setText(getIntent().getStringExtra("contact_no"));
            grocery_country.setText(getIntent().getStringExtra("country"));
            home_delivery.setText(getIntent().getStringExtra("home_delivery"));
        }
    }
}
