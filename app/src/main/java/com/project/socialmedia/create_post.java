package com.project.socialmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class create_post extends AppCompatActivity {
    static int requestGallery = 100;
    private EditText postDetails;
    private String post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);


        postDetails.findViewById(R.id.create_post_textview);

        try {
            post = postDetails.getText().toString().trim();


        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView txtgalary = findViewById(R.id.gallery_textview);
        txtgalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                gallery.putExtra(MediaStore.EXTRA_OUTPUT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                if(gallery.resolveActivity(getPackageManager())!=null) {
                    startActivityForResult(gallery,requestGallery);

                }
            }

        });
    }
}
