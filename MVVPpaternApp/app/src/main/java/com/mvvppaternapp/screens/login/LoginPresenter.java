package com.mvvppaternapp.screens.login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mvvppaternapp.apipreesenter.APIResponsePresenter;
import com.mvvppaternapp.apipreesenter.ApiReqType;
import com.mvvppaternapp.interfaces.IRequestInterface;
import com.mvvppaternapp.interfaces.IResponseInterface;
import com.mvvppaternapp.network.APIRequestparam;

import com.mvvppaternapp.retrofit.Constant;
import com.mvvppaternapp.retrofit.RestInterface;
import com.mvvppaternapp.retrofit.RetrofitError;
import com.mvvppaternapp.retrofit.RetrofitUtil;
import com.mvvppaternapp.screens.login.model.LoginModel;
import com.mvvppaternapp.singleton.AppController;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class LoginPresenter implements ILoginPresenter/*, IResponseInterface*/ {

    private IRequestInterface iRequestInterface;
    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
       // this.iRequestInterface = new APIResponsePresenter(this);
        this.iLoginView = iLoginView;
    }

   /* @Override
    public void onResponseSuccess(Response response, String reqType) {

        if (response != null){
            if (reqType.equalsIgnoreCase(ApiReqType.LOGIN)){
                LoginModel model = (LoginModel) response.body();
                iLoginView.setLoginDetails(model);
            }
        }
    }

    @Override
    public void onResponseFailure(String responseError) {

        Log.i("Mono-->LoginPresenter::","" + responseError);
    }
*/
    @Override
    public void getLoginDetails(String phone, String password) {
        RestInterface service = RetrofitUtil.retrofit(Constant.BASE_URL);
        Call<ResponseBody> call = service.Apilogin(phone, password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String r = response.body().string();
                        Log.d("Response", r);
                        JSONObject jsonObject = new JSONObject(r);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                String str = RetrofitError.showErrorMessage(t);

            }
        });

      //  iRequestInterface.CallApi(AppController.getInstance().service.loginDetails(APIRequestparam.defaultInstance().loginDetails(phone, password)), ApiReqType.LOGIN);
    }
}
