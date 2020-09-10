package com.project.socialmedia.model;

import java.util.ArrayList;

public class Post {
    private String post;
    private  int likes,comments;
    private String  imageUrl;

    public Post(String post, int likes, int comments,String imageData) {
        this.post = post;
        this.likes = likes;
        this.comments = comments;
        this.imageUrl = imageData;

    }

    public String getImageData() {
        return imageUrl;
    }

    public void setImageData(String imageData) {
        this.imageUrl = imageData;
    }

    public Post() {
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
