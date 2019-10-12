package com.mvvppaternapp.apipreesenter;

import com.google.gson.JsonObject;
import com.mvvppaternapp.screens.login.model.LoginModel;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface RestApi {

    @POST(ApiConstants.USER_LOGIN)
    Call<LoginModel> loginDetails(@Body JsonObject jsonObject);


  /*  @POST(ApiConstants.USER_LOGIN)
    Call<LoginModel> loginDetails(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @POST(ApiConstants.USER_REGISTER)
    Call<RegisterModel> registerDetails(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @GET(ApiConstants.JOB_DETAILS)
    Call<JobModel> getJobDetails();

    @Headers("Content-Type: application/json")
    @POST(ApiConstants.ADD_JOB)
    Call<AddJobModel> addJobs(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @GET(ApiConstants.RESTAURANT_DETAILS)
    Call<RestaurantForSaleModel> getRestaurantDetails();

    @Headers("Content-Type: application/json")
    @POST(ApiConstants.ADD_RESTAURANT)
    Call<AddRestaurantModel> addRestaurant(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @GET(ApiConstants.HOTEL_DETAILS)
    Call<HotelForSaleModel> getHotelDetails();

    @Headers("Content-Type: application/json")
    @POST(ApiConstants.ADD_HOTEL)
    Call<AddHotelModel> addHotel(@Body JsonObject jsonObject);

    @Headers("Content-Type: application/json")
    @GET(ApiConstants.GROCERY_DETAILS)
    Call<GroceryModel> getGroceryDetails();

    @Headers("Content-Type: application/json")
    @POST(ApiConstants.ADD_GROCERY)
    Call<AddGroceryModel> addGrocery(@Body JsonObject jsonObject);*/
}
