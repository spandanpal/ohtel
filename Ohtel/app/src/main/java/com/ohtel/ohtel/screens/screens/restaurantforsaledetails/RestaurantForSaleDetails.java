package com.ohtel.ohtel.screens.screens.restaurantforsaledetails;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.ohtel.ohtel.BaseActivity;
import com.ohtel.ohtel.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RestaurantForSaleDetails extends BaseActivity {

    @Bind(R.id.image_back_icon_id)
    AppCompatImageView backIcon;
    @Bind(R.id.toolbar_menu_left)
    AppCompatImageView toolBarMenu;
    @Bind(R.id.restaurant_address)
    AppCompatTextView restaurant_address;
    @Bind(R.id.restaurant_name)
    AppCompatTextView restaurant_name;
    @Bind(R.id.restaurant_type)
    AppCompatTextView restaurant_type;
    @Bind(R.id.restaurant_email)
    AppCompatTextView restaurant_email;
    @Bind(R.id.restaurant_contact_no)
    AppCompatTextView restaurant_contact_no;
    @Bind(R.id.restaurant_alt_no)
    AppCompatTextView restaurant_alt_no;
    @Bind(R.id.restaurant_no_tables)
    AppCompatTextView restaurant_no_tables;
    @Bind(R.id.restaurant_location)
    AppCompatTextView restaurant_location;
    @Bind(R.id.restaurant_furnished_type)
    AppCompatTextView restaurant_furnished_type;
    @Bind(R.id.restaurant_ac_non_ac)
    AppCompatTextView restaurant_ac_non_ac;
    @Bind(R.id.restaurant_image)
    AppCompatImageView restaurantImage;
    private String acNonAc,strAddress,alternativeNo,contactNo,furnishedType,strLocation,strImage,strMailId,noTabels,restaurantName,restaurantType;
    Bitmap bitmap ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.restaurant_for_sale_details,rl_base_container);
        setTitle("Restaurant For Sale Details");
        ButterKnife.bind(this);
        toolBarMenu.setVisibility(View.GONE);
        backIcon.setVisibility(View.VISIBLE);
        if (getIntent()!=null){

            acNonAc = getIntent().getStringExtra("ac_non_ac");
            strAddress = getIntent().getStringExtra("address");
            alternativeNo = getIntent().getStringExtra("alternative_no");
            contactNo = getIntent().getStringExtra("contact_no");
            furnishedType = getIntent().getStringExtra("furnished_type");
            strLocation = getIntent().getStringExtra("location");
//            strImage = getIntent().getStringExtra("image");
            strMailId = getIntent().getStringExtra("mail_id");
            noTabels = getIntent().getStringExtra("no_tables");
            restaurantName = getIntent().getStringExtra("restaurant_name");
            restaurantType = getIntent().getStringExtra("restaurant_type");

            if (getIntent().hasExtra("image")){
                Bitmap _bitmap = BitmapFactory.decodeByteArray(
                        getIntent().getByteArrayExtra("image"),0,getIntent().getByteArrayExtra("image").length);
                restaurantImage.setImageBitmap(_bitmap);
            }
        }

        restaurant_ac_non_ac.setText(acNonAc);
        restaurant_address.setText(strAddress);
        restaurant_alt_no.setText(alternativeNo);
        restaurant_contact_no.setText(contactNo);
        restaurant_furnished_type.setText(furnishedType);
        restaurant_location.setText(strLocation);
//        restaurant_ac_non_ac.setText(strImage);
        restaurant_email.setText(strMailId);
        restaurant_no_tables.setText(noTabels);
        restaurant_name.setText(restaurantName);
        restaurant_type.setText(restaurantType);
        }
}
