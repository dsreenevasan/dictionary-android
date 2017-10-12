package com.example.aravind.aurora.entities;


import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class InitApiEntity {

    private int code;
    private int message;
    private long currentServerDate;
    List<AnyEntity> contactList;
    private int userId;

    public InitApiEntity(int userId){

        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public long getCurrentServerDate() {
        return currentServerDate;
    }

    public void setCurrentServerDate(long currentServerDate) {
        this.currentServerDate = currentServerDate;
    }

    public List<AnyEntity> getContactList() {
        return contactList;
    }

    public void setContactList(List<AnyEntity> contactList) {
        this.contactList = contactList;
    }

    public interface RestCallInterface{
        void onInitialise(InitApiEntity initApiEntity, VolleyError volleyError);
    }

    public JSONObject getInitAPIParams(){
        JSONObject initAPIJson = null;
        Gson gson = new Gson();
        String initAPIString = gson.toJson(this);
        try{
            initAPIJson = new JSONObject(initAPIString);

        }catch (JSONException e){
            e.printStackTrace();
        }
        return initAPIJson;
    }
}
