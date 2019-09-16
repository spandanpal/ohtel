package com.ohtel.ohtel.screens.screens.hotelforsale;

import com.ohtel.ohtel.screens.screens.hotelforsale.model.HotelForSaleModel;

public interface IHotelForSaleView {

    void showProgressBar();

    void hideProgressBar();

    void setHotelData(HotelForSaleModel model);
}
