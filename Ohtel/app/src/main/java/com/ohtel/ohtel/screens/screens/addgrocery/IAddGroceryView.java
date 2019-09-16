package com.ohtel.ohtel.screens.screens.addgrocery;

import com.ohtel.ohtel.screens.screens.addgrocery.model.AddGroceryModel;

public interface IAddGroceryView {

    void showProgressBar();

    void hideProgressBar();

    void setData(AddGroceryModel model);
}
