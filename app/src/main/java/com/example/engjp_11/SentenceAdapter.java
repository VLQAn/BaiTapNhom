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

public class SentenceAdapter extends RecyclerView.Adapter<SentenceAdapter.SentenceViewHolder> {

    private final List<SentenceItem> sentenceList;
    private final Context context;

    public SentenceAdapter(Context context, List<SentenceItem> sentenceList) {
        this.context = context;
        this.sentenceList = sentenceList;
    }

    @NonNull
    @Override
    public SentenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sentence, parent, false);
        return new SentenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SentenceViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SentenceItem sentenceItem = sentenceList.get(position);
        holder.sentenceText.setText(sentenceItem.getSentence());
        holder.meaningText.setText(sentenceItem.getMeaning());

        // Hiển thị biểu tượng đúng trạng thái
        if (sentenceItem.isSaved()) {
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
                    sentenceItem.setSaved(!sentenceItem.isSaved());

                    // Cập nhật Firebase
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Sentences");
                    databaseReference.child(String.valueOf(position + 1)).child("isSaved").setValue(sentenceItem.isSaved());

                    // Cập nhật biểu tượng
                    if (sentenceItem.isSaved()) {
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
        return sentenceList.size();
    }

    static class SentenceViewHolder extends RecyclerView.ViewHolder {
        TextView sentenceText, meaningText;
        ImageView saveIcon;

        SentenceViewHolder(View itemView) {
            super(itemView);
            sentenceText = itemView.findViewById(R.id.sentence_text);
            meaningText = itemView.findViewById(R.id.meaning_text);
            saveIcon = itemView.findViewById(R.id.save_icon);
        }
    }
}
