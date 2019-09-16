package com.ohtel.ohtel.screens.screens.restaurantforsale;

import com.ohtel.ohtel.screens.screens.restaurantforsale.model.RestaurantForSaleModel;

public interface IRestaurantView {

    void showProgressBat();

    void hideProgressBAr();

    void setRestaurantDetails(RestaurantForSaleModel model);
}
