package com.example.engjp_11;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class VideoItem  {
    private String title;
    private String heartCount;
    private String sentence;
    private String videoImg; // URL hình ảnh từ Firebase

    public VideoItem() {
        // Constructor trống cần thiết cho Firebase
    }

    public VideoItem(String title, String heartCount, String sentence, String videoImg) {
        this.title = title;
        this.heartCount = heartCount;
        this.sentence = sentence;
        this.videoImg = videoImg;
    }

    // Getter và Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeartCount() {
        return heartCount;
    }

    public void setHeartCount(String heartCount) {
        this.heartCount = heartCount;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getVideoImg() {
        return videoImg;
    }

    public void setVideoImg(String videoImg) {
        this.videoImg = videoImg;
    }


}