package com.example.engjp_11;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Class_Course#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Class_Course_Item extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Class_Course_Item() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Class_Course.
     */
    // TODO: Rename and change types and number of parameters
    public static Class_Course newInstance(String param1, String param2) {
        Class_Course fragment = new Class_Course();
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
        View view = inflater.inflate(R.layout.class_course_item, container, false);

        LinearLayout itemClassCourse = view.findViewById(R.id.item_class_course);
        itemClassCourse.setOnClickListener(v -> {
            // Sử dụng FragmentManager và FragmentTransaction để chuyển fragment
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Tạo một instance của fragment bạn muốn chuyển tới
            Fragment fragmenIntroVocab = new Class_Course_Choosed();

            // Thay thế fragment hiện tại bằng fragment mới
            fragmentTransaction.replace(R.id.class_view, fragmenIntroVocab);
            fragmentTransaction.addToBackStack(null); // Cho phép quay lại fragment trước đó bằng nút back
            fragmentTransaction.commit();
        });

        return view;
    }
}