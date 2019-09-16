package com.ohtel.ohtel.screens.screens.addhotel;

import android.util.Log;

import com.ohtel.ohtel.apipreesenter.APIResponsePresenter;
import com.ohtel.ohtel.apipreesenter.ApiReqType;
import com.ohtel.ohtel.interfaces.IRequestInterface;
import com.ohtel.ohtel.interfaces.IResponseInterface;
import com.ohtel.ohtel.network.APIRequestparam;
import com.ohtel.ohtel.screens.screens.addhotel.model.AddHotelModel;
import com.ohtel.ohtel.singleton.AppController;

import retrofit.Response;

public class AddHotelPresenter implements IResponseInterface,IAddHotelPresenter{

    private IRequestInterface iRequestInterface;
    private IAddHotelView iAddHotelView;

    public AddHotelPresenter(IAddHotelView iAddHotelView) {
        this.iRequestInterface = new APIResponsePresenter(this);
        this.iAddHotelView = iAddHotelView;
    }

    @Override
    public void onResponseSuccess(Response response, String reqType) {
        iAddHotelView.hideProgressBar();
        if (response!=null){
            if (reqType.equalsIgnoreCase(ApiReqType.ADD_HOTEL)){
                AddHotelModel model = (AddHotelModel) response.body();
                iAddHotelView.addHotelData(model);
            }
        }
    }

    @Override
    public void onResponseFailure(String responseError) {
        iAddHotelView.hideProgressBar();
        Log.e("add hotel error", "onResponseFailure: " );
    }

    @Override
    public void getHotelDetails(String hotel_name, String contact_no, String alternative_no, String location, String address, String mail_id, String no_of_rooms, String no_of_dulex_room, String no_of_suit_room, String furnished_type, String ac_rooms, String built_up_area, String lift_available, String gym_attached, String wifi_provided, String image) {
        iRequestInterface.CallApi(AppController.getInstance().service.addHotel(APIRequestparam.defaultInstance().addHotel(hotel_name, contact_no, alternative_no, location, address, mail_id, no_of_rooms, no_of_dulex_room, no_of_suit_room, furnished_type, ac_rooms, built_up_area, lift_available, gym_attached, wifi_provided, image)),ApiReqType.ADD_HOTEL);
    }
}
