package com.example.engjp_10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder> {
    private List<String> buttonList;  // Danh sách chứa text cho các nút
    private Context context;

    // Constructor
    public ButtonAdapter(Context context, List<String> buttonList) {
        this.context = context;
        this.buttonList = buttonList;
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
    public void onBindViewHolder(@NonNull ButtonViewHolder holder, int position) {
        String buttonText = buttonList.get(position);
        holder.button.setText(buttonText);

        // Thiết lập sự kiện onClick cho từng nút
        holder.button.setOnClickListener(v ->
                Toast.makeText(context, buttonText + " clicked", Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return buttonList.size();
    }
}


