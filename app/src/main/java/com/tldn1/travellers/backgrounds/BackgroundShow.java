package com.tldn1.travellers.backgrounds;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.tldn1.travellers.other.MySingleton;
import com.tldn1.travellers.models.Guider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by X on 1/5/2017.
 */

public class BackgroundShow {

    Context context;
//    static String MY_PREFS_NAME = "COSTUMERINFO";
//
//    SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//    String name = prefs.getString("name", "");
//    String howLong = prefs.getString("howLong", "");
    ArrayList<Guider> arrayList = new ArrayList<>();
    String json_url = "http://10.0.3.2/Travellers/showGuide.php/?hlong=3&name=Mico";

    public BackgroundShow(Context context) {
        this.context = context;

    }

    public ArrayList<Guider> getList() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST,
                json_url, (String) null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                while (count < response.length()) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(count);
                        Guider guider = new Guider(jsonObject.getString("name"), jsonObject.getString("image"));
                        Toast.makeText(context, "name "+guider.getName()+" image "+guider.getImage(), Toast.LENGTH_SHORT).show();
                        arrayList.add(guider);
                        count++;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
            }
        });
        MySingleton.getMyInstance(context).addToRequestque(jsonArrayRequest);
        return arrayList;
    }

}
