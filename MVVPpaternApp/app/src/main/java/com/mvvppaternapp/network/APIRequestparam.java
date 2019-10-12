package com.mvvppaternapp.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class APIRequestparam {

    private static APIRequestparam apiRequestparamInstance = null;

    public static APIRequestparam defaultInstance() {
        if (apiRequestparamInstance == null) {
            apiRequestparamInstance = new APIRequestparam();
        }

        return apiRequestparamInstance;
    }

    private JsonObject respParamObj;
    private JsonArray respParamArry;

    public JsonObject loginDetails(String mobile,String password){

        try {
            respParamObj = new JsonObject();
            respParamObj.addProperty("MobileNo", mobile);
            respParamObj.addProperty("password", password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return respParamObj;
    }

    public JsonObject registerDetails(String firstName,String lastName,String email,String hotelName,String password){
        
        try {
            respParamObj = new JsonObject();
            respParamObj.addProperty("f_name", firstName);
            respParamObj.addProperty("l_name", lastName);
            respParamObj.addProperty("email", email);
            respParamObj.addProperty("hotel_name", hotelName);
            respParamObj.addProperty("password", password);

        }catch (Exception e){
            e.printStackTrace();
        }
        return respParamObj;
    }

    public JsonObject addJob(String hotelName,String emailId,String contactNo,String jobName,String noPosition,String location,
                             String address,String qualification,String lastDate){

        try {
            respParamObj = new JsonObject();
            respParamObj.addProperty("hotel_name", hotelName);
            respParamObj.addProperty("hr_mail_id", emailId);
            respParamObj.addProperty("contact_no", contactNo);
            respParamObj.addProperty("job_name", jobName);
            respParamObj.addProperty("no_of_position", noPosition);
            respParamObj.addProperty("location", location);
            respParamObj.addProperty("address", address);
            respParamObj.addProperty("qualification", qualification);
            respParamObj.addProperty("last_date", lastDate);

        }catch (Exception e){
            e.printStackTrace();
        }
        return respParamObj;
    }

    public JsonObject addRestaurant(String restaurantName,String emailId,String contactNo,String alterNo,String noTables,String location,
                             String address,String acNonAc,String restaurantType,String furnishedType,String image){

        try {
            respParamObj = new JsonObject();
            respParamObj.addProperty("restaurant_name", restaurantName);
            respParamObj.addProperty("mail_id", emailId);
            respParamObj.addProperty("contact_no", contactNo);
            respParamObj.addProperty("alternative_no", alterNo);
            respParamObj.addProperty("no_of_tables", noTables);
            respParamObj.addProperty("location", location);
            respParamObj.addProperty("address", address);
            respParamObj.addProperty("ac_non_ac", acNonAc);
            respParamObj.addProperty("resturant_type", restaurantType);
            respParamObj.addProperty("furnished_type", furnishedType);
            respParamObj.addProperty("image", image);
        }catch (Exception e){
            e.printStackTrace();
        }
        return respParamObj;
    }

    public JsonObject addHotel(String hotel_name,String contact_no,String alternative_no,String location,String address,String mail_id,
                                    String no_of_rooms,String no_of_dulex_room,String no_of_suit_room,String furnished_type,String ac_rooms,
                               String built_up_area,String lift_available,String gym_attached,String wifi_provided,String image){

        try {
            respParamObj = new JsonObject();
            respParamObj.addProperty("hotel_name", hotel_name);
            respParamObj.addProperty("contact_no", contact_no);
            respParamObj.addProperty("alternative_no", alternative_no);
            respParamObj.addProperty("location", location);
            respParamObj.addProperty("address", address);
            respParamObj.addProperty("mail_id", mail_id);
            respParamObj.addProperty("no_of_rooms", no_of_rooms);
            respParamObj.addProperty("no_of_dulex_room", no_of_dulex_room);
            respParamObj.addProperty("no_of_suit_room", no_of_suit_room);
            respParamObj.addProperty("furnished_type", furnished_type);
            respParamObj.addProperty("ac_rooms", ac_rooms);
            respParamObj.addProperty("built_up_area", built_up_area);
            respParamObj.addProperty("lift_available", lift_available);
            respParamObj.addProperty("gym_attached", gym_attached);
            respParamObj.addProperty("wifi_provided", wifi_provided);
            respParamObj.addProperty("image", image);
        }catch (Exception e){
            e.printStackTrace();
        }
        return respParamObj;
    }

    public JsonObject addGrocery(String shop_name,String contact_no,String home_delivery,String items,String available,String address,
                               String landmark,String city,String country,String image){

        try {
            respParamObj = new JsonObject();
            respParamObj.addProperty("shop_name", shop_name);
            respParamObj.addProperty("contact_no", contact_no);
            respParamObj.addProperty("home_delivery", home_delivery);
            respParamObj.addProperty("items", items);
            respParamObj.addProperty("available", available);
            respParamObj.addProperty("address", address);
            respParamObj.addProperty("landmark", landmark);
            respParamObj.addProperty("city", city);
            respParamObj.addProperty("country", country);
            respParamObj.addProperty("image", image);
        }catch (Exception e){
            e.printStackTrace();
        }
        return respParamObj;
    }
}
