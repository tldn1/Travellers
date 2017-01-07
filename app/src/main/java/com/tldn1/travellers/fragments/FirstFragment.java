package com.tldn1.travellers.fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.tldn1.travellers.R;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    private static int mYear;
    private static int mMonth;
    private static int mDay;
    private static int mHour;
    private static int mMinute;
    static final String DATE_DIALOG_ID = "datePicker";
    static final String TIME_DIALOG_ID = "timePicker";
    static Button btnName,btnArrivalDate,btnArrivalTime,btnLong;
    static SharedPreferences.Editor sharedPref;
    static String MY_PREFS_NAME = "COSTUMERINFO";


    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        sharedPref = this.getActivity().getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE).edit();


        btnName = (Button)view.findViewById(R.id.btnName);
        btnArrivalDate = (Button)view.findViewById(R.id.btnDate);
        btnArrivalTime = (Button)view.findViewById(R.id.btnTime);
        btnLong = (Button)view.findViewById(R.id.btnLong);

        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getName();
            }
        });
        btnArrivalDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), DATE_DIALOG_ID);

            }
        });
        btnArrivalTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), TIME_DIALOG_ID);

            }
        });
        btnLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLong();
            }
        });


        return view;

    }
    public void getName(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Name");
        alertDialog.setMessage("Enter name");
        final EditText edt = new EditText(getContext());
        edt.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

        alertDialog.setView(edt);


        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tmp = edt.getText().toString();
                btnName.setText(tmp);
                sharedPref.putString("name", btnName.getText().toString());
                sharedPref.commit();

            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
            }
        });


        alertDialog.show();
    }
    public void getLong(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("How long u need guide");
        alertDialog.setMessage("Enter number of days");
        final EditText edt = new EditText(getContext());
        edt.setInputType(InputType.TYPE_CLASS_NUMBER);

        alertDialog.setView(edt);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tmp = edt.getText().toString();
                btnLong.setText(tmp);

                sharedPref.putString("howLong", btnLong.getText().toString());
                sharedPref.commit();



            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alertDialog.show();
    }
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // set default date
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // get selected date
            mYear = year;
            mMonth = month;
            mDay = day;

            // show selected date to date button
            btnArrivalDate.setText(new StringBuilder()
                    .append(mYear).append("-")
                    .append(mMonth + 1).append("-")
                    .append(mDay).append(" "));
            sharedPref.putString("arrivalDate", btnArrivalDate.getText().toString());
            sharedPref.commit();
        }

    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // set default time
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of DatePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // get selected time
            mHour = hourOfDay;
            mMinute = minute;

            // show selected time to time button
            btnArrivalTime.setText(new StringBuilder()
                    .append(pad(mHour)).append(":")
                    .append(pad(mMinute)).append(":")
                    .append("00"));
            sharedPref.putString("arrivalTime", btnArrivalTime.getText().toString());
            sharedPref.commit();
        }
    }
    private static String pad(int c) {
        if (c >= 10){
            return String.valueOf(c);
        }else{
            return "0" + String.valueOf(c);
        }
    }


}
