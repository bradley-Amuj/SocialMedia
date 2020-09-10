package com.project.socialmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class sign_up extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "Authentication";
    private String mSpinnerLabel;
    private EditText firstname, lastname,admission_number,email,password,confirm_password;
    private boolean gender;
    private String Firstname,Lastname,Admission_number,Email,Password,Confirm_password;
    private String url = "http://blooming-mesa-19123.herokuapp.com/register";
    private RequestQueue requestQueue;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        admission_number = findViewById(R.id.adm);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);


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

    private boolean getDetails(){
        Firstname = firstname.getText().toString().trim();
        Lastname = lastname.getText().toString().trim();
        Admission_number = admission_number.getText().toString().trim();
        Email = email.getText().toString().trim();
        Password = password.getText().toString().trim();
        Confirm_password = confirm_password.getText().toString().trim();

        if(TextUtils.isEmpty(Firstname)){
            firstname.setError("Please Enter First name");
            return false;
        }
        if(TextUtils.isEmpty(Lastname)){
            lastname.setError("Please Enter Last name");
            return false;
        }
        if(TextUtils.isEmpty(Admission_number)){
            admission_number.setError("Please Enter admission number");
            return false;
        }
        if(TextUtils.isEmpty(Email)){
            email.setError("Please Enter  email");
            return false;
        }
        if(TextUtils.isEmpty(Password)){
            password.setError("Please Enter  password");
            return false;
        }


        if(!TextUtils.equals(Confirm_password,Password)){
            confirm_password.setError("Passwords do not match");
            return false;
        }


        return true;




    }

    public void launchRegister(View view) {

        if(getDetails()){
            requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"User created successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),login.class));
                    finish();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();


                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("first_name",Firstname);
                    params.put("last_name",Lastname);
                    params.put("admission_number",Admission_number);
                    params.put("email",Email);
                    params.put("password",Password);
                    params.put("gender", String.valueOf(gender));




                    return params;
                }
            };


            requestQueue.add(stringRequest);
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Creating User");
            progressDialog.show();


        }



    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mSpinnerLabel =adapterView.getItemAtPosition(i).toString();

        if(mSpinnerLabel.equals("Female")){
            gender = false;
        }else{
            gender = true;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }

    public void launchlogin(View view) {
      startActivity(new Intent(this,login.class));
      finish();
    }


}