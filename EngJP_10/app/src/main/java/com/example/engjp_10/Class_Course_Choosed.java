package com.example.engjp_10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Class_Course_Choosed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Class_Course_Choosed extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Class_Course_Choosed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_class_course_choosed.
     */
    // TODO: Rename and change types and number of parameters
    public static Class_Course_Choosed newInstance(String param1, String param2) {
        Class_Course_Choosed fragment = new Class_Course_Choosed();
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
        View view = inflater.inflate(R.layout.fragment_class_course_choosed, container, false);

        // Tìm ImageView theo ID
        ImageView btnPre = view.findViewById(R.id.img_vw_class_course_choose_pre);

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

        return view;
    }
}