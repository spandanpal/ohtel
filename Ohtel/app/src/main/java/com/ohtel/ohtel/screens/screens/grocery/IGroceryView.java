package com.ohtel.ohtel.screens.screens.grocery;

import com.ohtel.ohtel.screens.screens.grocery.model.GroceryModel;

public interface IGroceryView {

    void showProgressBar();

    void hideProgressBar();

    void setData(GroceryModel model);
}
