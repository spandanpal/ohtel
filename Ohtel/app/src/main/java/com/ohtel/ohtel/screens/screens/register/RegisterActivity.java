package com.ohtel.ohtel.screens.screens.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.ohtel.ohtel.R;
import com.ohtel.ohtel.screens.screens.login.LoginActivity;
import com.ohtel.ohtel.screens.screens.register.model.RegisterModel;
import com.ohtel.ohtel.utils.CommonUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements IRegisterView{

    @Bind(R.id.tv_login)
    AppCompatTextView tvLogin;
    @Bind(R.id.btn_register)
    AppCompatButton btnRegister;
    @Bind(R.id.et_first_name)
    AppCompatEditText etFirstName;
    @Bind(R.id.et_last_name)
    AppCompatEditText etLastName;
    @Bind(R.id.et_email)
    AppCompatEditText etEmail;
    @Bind(R.id.et_password)
    AppCompatEditText etPassword;
    @Bind(R.id.et_confirm_password)
    AppCompatEditText etConfirmPassword;
    private String password,confirmPassword;
    private IRegisterPresenter iRegisterPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        password = etPassword.getText().toString();
        confirmPassword = etConfirmPassword.getText().toString();
        iRegisterPresenter = new RegisterPresenter(this);
    }

    @OnClick({R.id.tv_login,R.id.btn_register})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btn_register:
                if (etFirstName.getText().toString().length()==0){
                    etFirstName.setError(getString(R.string.pls_enter_first_name));
                }else if (etLastName.getText().toString().length()==0){
                    etLastName.setError(getString(R.string.pls_enter_last_name));
                }else if (etEmail.getText().toString().length()==0){
                    etEmail.setError(getString(R.string.pls_enter_email));
                }else if (etPassword.getText().toString().length()==0){
                    etPassword.setError(getString(R.string.pls_enter_password));
                }else if (etConfirmPassword.getText().toString().length()==0){
                    etConfirmPassword.setError(getString(R.string.pls_enter_password));
                }else if (!confirmPassword.matches(password)){
                    etConfirmPassword.setError(getString(R.string.password_not_matches));
                }else {
                    callApi();
                }
                break;
        }
    }

    private void callApi() {
        showProgressBar();
        iRegisterPresenter.getRegister(etFirstName.getText().toString(),etLastName.getText().toString(),etEmail.getText().toString(),
                                        "ohtel",etPassword.getText().toString());
    }

    @Override
    public void setRegisterDetails(RegisterModel model) {
        if (model.getStatus().equalsIgnoreCase("Success")){
            Toast.makeText(this, model.getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }else if (model.getStatus().equalsIgnoreCase("failed")){
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
}
