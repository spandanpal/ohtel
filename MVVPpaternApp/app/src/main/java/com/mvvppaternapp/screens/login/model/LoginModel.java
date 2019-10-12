package com.mvvppaternapp.screens.login.model;

import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("type")
    private String mType;

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }
}
