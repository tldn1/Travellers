package com.tldn1.travellers.other;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by X on 1/5/2017.
 */

public class MySingleton {

    private static MySingleton myInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private MySingleton(Context context) {
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingleton getMyInstance(Context context) {
        if (myInstance == null) {
            myInstance = new MySingleton(context);
        }
        return myInstance;
    }

    public void addToRequestque(Request request) {
        requestQueue.add(request);
    }
}
