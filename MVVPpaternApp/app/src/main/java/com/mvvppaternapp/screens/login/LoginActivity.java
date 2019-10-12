package com.mvvppaternapp.screens.login;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.mvvppaternapp.R;
import com.mvvppaternapp.screens.dashBoard.DashBoardActivity;
import com.mvvppaternapp.screens.login.model.LoginModel;

public class LoginActivity extends AppCompatActivity implements ILoginView{

    @Bind({R.id.et_phone})
    AppCompatEditText etPhone;
    @Bind({R.id.et_password})
    AppCompatEditText etPassword;
    @Bind({R.id.btn_login})
    AppCompatButton btnLogin;
    private ILoginPresenter iLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        iLoginPresenter = new LoginPresenter(this);
    }
    @OnClick({R.id.btn_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
                if (etPhone.getText().toString().length() == 0) {
                    etPhone.setError("Please Enter Phone Number");
                }
                else if(etPassword.getText().toString().length() == 0){
                    etPassword.setError("Please Enter Password");
                }
                else {
                    callApi();
                }
                break;
        }
    }

    private void callApi(){
        iLoginPresenter.getLoginDetails(etPhone.getText().toString(), etPassword.getText().toString());
    }

    @Override
    public void setLoginDetails(LoginModel model) {
        if (model.getmType().equalsIgnoreCase("sucess")){
            Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Please try again!!", Toast.LENGTH_SHORT).show();
        }
    }
}
