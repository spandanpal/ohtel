package com.ohtel.ohtel.screens.screens.addrestaurant;

import com.ohtel.ohtel.screens.screens.addrestaurant.model.AddRestaurantModel;

public interface IAddRestaurantView {

    void showProgressBar();

    void hideProgressBar();

    void setResult(AddRestaurantModel model);
}
