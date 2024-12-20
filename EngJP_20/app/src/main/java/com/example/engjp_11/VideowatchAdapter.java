package com.example.engjp_11;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
public class VideowatchAdapter extends RecyclerView.Adapter<VideowatchAdapter.VideoViewHolder> {
    private final List<Videowatched> videoList;
    private final Context context;

    public VideowatchAdapter(Context context, List<Videowatched> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_videowatched, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Videowatched video = videoList.get(position);
        holder.title.setText(video.getTitle());
        holder.description.setText(video.getDescription());
        Glide.with(context).load(video.getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView image;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.video_title);
            description = itemView.findViewById(R.id.video_description);
            image = itemView.findViewById(R.id.video_image);
        }
    }
}
