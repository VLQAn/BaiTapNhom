package com.example.engjp_11;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private final List<WordItem> wordList;
    private final Context context;

    public WordAdapter(Context context, List<WordItem> wordList) {
        this.context = context;
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_word, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, @SuppressLint("RecyclerView") int position) {
        WordItem wordItem = wordList.get(position);
        holder.wordText.setText(wordItem.getWord());
        holder.meaningText.setText(wordItem.getMeaning());

        // Hiển thị biểu tượng đúng trạng thái
        if (wordItem.isSaved()) {
            holder.saveIcon.setImageResource(R.drawable.bookmark_circle_filled);
        } else {
            holder.saveIcon.setImageResource(R.drawable.bookmark_circle);
        }

        holder.saveIcon.setOnClickListener(v -> {
            ObjectAnimator rotationOut = ObjectAnimator.ofFloat(holder.saveIcon, "rotationX", 0f, 90f);
            ObjectAnimator rotationIn = ObjectAnimator.ofFloat(holder.saveIcon, "rotationX", -90f, 0f);

            rotationOut.setDuration(150);
            rotationIn.setDuration(150);

            rotationOut.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    // Chuyển đổi trạng thái lưu
                    wordItem.setSaved(!wordItem.isSaved());

                    // Cập nhật Firebase
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Vocabulary");
                    databaseReference.child(String.valueOf(position + 1)).child("isSaved").setValue(wordItem.isSaved());

                    // Cập nhật biểu tượng
                    if (wordItem.isSaved()) {
                        holder.saveIcon.setImageResource(R.drawable.bookmark_circle_filled);
                    } else {
                        holder.saveIcon.setImageResource(R.drawable.bookmark_circle);
                    }
                    rotationIn.start();
                }
            });

            rotationOut.start();
        });
    }


    @Override
    public int getItemCount() {
        return wordList.size();
    }

    static class WordViewHolder extends RecyclerView.ViewHolder {
        TextView wordText, meaningText;
        ImageView saveIcon;

        WordViewHolder(View itemView) {
            super(itemView);
            wordText = itemView.findViewById(R.id.word_text);
            meaningText = itemView.findViewById(R.id.meaning_text);
            saveIcon = itemView.findViewById(R.id.save_icon);
        }
    }
}
