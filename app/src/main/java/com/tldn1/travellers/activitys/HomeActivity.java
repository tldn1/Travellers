package com.tldn1.travellers.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tldn1.travellers.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void Hire(View view){
        Intent i = new Intent(HomeActivity.this,MainActivity.class);
        startActivity(i);
    }
}
