package com.example.aravind.aurora.RESTcalls;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class JSONBaseRequest extends JsonObjectRequest{

    public JSONBaseRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener,
                           Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(
                1000,1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
    }

    public JSONBaseRequest(int post, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener,
                           Response.ErrorListener errorListener, int timeout, int retries) {

        super(url, jsonRequest, listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(
                timeout,retries,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response){
        return super.parseNetworkResponse(response);
    }
}
