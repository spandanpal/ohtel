package com.ohtel.ohtel.screens.screens.register;

import com.ohtel.ohtel.screens.screens.register.model.RegisterModel;

public interface IRegisterView {

    void setRegisterDetails(RegisterModel model);

    void showProgressBar();

    void hideProgressBar();
}
