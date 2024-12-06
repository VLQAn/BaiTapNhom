package com.example.engjp_11;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Class_Course#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Class_Course extends Fragment {

    private ListView listView;
    private ClassCourseAdapter adapter;
    private List<ClassCourseItem> courseList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Class_Course() {
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
        View rootView = inflater.inflate(R.layout.fragment_class_course, container, false);

        listView = rootView.findViewById(R.id.class_course_listView);

        // Khởi tạo danh sách các khóa học
        courseList = new ArrayList<>();
        courseList.add(new ClassCourseItem(R.drawable.img_class_course_item_daniel, "Mr. Daniel", "Common daily sentences"));
        courseList.add(new ClassCourseItem(R.drawable.img_class_course_item_simpson, "The Simpsons", "Addressing family members"));

        // Tạo adapter và gán cho ListView
        adapter = new ClassCourseAdapter(getContext(), courseList);
        listView.setAdapter(adapter);

        // Xử lý khi một mục trong ListView được nhấn
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Tạo một instance của fragment_class_course_choosed
                Class_Course_Choosed fragmentChoosed = new Class_Course_Choosed();

                // Bắt đầu transaction để thay thế fragment
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.class_view, new Class_Course_Choosed()); // fragment_container là ID của layout chứa fragment
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return rootView;
    }
}