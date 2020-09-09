package com.project.socialmedia.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.socialmedia.MainActivity;
import com.project.socialmedia.R;

public class sign_up extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "Authentication";
    private String mSpinnerLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Spinner genderSpinner = findViewById(R.id.gender_spinner);
        if (genderSpinner != null) {
            genderSpinner.setOnItemSelectedListener( this);
        }
        //create an array adapter using string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_label, android.R.layout.simple_spinner_item);
        //specify the layout for drop down menu
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //attach the spinner to the adapter
        if (genderSpinner != null) {
            genderSpinner.setAdapter(adapter);

        }

    }

    public void launchRegister(View view) {

        Intent myIntent = new Intent(this, sign_up.class);
        //start a second activity using the method start activity
        Log.d(TAG, "Button Register Clicked");

        startActivity(myIntent);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mSpinnerLabel =adapterView.getItemAtPosition(i).toString();
        Toast myToast= Toast.makeText (this, "Selected Gender as:" +mSpinnerLabel,Toast.LENGTH_SHORT);
        myToast.show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        Toast toast =Toast.makeText(this, "nothing selected",Toast.LENGTH_SHORT);
        toast.show();

    }

    public void launchlogin(View view) {
        //Intent myIntent = new Intent(this, activity_home.class);
//        //start a second activity using the method start activity
//        Log.d(TAG, "Button Register Clicked");
//
//
//        startActivity(myIntent);
    }

    public void launchhome(View view) {
        Intent gotoMainActivity = new Intent(this, MainActivity.class);
        startActivity(gotoMainActivity);
        finish();
    }
}