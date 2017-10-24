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

public class MainActivity extends Parent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://httpbin.org/post";
        Map<String, String> params = new HashMap<>();
        params.put("name","Checker");
        params.put("id","1234567890");

        volleyCallback.getData(url, Request.Method.POST, params, new VolleyListener() {
            @Override
            public void onResponseJson(JSONObject jsonObject) {
                Log.e("object",String.valueOf(jsonObject));
            }
        });


    }
}
