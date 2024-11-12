package com.example.engjp_11;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_introductory_vocabulary, container, false);

        // Tìm ImageView theo ID
        ImageView btnPre = view.findViewById(R.id.img_vw_intro_vocab_pre);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Dữ liệu mẫu
        List<WordItem> wordList = new ArrayList<>();
        wordList.add(new WordItem("Joyful", "Vui sướng, hoan hỉ."));
        wordList.add(new WordItem("Cheerful", "Vui sướng, hoan hỉ."));
        wordList.add(new WordItem("Blissful", "Hạnh phúc tột độ, cực kỳ hạnh phúc."));
        wordList.add(new WordItem("Elated", "Phấn khích, hân hoan, rất vui mừng."));
        wordList.add(new WordItem("Ecstatic", "Cực kỳ vui sướng, ngây ngất."));


        WordAdapter adapter = new WordAdapter(getContext(), wordList);
        recyclerView.setAdapter(adapter);

        // Thêm sự kiện OnClickListener cho ImageView
        btnPre.setOnClickListener(v -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack(); // Quay lại fragment trước đó
            } else {
                getActivity().onBackPressed(); // Đóng fragment hoặc thoát nếu không còn fragment nào khác
            }
        });

        return view;
    }
}
