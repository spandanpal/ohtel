
package com.ohtel.ohtel.screens.screens.hotelforsale.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class HotelForSaleModel {

    @SerializedName("data")
    private List<Datum> mData;

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

}
