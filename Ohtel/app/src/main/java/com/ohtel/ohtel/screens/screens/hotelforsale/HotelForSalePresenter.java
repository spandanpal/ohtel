package com.ohtel.ohtel.screens.screens.hotelforsale;

import android.util.Log;

import com.ohtel.ohtel.apipreesenter.APIResponsePresenter;
import com.ohtel.ohtel.apipreesenter.ApiReqType;
import com.ohtel.ohtel.interfaces.IRequestInterface;
import com.ohtel.ohtel.interfaces.IResponseInterface;
import com.ohtel.ohtel.screens.screens.hotelforsale.model.HotelForSaleModel;
import com.ohtel.ohtel.singleton.AppController;

import retrofit.Response;

public class HotelForSalePresenter implements IResponseInterface,IHotelForSalePresenter {

    private IRequestInterface iRequestInterface;
    private IHotelForSaleView iHotelForSaleView;

    public HotelForSalePresenter(IHotelForSaleView iHotelForSaleView) {
        this.iRequestInterface = new APIResponsePresenter(this);
        this.iHotelForSaleView = iHotelForSaleView;
    }

    @Override
    public void onResponseSuccess(Response response, String reqType) {
        iHotelForSaleView.hideProgressBar();
        if (response!=null){
            if (reqType.equalsIgnoreCase(ApiReqType.HOTEL_DETAILS)){
                HotelForSaleModel model = (HotelForSaleModel) response.body();
                iHotelForSaleView.setHotelData(model);
            }
        }
    }

    @Override
    public void onResponseFailure(String responseError) {
        iHotelForSaleView.hideProgressBar();
        Log.e("hotel detail failed", "onResponseFailure: " );
    }

    @Override
    public void getHotelData() {
        iRequestInterface.CallApi(AppController.getInstance().service.getHotelDetails(),ApiReqType.HOTEL_DETAILS);
    }
}
