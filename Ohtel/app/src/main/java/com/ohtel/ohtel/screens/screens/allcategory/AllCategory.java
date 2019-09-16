package com.ohtel.ohtel.screens.screens.allcategory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ohtel.ohtel.R;
import com.ohtel.ohtel.screens.screens.hotelforsale.HotelForSaleActivity;
import com.ohtel.ohtel.screens.screens.restaurantforsale.RestaurantForSaleFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllCategory extends Fragment {

   @Bind({R.id.restaurant_for_sale})
   AppCompatImageView restaurantForSale;
    @Bind({R.id.restaurant_less_than_30_rooms})
    AppCompatImageView restaurantLessThan30Rooms;
    @Bind({R.id.restaurant_more_than_30_rooms})
    AppCompatImageView restaurantmoreThan30Rooms;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_category, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick({R.id.restaurant_for_sale,R.id.restaurant_less_than_30_rooms,R.id.restaurant_more_than_30_rooms})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.restaurant_for_sale:
                startActivity(new Intent(getActivity(), RestaurantForSaleFragment.class));
                break;
            case R.id.restaurant_less_than_30_rooms:
                startActivity(new Intent(getActivity(), HotelForSaleActivity.class));
                break;
            case R.id.restaurant_more_than_30_rooms:
                break;
        }
    }
}
