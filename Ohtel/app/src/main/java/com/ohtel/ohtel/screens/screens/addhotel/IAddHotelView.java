package com.ohtel.ohtel.screens.screens.addhotel;

import com.ohtel.ohtel.screens.screens.addhotel.model.AddHotelModel;

public interface IAddHotelView {

    void showProgressBar();

    void hideProgressBar();

    void addHotelData(AddHotelModel model);
}
