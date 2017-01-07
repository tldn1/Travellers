package com.tldn1.travellers.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.tldn1.travellers.R;
import com.tldn1.travellers.adapters.ViewPagerAdapter;
import com.tldn1.travellers.backgrounds.BackgroundInsert;
import com.tldn1.travellers.fragments.FirstFragment;
import com.tldn1.travellers.fragments.SecondFragment;
import com.tldn1.travellers.fragments.ThirdFragment;

public class MainActivity extends AppCompatActivity {
    FrameLayout container;
    Button previous, nextTwo, nextOne, finish;
    int brojac = 0;
    SharedPreferences prefs;
    SharedPreferences prefsGuide;
    static String MY_PREFS_NAME = "COSTUMERINFO";
    static String MY_PREFS_NAME_GUIDE = "GUIDEINFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        previous = (Button) findViewById(R.id.button3);
        nextOne = (Button) findViewById(R.id.button2);
        nextTwo = (Button) findViewById(R.id.button4);
        finish = (Button) findViewById(R.id.button5);
        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        prefsGuide = getSharedPreferences(MY_PREFS_NAME_GUIDE, MODE_PRIVATE);

        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            FirstFragment firstFragment = new FirstFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();

        }
    }

    public void Next(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        SecondFragment secondFragment = new SecondFragment();
        nextOne.setVisibility(View.GONE);
        nextTwo.setVisibility(View.VISIBLE);
        transaction.replace(R.id.fragment_container, secondFragment);
        transaction.addToBackStack(null);
        previous.setVisibility(View.VISIBLE);
        brojac++;
        transaction.commit();
    }

    public void Previous(View view) {

        if (brojac == 2) {
            nextTwo.setVisibility(View.VISIBLE);
        } else if (brojac == 1) {
            nextTwo.setVisibility(View.GONE);
            nextOne.setVisibility(View.VISIBLE);
        }

        finish.setVisibility(View.GONE);

        getSupportFragmentManager().popBackStackImmediate();
        brojac--;
        if (brojac == 0) {
            previous.setVisibility(View.GONE);
        }

    }

    public void NextTwo(View view) {


        String name = prefs.getString("name", "");
        String howLong = prefs.getString("howLong", "");
        String arrivalDate = prefs.getString("arrivalDate", "");
        String arrivalTime = prefs.getString("arrivalTime", "");

        String gender = prefsGuide.getString("gender", "");
        String age = prefsGuide.getString("age", "");
        String language = prefsGuide.getString("language", "").toLowerCase();

        int price = Integer.parseInt(howLong) * 10;
        String priceString = Integer.toString(price);

        BackgroundInsert insert = new BackgroundInsert(this);
        insert.execute(name, arrivalDate + " " + arrivalTime, howLong, priceString, gender, age, language);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ThirdFragment secondFragment = new ThirdFragment();
        finish.setVisibility(View.VISIBLE);
        nextTwo.setVisibility(View.GONE);
        transaction.replace(R.id.fragment_container, secondFragment);
        transaction.addToBackStack(null);
        previous.setVisibility(View.VISIBLE);
        brojac++;
        transaction.commit();
    }

    public void Finish(View view) {
        prefs.edit().clear().apply();
        prefsGuide.edit().clear().apply();
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.actionAbout:
                startActivity(new Intent(this, AboutMe.class));
        }

        return true;
    }
}
