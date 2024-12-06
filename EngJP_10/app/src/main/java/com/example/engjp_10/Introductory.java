package com.example.engjp_10;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Introductory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Introductory extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Introductory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment introductory.
     */
    // TODO: Rename and change types and number of parameters
    public static Introductory newInstance(String param1, String param2) {
        Introductory fragment = new Introductory();
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
        View view = inflater.inflate(R.layout.fragment_introductory, container, false);

        // Tìm nút theo ID và thêm OnClickListener
        Button btnVocab = view.findViewById(R.id.btn_vocab);
        Button btnVideo = view.findViewById(R.id.btn_video);
        Button btnListening = view.findViewById(R.id.btn_listening);
        Button btnSampSentence = view.findViewById(R.id.btn_sample_sententces);
        Button btnReview = view.findViewById(R.id.btn_review);

        btnVocab.setOnClickListener(v -> {
            // Sử dụng FragmentManager và FragmentTransaction để chuyển fragment
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Tạo một instance của fragment bạn muốn chuyển tới
            Fragment fragmenIntroVocab = new Introductory_Vocabulary();

            // Thay thế fragment hiện tại bằng fragment mới
            fragmentTransaction.replace(R.id.fragment_container, fragmenIntroVocab);
            fragmentTransaction.addToBackStack(null); // Cho phép quay lại fragment trước đó bằng nút back
            fragmentTransaction.commit();
        });

        btnVideo.setOnClickListener(v -> {
            // Sử dụng Intent để chuyển Activity
            Intent intent = new Intent(getActivity(), Activity_Video_Detail.class);
            startActivity(intent);
        });

        btnListening.setOnClickListener(view1 -> {
            // Sử dụng FragmentManager và FragmentTransaction để chuyển fragment
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Tạo một instance của fragment bạn muốn chuyển tới
            Fragment fragmenIntroVocab = new Introductory_Listening1();

            // Thay thế fragment hiện tại bằng fragment mới
            fragmentTransaction.replace(R.id.fragment_container, fragmenIntroVocab);
            fragmentTransaction.addToBackStack(null); // Cho phép quay lại fragment trước đó bằng nút back
            fragmentTransaction.commit();
        });

        btnSampSentence.setOnClickListener(view1 -> {
            // Sử dụng FragmentManager và FragmentTransaction để chuyển fragment
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Tạo một instance của fragment bạn muốn chuyển tới
            Fragment fragmenIntroVocab = new Introduction_SampleSentence();

            // Thay thế fragment hiện tại bằng fragment mới
            fragmentTransaction.replace(R.id.fragment_container, fragmenIntroVocab);
            fragmentTransaction.addToBackStack(null); // Cho phép quay lại fragment trước đó bằng nút back
            fragmentTransaction.commit();
        });

        btnReview.setOnClickListener(view1 -> {
            // Sử dụng Intent để chuyển Activity
            Intent intent = new Intent(getActivity(), Activity_Review_Vocabulary.class);
            startActivity(intent);
        });

        return view;
    }
}