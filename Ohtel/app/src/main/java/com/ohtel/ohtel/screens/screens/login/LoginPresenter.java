package com.ohtel.ohtel.screens.screens.login;

import android.util.Log;

import com.ohtel.ohtel.apipreesenter.APIResponsePresenter;
import com.ohtel.ohtel.apipreesenter.ApiReqType;
import com.ohtel.ohtel.interfaces.IRequestInterface;
import com.ohtel.ohtel.interfaces.IResponseInterface;
import com.ohtel.ohtel.network.APIRequestparam;
import com.ohtel.ohtel.screens.screens.login.model.LoginModel;
import com.ohtel.ohtel.singleton.AppController;

import retrofit.Response;

public class LoginPresenter implements ILoginPresenter,IResponseInterface {

    private IRequestInterface iRequestInterface;
    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
        this.iRequestInterface = new APIResponsePresenter(this);
        this.iLoginView = iLoginView;
    }

    @Override
    public void onResponseSuccess(Response response, String reqType) {
        iLoginView.hideProgressBar();
        if (response!=null){
            if (reqType.equalsIgnoreCase(ApiReqType.LOGIN)){
                LoginModel model = (LoginModel) response.body();
                iLoginView.setLoginDetails(model);
            }
        }
    }

    @Override
    public void onResponseFailure(String responseError) {
        iLoginView.hideProgressBar();
        Log.e("failed", "onResponseFailure: "  );
    }

    @Override
    public void getLoginDetails(String email, String password) {
        iRequestInterface.CallApi(AppController.getInstance().service.loginDetails(APIRequestparam.defaultInstance().loginDetails(email, password)), ApiReqType.LOGIN);
    }
}
