
package com.ohtel.ohtel.screens.screens.grocery.model;


import com.google.gson.annotations.SerializedName;


public class Datum {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("available")
    private String mAvailable;
    @SerializedName("city")
    private String mCity;
    @SerializedName("contact_no")
    private String mContactNo;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("home_delivery")
    private String mHomeDelivery;
    @SerializedName("id")
    private String mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("items")
    private String mItems;
    @SerializedName("landmark")
    private String mLandmark;
    @SerializedName("shop_name")
    private String mShopName;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getAvailable() {
        return mAvailable;
    }

    public void setAvailable(String available) {
        mAvailable = available;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getContactNo() {
        return mContactNo;
    }

    public void setContactNo(String contactNo) {
        mContactNo = contactNo;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getHomeDelivery() {
        return mHomeDelivery;
    }

    public void setHomeDelivery(String homeDelivery) {
        mHomeDelivery = homeDelivery;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getItems() {
        return mItems;
    }

    public void setItems(String items) {
        mItems = items;
    }

    public String getLandmark() {
        return mLandmark;
    }

    public void setLandmark(String landmark) {
        mLandmark = landmark;
    }

    public String getShopName() {
        return mShopName;
    }

    public void setShopName(String shopName) {
        mShopName = shopName;
    }

}
