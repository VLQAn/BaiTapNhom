package com.example.engjp_11;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        View rootView = inflater.inflate(R.layout.fragment_class_course, container, false);

        listView = rootView.findViewById(R.id.class_course_listView);
        courseList = new ArrayList<>();
        adapter = new ClassCourseAdapter(getContext(), courseList);
        listView.setAdapter(adapter);

        // Firebase reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("courses");

        // Fetch data from Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                courseList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ClassCourseItem course = snapshot.getValue(ClassCourseItem.class);
                    if (course != null) {
                        Log.d("FirebaseData", "imageResId: " + course.getImageResId());
                        courseList.add(course);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("FirebaseError", databaseError.getMessage());
            }
        });

// Thêm sự kiện click cho ListView
        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            // Lấy item được click
            ClassCourseItem selectedItem = courseList.get(position);

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
        return rootView;
    }
}