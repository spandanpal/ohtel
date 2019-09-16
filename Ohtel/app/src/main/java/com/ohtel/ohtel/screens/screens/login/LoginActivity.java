package com.ohtel.ohtel.screens.screens.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.ohtel.ohtel.R;
import com.ohtel.ohtel.database.SharedPreferenceDB;
import com.ohtel.ohtel.screens.screens.dashboard.DashBoard;
import com.ohtel.ohtel.screens.screens.login.model.LoginModel;
import com.ohtel.ohtel.screens.screens.register.RegisterActivity;
import com.ohtel.ohtel.utils.CommonUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView{

    @Bind({R.id.tv_signup})
    AppCompatTextView tvSignUP;
    @Bind(R.id.btn_login)
    AppCompatButton btnLogin;
    @Bind((R.id.et_email))
    AppCompatEditText etEmail;
    @Bind((R.id.et_password))
    AppCompatEditText etPassword;
    private ILoginPresenter iLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (SharedPreferenceDB.defaultInstance().checkLogin(this)){
            startActivity(new Intent(this,DashBoard.class));
        }
        iLoginPresenter = new LoginPresenter(this);
    }

    @OnClick({R.id.tv_signup,R.id.btn_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_signup:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.btn_login:
                if (etEmail.getText().toString().length()==0){
                    etEmail.setError(getString(R.string.pls_enter_email));
                }else  if (etPassword.getText().toString().length()==0){
                    etPassword.setError(getString(R.string.pls_enter_password));
                }else {
                    callApi();
                }
                break;
        }
    }

    private void callApi() {
        showProgressBar();
        iLoginPresenter.getLoginDetails(etEmail.getText().toString(),etPassword.getText().toString());
    }

    @Override
    public void setLoginDetails(LoginModel model) {
        if (model.getStatus().equalsIgnoreCase("Success")) {
            SharedPreferenceDB.defaultInstance().saveLogin(this, true);
            Intent intent = new Intent(this, DashBoard.class);
            startActivity(intent);
        } else if (model.getStatus().equalsIgnoreCase("Failed")) {
            Toast.makeText(this, model.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgressBar() {
        CommonUtils.showProgressBar(this);
    }

    @Override
    public void hideProgressBar() {
        CommonUtils.dismissProgressDialog();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SharedPreferenceDB.defaultInstance().checkLogin(this)){
            startActivity(new Intent(this,DashBoard.class));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SharedPreferenceDB.defaultInstance().checkLogin(this)){
            startActivity(new Intent(this,DashBoard.class));
        }
    }
}
