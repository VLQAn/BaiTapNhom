package com.example.engjp_11;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Review extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private RecyclerView videosRecyclerView;
    private VideowatchAdapter videoAdapter;
    private List<Videowatched> videoList;

    public Review() {
        // Required empty public constructor
    }

    public static Review newInstance(String param1, String param2) {
        Review fragment = new Review();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);

        setupRecyclerView(view);
        fetchVideosFromFirebase();
        setupClickListeners(view);
        return view;
    }


    private void setupClickListeners(View view) {
        LinearLayout itemReviewVocab = view.findViewById(R.id.item_review_vocab);
        LinearLayout itemReviewSentence = view.findViewById(R.id.item_review_sentence);

        itemReviewVocab.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Review_Vocabulary.class);
            startActivity(intent);
        });

        itemReviewSentence.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Review_Sample_Sentence.class);
            startActivity(intent);
        });
    }

    private void setupRecyclerView(View view) {
        videosRecyclerView = view.findViewById(R.id.videos_recycler_view);
        videosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        videoList = new ArrayList<>();
        videoAdapter = new VideowatchAdapter(getContext(), videoList);
        videosRecyclerView.setAdapter(videoAdapter);
    }

    private void fetchVideosFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Videowatched");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                videoList.clear(); // Làm trống danh sách trước khi thêm dữ liệu mới
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Videowatched video = snapshot.getValue(Videowatched.class);
                    if (video != null) {
                        videoList.add(video); // Thêm video vào danh sách
                    }
                }
                videoAdapter.notifyDataSetChanged(); // Cập nhật RecyclerView
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi nếu xảy ra
            }
        });
    }
}