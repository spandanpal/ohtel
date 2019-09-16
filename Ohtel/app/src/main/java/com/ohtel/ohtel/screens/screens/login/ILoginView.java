package com.ohtel.ohtel.screens.screens.login;

import com.ohtel.ohtel.screens.screens.login.model.LoginModel;

public interface ILoginView {

    void setLoginDetails(LoginModel model);

    void showProgressBar();

    void hideProgressBar();
}
