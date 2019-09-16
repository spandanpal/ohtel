package com.ohtel.ohtel.screens.screens.register;

import android.util.Log;

import com.ohtel.ohtel.apipreesenter.APIResponsePresenter;
import com.ohtel.ohtel.apipreesenter.ApiReqType;
import com.ohtel.ohtel.interfaces.IRequestInterface;
import com.ohtel.ohtel.interfaces.IResponseInterface;
import com.ohtel.ohtel.network.APIRequestparam;
import com.ohtel.ohtel.screens.screens.register.model.RegisterModel;
import com.ohtel.ohtel.singleton.AppController;

import retrofit.Response;

public class RegisterPresenter implements IResponseInterface,IRegisterPresenter {

    private IRequestInterface iRequestInterface;
    private IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRequestInterface = new APIResponsePresenter(this);
        this.iRegisterView = iRegisterView;
    }

    @Override
    public void onResponseSuccess(Response response, String reqType) {
        iRegisterView.hideProgressBar();
        if (response!=null){
            if (reqType.equalsIgnoreCase(ApiReqType.USER_REGISTRATION)) {
                RegisterModel model = (RegisterModel) response.body();
                iRegisterView.setRegisterDetails(model);
            }
        }
    }

    @Override
    public void onResponseFailure(String responseError) {
        iRegisterView.hideProgressBar();
        Log.e("registerResponseFailed", "onResponseFailure: " );
    }

    @Override
    public void getRegister(String firstName, String lastName, String email, String hotelName, String password) {
        iRequestInterface.CallApi(AppController.getInstance().service.registerDetails(APIRequestparam.defaultInstance().registerDetails(firstName, lastName, email, hotelName, password)), ApiReqType.USER_REGISTRATION);
    }
}
