package com.threeanonymous.volleycallback.VolleyCallback;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.threeanonymous.volleycallback.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by handyhandy on 10/22/17.
 */

public class VolleyCallback {
    private VolleyListener volleyListener;
    private String url;
    private Integer method;
    private Map<String, String> headers;
    private Map<String, String> parameters;
    private final static String TAG = "TAG_VOLLEY";

    public VolleyCallback() {
        this.volleyListener = null;

    }

    public void getData(String url, Integer method, Map<String, String> headers , Map<String, String> parameters , VolleyListener volleyListener) {
        this.volleyListener = volleyListener;
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.parameters = parameters;
        getDataFromServer();
    }

    public void getData(String url, Integer method, Map<String, String> parameters , VolleyListener volleyListener) {
        this.volleyListener = volleyListener;
        this.url = url;
        this.method = method;
        this.headers = Collections.emptyMap();
        this.parameters = parameters;
        getDataFromServer();
    }

    public void getData(String url, Integer method , VolleyListener volleyListener) {
        this.volleyListener = volleyListener;
        this.url = url;
        this.method = method;
        this.headers = Collections.emptyMap();
        this.parameters = Collections.emptyMap();
        getDataFromServer();
    }

    private void getDataFromServer(){
        String TAG_JSON = "json_obj_req";
        StringRequest jsonObjReq = new StringRequest(method,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("urlResponse", response);
                    JSONObject jsonObject = new JSONObject(response);
                    volleyListener.onResponseJson(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                return parameters;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, TAG_JSON);

    }

}
