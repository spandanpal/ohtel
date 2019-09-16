package com.ohtel.ohtel.screens.screens.grocery;

import com.ohtel.ohtel.apipreesenter.APIResponsePresenter;
import com.ohtel.ohtel.apipreesenter.ApiReqType;
import com.ohtel.ohtel.interfaces.IRequestInterface;
import com.ohtel.ohtel.interfaces.IResponseInterface;
import com.ohtel.ohtel.screens.screens.grocery.model.GroceryModel;
import com.ohtel.ohtel.singleton.AppController;

import retrofit.Response;

public class GroceryPresenter implements IResponseInterface,IGroceryPresenter {

    private IGroceryView iGroceryView;
    private IRequestInterface iRequestInterface;

    public GroceryPresenter(IGroceryView iGroceryView) {
        this.iGroceryView = iGroceryView;
        this.iRequestInterface = new APIResponsePresenter(this);
    }

    @Override
    public void onResponseSuccess(Response response, String reqType) {
        iGroceryView.hideProgressBar();
        if (response!=null){
            if (reqType.equalsIgnoreCase(ApiReqType.GROCERY_DETAILS)){
                GroceryModel model = (GroceryModel) response.body();
                iGroceryView.setData(model);
            }
        }
    }

    @Override
    public void onResponseFailure(String responseError) {
        iGroceryView.hideProgressBar();
    }

    @Override
    public void getGroceryDetails() {
        iRequestInterface.CallApi(AppController.getInstance().service.getGroceryDetails(),ApiReqType.GROCERY_DETAILS);
    }
}
