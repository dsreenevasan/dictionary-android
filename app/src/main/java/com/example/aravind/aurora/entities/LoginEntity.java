package com.example.aravind.aurora.entities;


import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginEntity {

    private String userName;
    private String password;
    private int code;
    private String message;
    private AuroraUser auroraUser;

    public AuroraUser getAuroraUser() {
        return auroraUser;
    }

    public void setAuroraUser(AuroraUser auroraUser) {
        this.auroraUser = auroraUser;
    }

    public LoginEntity(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public interface RestCallInterface{
        void onLogin(LoginEntity loginEntity, VolleyError volleyError);
    }

    public JSONObject getJSONObjectAsParams(){

        JSONObject loginEntityJson = null;
        Gson gs = new Gson();
        String loginString = gs.toJson(this);
        try {
            loginEntityJson = new JSONObject(loginString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loginEntityJson.remove("code");
        return loginEntityJson;
    }
}
