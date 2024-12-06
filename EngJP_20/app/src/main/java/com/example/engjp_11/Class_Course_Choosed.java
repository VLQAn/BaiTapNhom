package com.example.engjp_11;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Class_Course_Choosed extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Class_Course_Choosed() {
        // Required empty public constructor
    }

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
        View view = inflater.inflate(R.layout.fragment_class_course_choosed, container, false);

        ImageView btnPre = view.findViewById(R.id.img_vw_class_course_choose_pre);
        btnPre.setOnClickListener(v -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack();
            } else {
                getActivity().onBackPressed(); // Dùng onBackPressed đơn giản hơn
            }
        });

        TextView lession1 = view.findViewById(R.id.lession1);
        LinearLayout subLayout1 = view.findViewById(R.id.subLayout1);
        LinearLayout subLayout2 = view.findViewById(R.id.subLayout2);
        LinearLayout subLayout = view.findViewById(R.id.subLayout);

        TextView lession2 = view.findViewById(R.id.lession2);
        LinearLayout mainLayout1 = view.findViewById(R.id.mainLayout1);
        LinearLayout mainLayout2 = view.findViewById(R.id.mainLayout2);
        LinearLayout mainLayout = view.findViewById(R.id.mainLayout);


        lession1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle visibility of the button container
                if (subLayout.getVisibility() == View.VISIBLE) {
                    subLayout.setVisibility(View.GONE); // Hide if it's currently visible
                } else {
                    subLayout.setVisibility(View.VISIBLE); // Show if it's currently hidden
                }
            }
        });

        subLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle when Step 1 is clicked
            }
        });

        subLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle when Step 2 is clicked
            }
        });

        lession2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle visibility of the button container
                if (mainLayout.getVisibility() == View.VISIBLE) {
                    mainLayout.setVisibility(View.GONE); // Hide if it's currently visible
                } else {
                    mainLayout.setVisibility(View.VISIBLE); // Show if it's currently hidden
                }
            }
        });

        mainLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle when Step 1 is clicked
            }
        });

        mainLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle when Step 2 is clicked
            }
        });
        return view;
    }
}
