package com.example.aravind.aurora.activities;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.example.aravind.aurora.R;
import com.example.aravind.aurora.RESTcalls.RestCallImplementation;
import com.example.aravind.aurora.constants.Constants;
import com.example.aravind.aurora.entities.LoginEntity;
import com.example.aravind.aurora.functions.CommonFunctions;
import com.example.aravind.aurora.functions.StartIntent;
import com.example.aravind.aurora.functions.UserDetails;

public class LoginActivity extends Activity {


    EditText etUserName,
             etPassword;

    LinearLayout llLoginDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RestCallImplementation.requestWithSomeHttpHeaders_JSON(LoginActivity.this, "Bat");
        setContentView(R.layout.act_login);
        setViewId();
    }

    @Override
    protected void onResume() {
        super.onResume();
        moveToNextActivity();
    }

    public void moveToNextActivity(){
        if(CommonFunctions.isNetworkAvailable(LoginActivity.this)){
            if(UserDetails.isUserLoggeIn(LoginActivity.this)){
                StartIntent.startSplashActivity(LoginActivity.this);
            }
        }else{
            CommonFunctions.toastString(Constants.NO_INTERNET_CONNECTION,LoginActivity.this);
        }
    }

    public void setViewId() {
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        llLoginDetails = (LinearLayout) findViewById(R.id.llLoginDetails);
    }

    public void login(View view) {

        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        if (userName == null || userName.length() < 1) {
            CommonFunctions.toastString("user name cannot be empty", LoginActivity.this);
        } else if (password == null || password.length() < 1) {
            CommonFunctions.toastString("password cannot be empty", LoginActivity.this);
        } else {
            CommonFunctions.showCustomDialog(LoginActivity.this,"Authenticating....");
            LoginEntity loginEntity = new LoginEntity(userName, password);
            RestCallImplementation.onLogin(loginEntity, new LoginEntity.RestCallInterface() {

                @Override
                public void onLogin(LoginEntity loginEntity, VolleyError volleyError) {
                    CommonFunctions.dismissCustomDialog();
                    if (volleyError == null) {
                        if (loginEntity.getAuroraUser().getRole() != null &&
                                !loginEntity.getAuroraUser().getRole().isEmpty() &&
                                (loginEntity.getAuroraUser().getRole().equals(Constants.USER_ROLE_KORE) ||
                                        loginEntity.getAuroraUser().getRole().equals(Constants.USER_ROLE_ORGANISER) ||
                                        loginEntity.getAuroraUser().getRole().equals(Constants.USER_ROLE_COORDINATOR) ||
                                        loginEntity.getAuroraUser().getRole().equals(Constants.USER_ROLE_VOLUNTEER) ||
                                        loginEntity.getAuroraUser().getRole().equals(Constants.USER_ROLE_ADMIN))
                                ) {
                            UserDetails.setUserLoggedIn(LoginActivity.this, true);
                            UserDetails.setUserRole(LoginActivity.this, loginEntity.getAuroraUser().getRole());
                            UserDetails.setUserId(LoginActivity.this,loginEntity.getAuroraUser().getUserId());
                            UserDetails.setUserName(LoginActivity.this, loginEntity.getAuroraUser().getUserName());
                            UserDetails.setUserPassword(LoginActivity.this,loginEntity.getAuroraUser().getPassword());
                            StartIntent.startSplashActivity(LoginActivity.this);
                        }

                    } else if (volleyError != null) {
                        if (loginEntity.getCode() == 401) {
                            CommonFunctions.toastString(loginEntity.getMessage(), LoginActivity.this);
                        }else{
                            CommonFunctions.toastString(Constants.NO_INTERNET_CONNECTION, LoginActivity.this);
                        }
                    }

                }
            }, LoginActivity.this);
        }
    }
}
