package com.ohtel.ohtel.screens.screens.addrestaurant.model;

import com.google.gson.annotations.SerializedName;

public class AddRestaurantModel {

    @SerializedName("Message")
    private String mMessage;
    @SerializedName("Status")
    private String mStatus;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }
}
