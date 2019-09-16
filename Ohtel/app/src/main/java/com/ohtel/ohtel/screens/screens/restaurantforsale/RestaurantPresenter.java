package com.ohtel.ohtel.screens.screens.restaurantforsale;

import android.util.Log;

import com.ohtel.ohtel.apipreesenter.APIResponsePresenter;
import com.ohtel.ohtel.apipreesenter.ApiReqType;
import com.ohtel.ohtel.interfaces.IRequestInterface;
import com.ohtel.ohtel.interfaces.IResponseInterface;
import com.ohtel.ohtel.screens.screens.restaurantforsale.model.RestaurantForSaleModel;
import com.ohtel.ohtel.singleton.AppController;

import retrofit.Response;

public class RestaurantPresenter implements IResponseInterface,IRestaurantPresenter {

    private IRequestInterface iRequestInterface;
    private IRestaurantView iRestaurantView;

    public RestaurantPresenter(IRestaurantView iRestaurantView) {
        this.iRequestInterface = new APIResponsePresenter(this);
        this.iRestaurantView = iRestaurantView;
    }

    @Override
    public void onResponseSuccess(Response response, String reqType) {
        iRestaurantView.hideProgressBAr();
        if (response!=null){
            if (reqType.equalsIgnoreCase(ApiReqType.RESTAURANT_DETAILS)){
                RestaurantForSaleModel model = (RestaurantForSaleModel)response.body();
                iRestaurantView.setRestaurantDetails(model);
            }
        }
    }

    @Override
    public void onResponseFailure(String responseError) {
        iRestaurantView.hideProgressBAr();
        Log.e("restaurantFailed :", "onResponseFailure: " );
    }

    @Override
    public void getRestaurantDetails() {
        iRequestInterface.CallApi(AppController.getInstance().service.getRestaurantDetails(), ApiReqType.RESTAURANT_DETAILS);
    }
}
