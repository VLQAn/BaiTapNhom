package com.example.engjp_11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class ClassCourseAdapter extends BaseAdapter {

    private final Context context;
    private final List<ClassCourseItem> courseList;

    public ClassCourseAdapter(Context context, List<ClassCourseItem> courseList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.class_course_item, parent, false);
        }

        // Tìm và ánh xạ các view trong layout class_course_item
        ShapeableImageView imageView = convertView.findViewById(R.id.itemImage);
        TextView titleTextView = convertView.findViewById(R.id.class_course_title);
        TextView descriptionTextView = convertView.findViewById(R.id.class_course_description);

        // Lấy dữ liệu cho item hiện tại
        ClassCourseItem item = courseList.get(position);

        // Đặt dữ liệu cho các view
        imageView.setImageResource(item.getImageResId());
        titleTextView.setText(item.getTitle());
        descriptionTextView.setText(item.getDescription());

        return convertView;
    }
}

