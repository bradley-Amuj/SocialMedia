package com.project.socialmedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.socialmedia.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.project.socialmedia.login.course;
import static com.project.socialmedia.login.faculty;
import static com.project.socialmedia.login.firstname;
import static com.project.socialmedia.login.lastname;

public class MyPostsAdapter extends RecyclerView.Adapter<MyPostsAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Post> myposts;

    public MyPostsAdapter(ArrayList<Post> myposts) {
        this.myposts = myposts;
    }

    @NonNull
    @Override
    public MyPostsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false);
        context = view.getContext();
        return new MyPostsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPostsAdapter.MyViewHolder holder, int position) {

        try {
            Post post = myposts.get(position);

            Picasso.with(context).
                    load(post.getImageData())
                    .into(holder.Media);

            holder.description.setText(post.getPost());
            holder.likes.setText(String.valueOf(post.getLikes()) + " likes");
            holder.comments.setText(String.valueOf(post.getComments()) + " comments");
            holder.name.setText(firstname + " " + lastname);
            holder.faculty.setText(faculty);
            holder.course.setText(course);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return myposts.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name,faculty,course,description,likes, comments, like_btn,comment_btn,share_btn;
        private ImageView profile_pic,Media;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            faculty = itemView.findViewById(R.id.faculty);
            course = itemView.findViewById(R.id.course);
            profile_pic = itemView.findViewById(R.id.profile_image);
            description = itemView.findViewById(R.id.Description);
            likes = itemView.findViewById(R.id.likes);
            comments = itemView.findViewById(R.id.comments);
            Media = itemView.findViewById(R.id.media);

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

