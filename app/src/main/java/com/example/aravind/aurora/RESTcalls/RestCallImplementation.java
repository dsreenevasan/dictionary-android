package com.example.aravind.aurora.RESTcalls;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.aravind.aurora.constants.Constants;
import com.example.aravind.aurora.entities.InitApiEntity;
import com.example.aravind.aurora.entities.LoginEntity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestCallImplementation {

    static RequestQueue queue;
    private static final String BASE_URL = Constants.BASE_URL;

    private static String getAbsoluteURL(String relativeURL){

        return (BASE_URL + relativeURL);
    }

    public static void onLogin(final LoginEntity loginEntity, final LoginEntity.RestCallInterface restCallInterface, final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        /*String url = getAbsoluteURL("/user/login/aurora");*/
        String url = "https://od-api.oxforddictionaries.com:443/api/v1/entries/en/ball";
        JSONObject postParams = loginEntity.getJSONObjectAsParams();
        Log.d("","" + postParams);
        JSONBaseRequest postRequest = new JSONBaseRequest(Request.Method.POST, url, postParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("",""+ response);
                            String desc = response.getString("tag_results");
                            /*Gson gson = new Gson();
                            LoginEntity newSuccessLoginEntity = gson.fromJson(response.toString(),LoginEntity.class);
                            loginEntity.setAuroraUser(newSuccessLoginEntity.getAuroraUser());
                            restCallInterface.onLogin(loginEntity,null);*/

                        } catch (Exception e) {
                            restCallInterface.onLogin(loginEntity,new VolleyError());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if(error.networkResponse != null && error.networkResponse.data != null){
                    Gson gson = new Gson();
                    LoginEntity newErrorLoginEntity = gson.fromJson(new String(error.networkResponse.data),LoginEntity.class);
                    if(newErrorLoginEntity.getMessage() != null){
                        loginEntity.setMessage(newErrorLoginEntity.getMessage());
                        loginEntity.setCode(newErrorLoginEntity.getCode());
                    }
                }
                restCallInterface.onLogin(loginEntity,new VolleyError());
            }
        },30000,0){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("app_id", "4f1f963f");
                params.put("app_key", "8462672de716c8a9bd02bf906322dca8");

                return params;
            }
        };
        queue.add(postRequest);
    }

    public static void initAPIEntity(InitApiEntity initApiEntity,final InitApiEntity.RestCallInterface restCallInterface,final Context context){

        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = getAbsoluteURL("/initializeConfig");
        //JSONObject postParams = initApiEntity.getInitAPIParams();
        JSONBaseRequest postRequest = new JSONBaseRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new Gson();
                        InitApiEntity newInitSuccessEntity = gson.fromJson(response.toString(),InitApiEntity.class);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse != null && error.networkResponse.data != null){
                    Gson gson = new Gson();
                }
            }
        },3000,0){

        };

    }

    public static void requestWithSomeHttpHeaders_JSON(final Context context, String str){
        Log.d("TAg = "," OK");

        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = "https://od-api.oxforddictionaries.com:443/api/v1/entries/en/" + str;
        //JSONObject postParams = initApiEntity.getInitAPIParams();
        JSONBaseRequest postRequest = new JSONBaseRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAg = "," OK1");
                        try {
                            Log.d("TAg = "," OK12");
                            //Log.d("TAg = ", response.getString("results"));
                            JSONArray results = response.getJSONArray("results");
                            JSONArray lexicalEntries = results.getJSONObject(0).getJSONArray("lexicalEntries");
                            JSONArray entries = lexicalEntries.getJSONObject(0).getJSONArray("entries");
                            JSONArray senses = entries.getJSONObject(0).getJSONArray("senses");
                            JSONArray definitions = senses.getJSONObject(0).getJSONArray("definitions");
                            String definition = definitions.toString();
                            Log.d("MEANING = ", definition);
                            Toast.makeText(context, definition, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Gson gson = new Gson();
                        InitApiEntity newInitSuccessEntity = gson.fromJson(response.toString(),InitApiEntity.class);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse != null && error.networkResponse.data != null){
                    Gson gson = new Gson();
                }
            }
        },3000,0){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("app_id", "4f1f963f");
                params.put("app_key", "8462672de716c8a9bd02bf906322dca8");

                return params;
            }

        };

        queue.add(postRequest);
    }

    public static void requestWithSomeHttpHeaders(final Context context) {
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = "https://od-api.oxforddictionaries.com:443/api/v1/entries/en/ball";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d("ERROR","error => "+error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("app_id", "4f1f963f");
                params.put("app_key", "8462672de716c8a9bd02bf906322dca8");

                return params;
            }
        };
        queue.add(postRequest);

    }

}
