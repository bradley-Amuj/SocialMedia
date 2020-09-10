package com.project.socialmedia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class User_posts_Adapter extends RecyclerView.Adapter<User_posts_Adapter.ViewHolder> {
    private TextView name,faculty,course,description,likes, comments, like_btn,comment_btn,share_btn;
    private ImageView profile_pic;

    @NonNull
    @Override
    public User_posts_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false);
        return new User_posts_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull User_posts_Adapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolder(@NonNull View itemView) {
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

        }
    }
}
