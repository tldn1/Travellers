package com.tldn1.travellers.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;
import com.tldn1.travellers.R;
import com.tldn1.travellers.backgrounds.BackgroundInsert;
import com.tldn1.travellers.backgrounds.BackgroundShow;
import com.tldn1.travellers.models.Guider;
import com.tldn1.travellers.other.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {
    TextView guidePrice, txtGuideName, txtArrivalDateTime;
    ImageView guideImageView;
    SharedPreferences prefs;

    static String MY_PREFS_NAME = "COSTUMERINFO";


    public ThirdFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        txtArrivalDateTime = (TextView) view.findViewById(R.id.arrivalDateTime);
        txtGuideName = (TextView) view.findViewById(R.id.guideName);
        guideImageView = (ImageView) view.findViewById(R.id.guideImageView);
        guidePrice = (TextView) view.findViewById(R.id.guidePrice);

        prefs = this.getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        String name = prefs.getString("name", "");
        String howLong = prefs.getString("howLong", "");
        String dateTime = prefs.getString("arrivalDate", "") + " " + prefs.getString("arrivalTime", "");
        txtArrivalDateTime.setText(dateTime);

        int price = Integer.parseInt(howLong) * 10;
        String priceString = Integer.toString(price);
        guidePrice.setText(priceString);


        String[] niz = name.split(" ");
        String prezime = niz[1];


        String server_url = "http://10.0.3.2/Travellers/showGuide.php/?hlong=" + howLong + "&name=" + prezime;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, server_url, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String img = "http://10.0.3.2/Travellers/";
                    txtGuideName.setText(response.getString("name"));
                    Picasso.with(getContext()).load(img + response.getString("image")).into(guideImageView);
                    Toast.makeText(getContext(), response.getString("image"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        MySingleton.getMyInstance(getContext()).addToRequestque(jsonObjectRequest);

        return view;
    }


}
