package com.ohtel.ohtel.screens.screens.addrestaurant;

import android.util.Log;

import com.ohtel.ohtel.apipreesenter.APIResponsePresenter;
import com.ohtel.ohtel.apipreesenter.ApiReqType;
import com.ohtel.ohtel.interfaces.IRequestInterface;
import com.ohtel.ohtel.interfaces.IResponseInterface;
import com.ohtel.ohtel.network.APIRequestparam;
import com.ohtel.ohtel.screens.screens.addrestaurant.model.AddRestaurantModel;
import com.ohtel.ohtel.singleton.AppController;

import retrofit.Response;

public class AddRestaurantPresenter implements IResponseInterface,IAddRestaurantPresenter{

    private IRequestInterface iRequestInterface;
    private IAddRestaurantView iAddRestaurantView;


    public AddRestaurantPresenter(IAddRestaurantView iAddRestaurantView) {
        this.iRequestInterface = new APIResponsePresenter(this);
        this.iAddRestaurantView = iAddRestaurantView;
    }

    @Override
    public void onResponseSuccess(Response response, String reqType) {
        iAddRestaurantView.hideProgressBar();
        if (response!=null){
            if (reqType.equalsIgnoreCase(ApiReqType.ADD_RESTAURANT)){
                AddRestaurantModel model = (AddRestaurantModel) response.body();
                iAddRestaurantView.setResult(model);
            }
        }
    }

    @Override
    public void onResponseFailure(String responseError) {
        iAddRestaurantView.hideProgressBar();
        Log.e("restaurant failed : ", "onResponseFailure: " );
    }

    @Override
    public void addRestaurant(String restaurantName, String emailId, String contactNo, String alterNo, String noTables, String location, String address, String acNonAc, String restaurantType, String furnishedType, String image) {
        iRequestInterface.CallApi(AppController.getInstance().service.addRestaurant(APIRequestparam.defaultInstance().addRestaurant(restaurantName, emailId, contactNo, alterNo, noTables, location, address, acNonAc, restaurantType, furnishedType, image)),ApiReqType.ADD_RESTAURANT);

    }
}
