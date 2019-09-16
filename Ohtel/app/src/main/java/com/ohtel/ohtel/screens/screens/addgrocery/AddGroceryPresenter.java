package com.ohtel.ohtel.screens.screens.addgrocery;

import com.ohtel.ohtel.apipreesenter.APIResponsePresenter;
import com.ohtel.ohtel.apipreesenter.ApiReqType;
import com.ohtel.ohtel.interfaces.IRequestInterface;
import com.ohtel.ohtel.interfaces.IResponseInterface;
import com.ohtel.ohtel.network.APIRequestparam;
import com.ohtel.ohtel.screens.screens.addgrocery.model.AddGroceryModel;
import com.ohtel.ohtel.singleton.AppController;

import retrofit.Response;

public class AddGroceryPresenter implements IResponseInterface,IAddGroceryPresenter {

    private IRequestInterface iRequestInterface;
    private IAddGroceryView iAddGroceryView;

    public AddGroceryPresenter(IAddGroceryView iAddGroceryView) {
        this.iRequestInterface = new APIResponsePresenter(this);
        this.iAddGroceryView = iAddGroceryView;
    }

    @Override
    public void onResponseSuccess(Response response, String reqType) {
        iAddGroceryView.hideProgressBar();
        if (response!=null){
            if (reqType.equalsIgnoreCase(ApiReqType.ADD_GROCERY)) {
                AddGroceryModel model = (AddGroceryModel) response.body();
                iAddGroceryView.setData(model);
            }
        }
    }

    @Override
    public void onResponseFailure(String responseError) {
        iAddGroceryView.hideProgressBar();
    }

    @Override
    public void addData(String shop_name, String contact_no, String home_delivery, String items, String available, String address, String landmark, String city, String country, String image) {
            iRequestInterface.CallApi(AppController.getInstance().service.addGrocery(APIRequestparam.defaultInstance().addGrocery(shop_name, contact_no, home_delivery, items, available, address, landmark, city, country, image)), ApiReqType.ADD_GROCERY);
    }
}
