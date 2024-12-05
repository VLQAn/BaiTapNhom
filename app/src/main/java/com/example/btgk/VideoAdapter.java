package com.example.btgk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private ArrayList<Video> videoList;

    // Constructor
    public VideoAdapter(ArrayList<Video> videoList) {
        this.videoList = videoList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        Video video = videoList.get(position);
        holder.titleTextView.setText(video.getTitle());
        // Bạn có thể thêm hành động khác nếu muốn hiển thị URL video hoặc xử lý sự kiện
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        public VideoViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.videoTitle);
        }
    }
}
