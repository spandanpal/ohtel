
package com.ohtel.ohtel.screens.screens.jobs.model;

import com.google.gson.annotations.SerializedName;


public class Datum {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("contact_no")
    private String mContactNo;
    @SerializedName("hotel_name")
    private String mHotelName;
    @SerializedName("hr_mail_id")
    private String mHrMailId;
    @SerializedName("id")
    private String mId;
    @SerializedName("job_name")
    private String mJobName;
    @SerializedName("last_date")
    private String mLastDate;
    @SerializedName("location")
    private String mLocation;
    @SerializedName("no_of_position")
    private String mNoOfPosition;
    @SerializedName("qualification")
    private String mQualification;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getContactNo() {
        return mContactNo;
    }

    public void setContactNo(String contactNo) {
        mContactNo = contactNo;
    }

    public String getHotelName() {
        return mHotelName;
    }

    public void setHotelName(String hotelName) {
        mHotelName = hotelName;
    }

    public String getHrMailId() {
        return mHrMailId;
    }

    public void setHrMailId(String hrMailId) {
        mHrMailId = hrMailId;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getJobName() {
        return mJobName;
    }

    public void setJobName(String jobName) {
        mJobName = jobName;
    }

    public String getLastDate() {
        return mLastDate;
    }

    public void setLastDate(String lastDate) {
        mLastDate = lastDate;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getNoOfPosition() {
        return mNoOfPosition;
    }

    public void setNoOfPosition(String noOfPosition) {
        mNoOfPosition = noOfPosition;
    }

    public String getQualification() {
        return mQualification;
    }

    public void setQualification(String qualification) {
        mQualification = qualification;
    }

}
