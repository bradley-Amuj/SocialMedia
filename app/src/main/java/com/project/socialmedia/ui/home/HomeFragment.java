package com.project.socialmedia.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.project.socialmedia.MyAdapter;
import com.project.socialmedia.R;
import com.project.socialmedia.create_post;
import com.project.socialmedia.model.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private String url = "http://blooming-mesa-19123.herokuapp.com/posts";

    private MyAdapter adapter;

    private List<Post> postlist;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        postlist = new ArrayList<>();
        getData(root);
        adapter = new MyAdapter(postlist);
        recyclerView.setAdapter(adapter);

        View createPost_layout = root.findViewById(R.id.create_post_layout);
        createPost_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), create_post.class);
                startActivity(intent);
            }
        });


        return root;
    }

    private void getData(View root) {
        final ProgressDialog progressDialog = new ProgressDialog(root.getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray array =  response.getJSONArray("posts");




                    for(int i =0;i<array.length();i++){
                        Post post = new Post();
                        JSONObject object = (JSONObject) array.get(i);
                        post.setPost(object.getString("post"));
                        post.setLikes(object.getInt("likes"));
                        post.setComments(object.getInt("comments"));
                        post.setImageData(object.getString("media"));







                        postlist.add(post);




                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                adapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }

        });
        RequestQueue requestQueue = Volley.newRequestQueue(root.getContext());
        requestQueue.add(jsonObjectRequest);







    }

}
