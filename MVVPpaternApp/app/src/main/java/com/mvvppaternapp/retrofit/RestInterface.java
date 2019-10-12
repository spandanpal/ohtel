package com.mvvppaternapp.retrofit;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface RestInterface {


    @FormUrlEncoded
    @POST(Constant.LOGIN)
    Call<ResponseBody> Apilogin(@Field(Constant.MOBILENO) String MobileNo,
                                @Field(Constant.PASSWORD) String password


    );






}

