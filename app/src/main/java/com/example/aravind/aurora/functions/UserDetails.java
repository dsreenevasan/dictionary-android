package com.example.aravind.aurora.functions;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.aravind.aurora.constants.Constants;

public class UserDetails {


    public static void setUserLoggedIn(Context context,Boolean status){
        SharedPreferences settings =  context.getSharedPreferences(Constants.USER_PREF,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("isLoggedIn",status);
        editor.commit();
    }

    public static Boolean isUserLoggeIn(Context context){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREF,0);
        return settings.getBoolean("isLoggedIn",false);
    }

    public static void setUserName(Context context,String userName){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREF,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("userName",userName);
        editor.commit();
    }

    public static String getUserName(Context context){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREF,0);
        return  settings.getString("userName","undefined");

    }

    public static void setUserPassword(Context context,String password){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREF,0);
        SharedPreferences.Editor editor= settings.edit();
        editor.putString("userPassword",password);
        editor.commit();
    }

    public static String getUserPassword(Context context){

        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREF,0);
        return settings.getString("userPassword","undefined");
    }

    public static void setUserRole(Context context,String role){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREF,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("userRole",role);
        editor.commit();
    }

    public static String getUserRole(Context context){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREF,0);
        return settings.getString("userRole","undefined");
    }

    public static void setUserId(Context context,int userId){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREF,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("userId",userId);
        editor.commit();
    }

    public static int getUserId(Context context){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREF,0);
        return (settings.getInt("userId",0));
    }

}
