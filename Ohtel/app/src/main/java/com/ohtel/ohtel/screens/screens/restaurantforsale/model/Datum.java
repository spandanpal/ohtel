package com.ohtel.ohtel.screens.screens.restaurantforsale.model;

import com.google.gson.annotations.SerializedName;

public class Datum {


    @SerializedName("ac_non_ac")
    private String mAcNonAc;
    @SerializedName("address")
    private String mAddress;
    @SerializedName("alternative_no")
    private String mAlternativeNo;
    @SerializedName("contact_no")
    private String mContactNo;
    @SerializedName("furnished_type")
    private String mFurnishedType;
    @SerializedName("Id")
    private String mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("location")
    private String mLocation;
    @SerializedName("mail_id")
    private String mMailId;
    @SerializedName("no_of_tables")
    private String mNoOfTables;
    @SerializedName("restaurant_name")
    private String mRestaurantName;
    @SerializedName("resturant_type")
    private String mResturantType;

    public String getAcNonAc() {
        return mAcNonAc;
    }

    public void setAcNonAc(String acNonAc) {
        mAcNonAc = acNonAc;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getAlternativeNo() {
        return mAlternativeNo;
    }

    public void setAlternativeNo(String alternativeNo) {
        mAlternativeNo = alternativeNo;
    }

    public String getContactNo() {
        return mContactNo;
    }

    public void setContactNo(String contactNo) {
        mContactNo = contactNo;
    }

    public String getFurnishedType() {
        return mFurnishedType;
    }

    public void setFurnishedType(String furnishedType) {
        mFurnishedType = furnishedType;
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

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getMailId() {
        return mMailId;
    }

    public void setMailId(String mailId) {
        mMailId = mailId;
    }

    public String getNoOfTables() {
        return mNoOfTables;
    }

    public void setNoOfTables(String noOfTables) {
        mNoOfTables = noOfTables;
    }

    public String getRestaurantName() {
        return mRestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        mRestaurantName = restaurantName;
    }

    public String getResturantType() {
        return mResturantType;
    }

    public void setResturantType(String resturantType) {
        mResturantType = resturantType;
    }
}
