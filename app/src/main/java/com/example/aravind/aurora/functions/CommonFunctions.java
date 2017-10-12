package com.example.aravind.aurora.functions;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.aravind.aurora.R;
import com.example.aravind.aurora.activities.LoginActivity;
import com.example.aravind.aurora.constants.Constants;

public class CommonFunctions {

    private static  ProgressDialog progressDialog = null;

    public static void toastString(String message, Context context){

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,message,duration);
        toast.show();
    }

    public static void clearLocalCache(Activity activity){

        SharedPreferences settings = activity.getApplicationContext()
                .getSharedPreferences(Constants.USER_PREF,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public static void showCustomDialog(Activity activity,String message){

        progressDialog = new ProgressDialog(activity, R.style.Custom_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public static void dismissCustomDialog(){

        progressDialog.dismiss();
        progressDialog = null;
    }

    public static boolean isNetworkAvailable(Activity activity){

        ConnectivityManager connectivityManager = (ConnectivityManager) activity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

}
