package com.project.socialmedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class login extends AppCompatActivity {
private String API_URL= "http://blooming-mesa-19123.herokuapp.com/login";
private EditText username,password;
private String username_txt,password_txt;


private String status;

    public static String token;
    private int userid;
    public static String firstname;
    public static String lastname;
    public static int admission_number;
    public static String email;
    public static String course;
    public static String faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);






    }


    private boolean getDetails(){
        username_txt = username.getText().toString().trim();
        password_txt = password.getText().toString().trim();


        if(TextUtils.isEmpty(username_txt)){

            username.setError("Username is empty");
            return false;
        }
        if(TextUtils.isEmpty(password_txt)){

            password.setError("Password is empty");
            return false;
        }
        return true;
    }

    public void launchLogin(View view) {
        if(getDetails()){

            Log.d("Data", "launchLogin: "+  username_txt);
            Log.d("Data", "launchLogin: "+  password_txt);

            getData();

        }


    }

    public void launchReg(View view) {
        startActivity(new Intent(this,sign_up.class));
        finish();
    }


    private void getData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in ...");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("TAG", "onResponse:Object "+ jsonObject);

                   status = jsonObject.getString("status");
                   token = jsonObject.getString("token");

                   JSONObject user  = new JSONObject(jsonObject.getString("user"));

                   userid = user.getInt("id");
                   firstname = user.getString("first_name");
                   lastname= user.getString("last_name");
                   admission_number = user.getInt("admission_number");
                   email= user.getString("email");
                   course= user.getString("course");
                   faculty= user.getString("faculty");


                    if(status.equals("ok")){
                        progressDialog.dismiss();
                        Toast.makeText(login.this, "Please wait", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }






                    Log.d("TAG", "onResponse: User"+ jsonObject.getString("user").getClass());



                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Volley", "onErrorResponse: "+ error.toString());
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "incorrect password", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("email",username_txt);
                params.put("password",password_txt);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }



        }
