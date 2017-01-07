package com.tldn1.travellers.fragments;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tldn1.travellers.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    ArrayList<Integer> mSelectedItems;
    ArrayList<Integer> mSelectedItemsAge;

    Button btnGender, btnAge, btnLanguage;
    SharedPreferences.Editor sharedPref;
    static String MY_PREFS_NAME = "GUIDEINFO";

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        sharedPref = this.getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        btnGender = (Button) view.findViewById(R.id.btnGender);
        btnAge = (Button) view.findViewById(R.id.btnAge);
        btnLanguage = (Button) view.findViewById(R.id.btnLanguage);

        btnGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getGender();
            }
        });

        btnAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAge();
            }
        });
        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLanguage();
            }
        });

        return view;
    }

    public void getGender() {
        mSelectedItems = new ArrayList<Integer>();


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Select Gender");
        alertDialog.setMultiChoiceItems(R.array.choices, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {

                if (isChecked) {
                    // if the user checked the item, add it to the selected items
                    mSelectedItems.add(which);
                } else if (mSelectedItems.contains(which)) {
                    // else if the item is already in the array, remove it
                    mSelectedItems.remove(Integer.valueOf(which));
                }


            }
        }).setPositiveButton("Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                String selectedIndex = "";
                for (Integer i : mSelectedItems) {
                    selectedIndex += i + " ";
                }
                if (selectedIndex.equals("0 ")) {
                    btnGender.setText("male");
                } else if (selectedIndex.equals("1 ")) {
                    btnGender.setText("female");
                } else if (selectedIndex.contains("1") && selectedIndex.contains("0")) {
                    btnGender.setText("male or female");
                }
                sharedPref.putString("gender", btnGender.getText().toString());
                sharedPref.commit();

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int ii) {

            }
        }).setCancelable(false).show();
    }

    public void getAge() {
        mSelectedItemsAge = new ArrayList<Integer>();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Select Age");
        alertDialog.setMultiChoiceItems(R.array.choicesAge, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {

                if (isChecked) {
                    // if the user checked the item, add it to the selected items
                    mSelectedItemsAge.add(which);
                } else if (mSelectedItemsAge.contains(which)) {
                    // else if the item is already in the array, remove it
                    mSelectedItemsAge.remove(Integer.valueOf(which));
                }


            }
        }).setPositiveButton("Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                String selectedIndex = "";
                for (Integer i : mSelectedItemsAge) {
                    selectedIndex += i + " ";
                }

                if (selectedIndex.equals("0 ")) {
                    btnAge.setText("< 30");
                } else if (selectedIndex.equals("1 ")) {
                    btnAge.setText("> 30");
                } else if (selectedIndex.contains("1") && selectedIndex.contains("0")) {
                    btnAge.setText("<> 30");
                }
                sharedPref.putString("age", btnAge.getText().toString());
                sharedPref.commit();

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int ii) {

            }
        }).setCancelable(false).show();
    }

    public void getLanguage() {


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Choose One")

                .setSingleChoiceItems(R.array.choicesLanguage, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }

                })

                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();

                        switch (selectedPosition) {
                            case 0:
                                btnLanguage.setText("English");
                                break;
                            case 1:
                                btnLanguage.setText("France");
                                break;
                            case 2:
                                btnLanguage.setText("Mandarian");
                                break;
                            case 3:
                                btnLanguage.setText("Serbian");
                                break;
                            case 4:
                                btnLanguage.setText("Portugise");
                                break;
                            case 5:
                                btnLanguage.setText("Spain");
                                break;
                        }
                        sharedPref.putString("language", btnLanguage.getText().toString());
                        sharedPref.commit();
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                    }
                })

                .show();


    }

}
