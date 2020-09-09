package com.project.socialmedia;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private TextView name,faculty,course,description,likes, comments, like_btn,comment_btn,share_btn;
    private ImageView profile_pic;
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            faculty = itemView.findViewById(R.id.faculty);
            course = itemView.findViewById(R.id.course);
            profile_pic = itemView.findViewById(R.id.profile_image);
            description = itemView.findViewById(R.id.Description);
            likes = itemView.findViewById(R.id.likes);
            comments = itemView.findViewById(R.id.comments);

            like_btn = itemView.findViewById(R.id.like);
            comment_btn = itemView.findViewById(R.id.comment);
            share_btn = itemView.findViewById(R.id.share);

            like_btn.setOnClickListener(this);
            comment_btn.setOnClickListener(this);
            share_btn.setOnClickListener(this);
            profile_pic.setOnClickListener(this);






        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.like:
                    Toast.makeText(view.getContext(),"clicked like",Toast.LENGTH_SHORT).show();
                    break; 
                case R.id.comment:
                    Toast.makeText(view.getContext(),"clicked comment",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.share:
                    Toast.makeText(view.getContext(),"clicked share",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.profile_image:
                    Intent intent = new Intent(view.getContext(),profile.class);
                    itemView.getContext().startActivity(intent);

               break;
            }
        }
    }
}
