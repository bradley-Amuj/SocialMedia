package com.project.socialmedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.project.socialmedia.model.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.project.socialmedia.login.admission_number;
import static com.project.socialmedia.login.email;
import static com.project.socialmedia.login.firstname;
import static com.project.socialmedia.login.lastname;
import static com.project.socialmedia.login.faculty;
import static com.project.socialmedia.login.token;

public class Account_Profile extends AppCompatActivity {

    private TextView display_Name, email_add, adm_number,course,faculty_txtv;
    private RecyclerView recyclerView;
    private String url = "http://blooming-mesa-19123.herokuapp.com/myposts";
    private ArrayList<Post> myPostList;
    private MyPostsAdapter adapter;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account__profile);
        display_Name = findViewById(R.id.display_name);
        email_add = findViewById(R.id.email);
        adm_number = findViewById(R.id.admission_number);
        faculty_txtv = findViewById(R.id.faculty);
        course = findViewById(R.id.course);
        myPostList = new ArrayList<>();



        try {
            display_Name.setText(firstname+" "+lastname);
            email_add.setText(email);
            adm_number.setText(admission_number);
            faculty_txtv.setText("Faculty "+faculty);
            course.setText("Course "+login.course);

        }catch (Exception e){


        }

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyPostsAdapter(myPostList);
        recyclerView.setAdapter(adapter);

        populateRecyclerview();






    }

    private void populateRecyclerview(){


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                try {
                    JSONArray array = response.getJSONArray("post");

                    for(int i =0; i<array.length();i++){
                        Post post = new Post();
                        JSONObject object = (JSONObject) array.get(i);
                        post.setPost(object.getString("post"));
                        post.setLikes(object.getInt("likes"));
                        post.setComments(object.getInt("comments"));
                        post.setImageData(object.getString("media"));

                        myPostList.add(post);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization","Bearer " +token);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }
}