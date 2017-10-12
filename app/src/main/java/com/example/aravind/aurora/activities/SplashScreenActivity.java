package com.example.aravind.aurora.activities;

import android.app.Activity;
import android.os.Bundle;

import com.example.aravind.aurora.R;
import com.example.aravind.aurora.entities.InitApiEntity;
import com.example.aravind.aurora.functions.CommonFunctions;
import com.example.aravind.aurora.functions.UserDetails;


public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splashscreen);
    }

    @Override
    protected void onResume() {
        super.onResume();
        moveToNextActivity();
    }

    public void moveToNextActivity(){

        if(CommonFunctions.isNetworkAvailable(SplashScreenActivity.this)){
            if(UserDetails.isUserLoggeIn(SplashScreenActivity.this)){
                moveToMainActivity();
            }
        }
    }

    public void moveToMainActivity(){

        //REst call goes here
       // InitApiEntity initApiEntity = new InitApiEntity(UserDetails.getUserId(SplashScreenActivity.this));



    }
}
