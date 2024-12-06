package com.example.engjp_11;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Video#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Video extends Fragment {
    private ListView listView;
    private VideoAdapter adapter;
    private List<VideoItem> videoList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Video() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Video.
     */
    // TODO: Rename and change types and number of parameters
    public static Video newInstance(String param1, String param2) {
        Video fragment = new Video();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);

        listView = rootView.findViewById(R.id.video_list);
        videoList = new ArrayList<>();
        adapter = new VideoAdapter(getContext(), videoList);
        listView.setAdapter( adapter);

        // Firebase reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Videos");

        // Fetch data from Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                videoList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    VideoItem course = snapshot.getValue(VideoItem.class);
                    if (course != null) {
                        Log.d("FirebaseData", "imageResId: " + course.getVideoImg());
                        videoList.add(course);
                    }
                }
                adapter.notifyDataSetChanged(); ;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("FirebaseError", databaseError.getMessage());
            }
        });

        // Thêm sự kiện click cho ListView
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Lấy item được click
            VideoItem selectedItem = videoList.get(position);

            // Chuyển sang Activity mới
            Intent intent = new Intent(getActivity(), Activity_VideoDetail.class);

            // Truyền dữ liệu của item được click sang Activity mới
//            intent.putExtra("video_title", selectedItem.getVideoImg());
//            intent.putExtra("video_url", selectedItem.getVideoUrl());
            startActivity(intent);
        });

        return rootView;
    }
}