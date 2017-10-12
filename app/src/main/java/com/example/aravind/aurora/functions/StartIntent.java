package com.example.aravind.aurora.functions;

import android.app.Activity;
import android.content.Intent;

import com.example.aravind.aurora.activities.LoginActivity;
import com.example.aravind.aurora.activities.SplashScreenActivity;


public class StartIntent{

    public static void startLoginActivity(Activity activity){

        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void startSplashActivity(Activity activity){

        Intent intent = new Intent(activity, SplashScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();
    }


}
