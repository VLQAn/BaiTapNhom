package com.example.engjp_11;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder> {
    private List<String> buttonList;  // Danh sách chứa text cho các nút
    private Context context;
    private OnItemClickListener onItemClickListener;
    private int selectedButtonPosition = -1; // Vị trí của Button được chọn

    // Interface để lắng nghe sự kiện nhấn vào từng nút
    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    // Constructor
    public ButtonAdapter(Context context, List<String> buttonList, OnItemClickListener listener) {
        this.context = context;
        this.buttonList = buttonList;
        this.onItemClickListener = listener;
    }

    // ViewHolder class để đại diện cho mỗi button item
    public static class ButtonViewHolder extends RecyclerView.ViewHolder {
        public Button button;

        public ButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.itemButton);  // Gắn button từ layout
        }
    }

    @NonNull
    @Override
    public ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ButtonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String buttonText = buttonList.get(position);
        holder.button.setText(buttonText);

        // Đổi màu Button nếu nó được chọn
        if (position == selectedButtonPosition) {
            holder.button.setBackgroundColor(Color.parseColor("#1813a2"));
            holder.button.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.button.setBackgroundColor(Color.parseColor("#FFFFFF")); // Màu mặc định
            holder.button.setTextColor(Color.parseColor("#000000"));
        }

        // Thiết lập sự kiện onClick cho từng nút
        holder.button.setOnClickListener(v -> {
            onItemClickListener.onItemClick(buttonText);

            // Cập nhật vị trí của Button được chọn và làm mới giao diện
            selectedButtonPosition = position;
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return buttonList.size();
    }
}


