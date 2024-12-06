package com.example.engjp_11;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class VideoAdapter extends BaseAdapter {

    private final Context context;
    private final List<VideoItem> courseList;

    public VideoAdapter(Context context, List<VideoItem> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.video_img);
        TextView titleView = convertView.findViewById(R.id.video_title);
        TextView descriptionView = convertView.findViewById(R.id.video_sentence);
        TextView heartCount = convertView.findViewById(R.id.heart_count);

        VideoItem item = courseList.get(position);

        String imageUrl = item.getVideoImg();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_image)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Log.e("GlideError", "Load failed", e);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            Log.d("GlideSuccess", "Image loaded successfully");
                            return false;
                        }

                    })
                    .into(imageView);

        } else {
            // Xử lý khi không có URL hợp lệ
            Log.e("Glide", "URL is null or empty");
            imageView.setImageResource(R.drawable.error_image); // Hiển thị ảnh lỗi nếu URL không hợp lệ
        }

        titleView.setText(item.getTitle());
        descriptionView.setText(item.getSentence());
        heartCount.setText(item.getHeartCount());

        return convertView;
    }
}

