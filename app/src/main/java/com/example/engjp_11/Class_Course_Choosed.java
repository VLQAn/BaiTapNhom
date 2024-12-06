package com.example.engjp_11;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Class_Course_Choosed extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    boolean isRadioButton1Checked = false;
    boolean isRadioButton2Checked = false;

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

        RadioButton radioButton1 = view.findViewById(R.id.radioButton1);
        RadioButton radioButton2 = view.findViewById(R.id.radioButton2);
        RadioButton radioButton3 = view.findViewById(R.id.radioButton3);
        RadioButton radioButton4 = view.findViewById(R.id.radioButton4);
        Button button7 = view.findViewById(R.id.button7);
        Button button8 = view.findViewById(R.id.button8);



        // Kiểm tra trạng thái của RadioButton1
        radioButton1.setOnClickListener(v -> {
            isRadioButton1Checked = radioButton1.isChecked();
            updateButtonState(button7, isRadioButton1Checked && isRadioButton2Checked);
        });

        // Kiểm tra trạng thái của RadioButton2
        radioButton2.setOnClickListener(v -> {
            isRadioButton2Checked = radioButton2.isChecked();
            updateButtonState(button7, isRadioButton1Checked && isRadioButton2Checked);
        });

        // Kiểm tra trạng thái của RadioButton1
        radioButton3.setOnClickListener(v -> {
            isRadioButton1Checked = radioButton3.isChecked();
            updateButtonState(button8, isRadioButton1Checked && isRadioButton2Checked);
        });

        // Kiểm tra trạng thái của RadioButton2
        radioButton4.setOnClickListener(v -> {
            isRadioButton2Checked = radioButton4.isChecked();
            updateButtonState(button8, isRadioButton1Checked && isRadioButton2Checked);
        });


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
    private void updateButtonState(Button button, boolean isCompleted) {
        if (isCompleted) {
            button.setText("Completed");
            button.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.btn_color1, null));
            button.setTextColor(getContext().getResources().getColor(R.color.white, null));
        } else {
            button.setText("Incomplete");
            button.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.white, null));
            button.setTextColor(getContext().getResources().getColor(R.color.black, null));
        }
    }
}
