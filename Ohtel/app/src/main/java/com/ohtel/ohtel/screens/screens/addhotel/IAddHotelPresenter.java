package com.ohtel.ohtel.screens.screens.addhotel;

public interface IAddHotelPresenter {

    void getHotelDetails(String hotel_name,String contact_no,String alternative_no,String location,String address,String mail_id,
                         String no_of_rooms,String no_of_dulex_room,String no_of_suit_room,String furnished_type,String ac_rooms,
                         String built_up_area,String lift_available,String gym_attached,String wifi_provided,String image);
}
