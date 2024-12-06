package com.example.engjp_11;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Introductory_Vocabulary extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Introductory_Vocabulary() {
        // Required empty public constructor
    }

    public static Introductory_Vocabulary newInstance(String param1, String param2) {
        Introductory_Vocabulary fragment = new Introductory_Vocabulary();
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
        View view = inflater.inflate(R.layout.fragment_introductory_vocabulary, container, false);

        ImageView btnPre = view.findViewById(R.id.img_vw_intro_vocab_pre);

        // Thêm sự kiện OnClickListener cho ImageView
        btnPre.setOnClickListener(v -> {
            // Sử dụng FragmentManager để pop fragment hiện tại ra khỏi backstack
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack(); // Quay lại fragment trước đó
            } else {
                getActivity().getOnBackPressedDispatcher(); // Đóng fragment hoặc thoát nếu không còn fragment nào khác
            }
        });

        // Tìm RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Kết nối Firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Vocabulary");

        List<WordItem> wordList = new ArrayList<>();
        WordAdapter adapter = new WordAdapter(getContext(), wordList);
        recyclerView.setAdapter(adapter);

        // Lấy dữ liệu từ Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                wordList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    WordItem wordItem = snapshot.getValue(WordItem.class);
                    wordList.add(wordItem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi
            }
        });

        return view;
    }

}
