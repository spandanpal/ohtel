
package com.ohtel.ohtel.screens.screens.grocery.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GroceryModel {

    @SerializedName("data")
    private List<Datum> mData;

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

}
