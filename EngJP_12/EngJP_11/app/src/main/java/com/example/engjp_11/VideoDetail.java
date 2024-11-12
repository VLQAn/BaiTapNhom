package com.example.engjp_11;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Video#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoDetail extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public VideoDetail() {
        // Required empty public constructor
    }

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_detail, container, false);

        // Khởi tạo VideoView
        VideoView videoView = view.findViewById(R.id.videoView);
        String videoPath = "android.resource://" + requireActivity().getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        // Tạo MediaController
        MediaController mediaController = new MediaController(requireContext());
        mediaController.setAnchorView(videoView); // Đặt MediaController vào giữa VideoView
        videoView.setMediaController(mediaController);

        // Thiết lập video để tự động lặp lại
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start(); // Phát lại video khi kết thúc
            }
        });

        // Bắt đầu phát video
        videoView.start();

        // Lấy tham chiếu đến TextView và thiết lập OnClickListener
        TextView txtSub = view.findViewById(R.id.txtSub);
        txtSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra văn bản hiện tại và thay đổi
                if (txtSub.getText().toString().equals("ALL")) {
                    txtSub.setText("EN");
                    txtSub.setTextColor(getResources().getColor(android.R.color.black));
                    txtSub.setBackground(getResources().getDrawable(R.drawable.lagsubbackgroundchange));
                }
                else if (txtSub.getText().toString().equals("EN")) {
                    txtSub.setText("VN");
                    txtSub.setTextColor(getResources().getColor(android.R.color.black));
                    txtSub.setBackground(getResources().getDrawable(R.drawable.lagsubbackgroundchange));
                }
                else if (txtSub.getText().toString().equals("VN")) {
                    txtSub.setText("OFF");
                    txtSub.setTextColor(getResources().getColor(android.R.color.black));
                    txtSub.setBackground(getResources().getDrawable(R.drawable.lagsubbackgroundchange));
                }
                else {
                    txtSub.setText("ALL");
                    txtSub.setTextColor(getResources().getColor(android.R.color.white));
                    txtSub.setBackground(getResources().getDrawable(R.drawable.lagsubbackground));
                }
            }
        });


        return view;
    }
}
