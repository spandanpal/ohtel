package com.ohtel.ohtel.screens.screens.hotelforsaledetails;

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

public class HotelForSaleDetailsActivity  extends BaseActivity {

    @Bind(R.id.image_back_icon_id)
    AppCompatImageView backIcon;
    @Bind(R.id.toolbar_menu_left)
    AppCompatImageView toolBarMenu;
    @Bind(R.id.wifi_provided)
    AppCompatTextView wifiProvided;

    @Bind(R.id.gym_attached)
    AppCompatTextView gymAttached;
    @Bind(R.id.lift_available)
    AppCompatTextView liftAvailable;
    @Bind(R.id.built_up_area)
    AppCompatTextView builtUpArea;
    @Bind(R.id.ac_rooms)
    AppCompatTextView acRooms;
    @Bind(R.id.hotel_address)
    AppCompatTextView hotelAddress;
    @Bind(R.id.hotel_furnished_type)
    AppCompatTextView hotelFurnishedType;
    @Bind(R.id.hotel_location)
    AppCompatTextView hotelLocation;
    @Bind(R.id.no_suit_room)
    AppCompatTextView noSuitRoom;
    @Bind(R.id.no_dulex_room)
    AppCompatTextView noDulexRoom;
    @Bind(R.id.no_rooms)
    AppCompatTextView noRooms;
    @Bind(R.id.hotel_email)
    AppCompatTextView hotelEmail;
    @Bind(R.id.hotel_name)
    AppCompatTextView hotelName;
    @Bind(R.id.hotel_contact_no)
    AppCompatTextView hotelContactNo;
    @Bind(R.id.hotel_altr_no)
    AppCompatTextView hotelAltrNo;
    @Bind(R.id.tv_image)
    AppCompatImageView imageView;

    Bitmap bmp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.hotel_for_sale_details,rl_base_container);
        setTitle("Hotel For Sale Details");
        ButterKnife.bind(this);
        toolBarMenu.setVisibility(View.GONE);
        backIcon.setVisibility(View.VISIBLE);
        if (getIntent()!=null) {
            hotelName.setText(getIntent().getStringExtra("hotel_name"));
            hotelContactNo.setText(getIntent().getStringExtra("contact_no"));
            hotelAltrNo.setText(getIntent().getStringExtra("alternative_no"));
            hotelLocation.setText(getIntent().getStringExtra("location"));
            hotelAddress.setText(getIntent().getStringExtra("address"));
            hotelEmail.setText(getIntent().getStringExtra("mail_id"));
            noRooms.setText(getIntent().getStringExtra("no_of_rooms"));
            noDulexRoom.setText(getIntent().getStringExtra("no_of_dulex_room"));
            noSuitRoom.setText(getIntent().getStringExtra("no_of_suit_room"));
            hotelFurnishedType.setText(getIntent().getStringExtra("furnished_type"));
            acRooms.setText(getIntent().getStringExtra("ac_rooms"));
            builtUpArea.setText(getIntent().getStringExtra("built_up_area"));
            liftAvailable.setText(getIntent().getStringExtra("lift_available"));
            gymAttached.setText(getIntent().getStringExtra("gym_attached"));
            wifiProvided.setText(getIntent().getStringExtra("wifi_provided"));

            byte[] byteArray = getIntent().getByteArrayExtra("image");
            bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        }

        imageView.setImageBitmap(bmp);
    }
}
