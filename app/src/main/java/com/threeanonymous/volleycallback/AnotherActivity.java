package com.threeanonymous.volleycallback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.threeanonymous.volleycallback.VolleyCallback.VolleyCallback;
import com.threeanonymous.volleycallback.VolleyCallback.VolleyListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AnotherActivity extends Parent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        String url = "www.facebook.com";

        Map<String, String> params = new HashMap<>();

        volleyCallback.getData(url, Request.Method.GET, params, new VolleyListener() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("object",String.valueOf(jsonObject));
            }
        });

    }
}
