package com.example.engjp_10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CustomAdapterVideo extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;

    public CustomAdapterVideo(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        // Trả về số item trong ListView
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Xác định layout cho từng item
        if (position == 0) {
            convertView = inflater.inflate(R.layout.layout_video, parent, false);
        } else if (position == 1) {
            convertView = inflater.inflate(R.layout.layout_sublanguage_all, parent, false);
        } else if (position == 2) {
            convertView = inflater.inflate(R.layout.layout_action_bar, parent, false);
        }

        return convertView;
    }
}
