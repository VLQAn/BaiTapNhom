package com.example.engjp_11;

import android.content.ClipData;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Introductory_Listening extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private MediaPlayer mediaPlayer;

    private Button btnOk;
    private LinearLayout wordContainer1;
    private LinearLayout wordContainer2;
    private boolean isCompleted = false;

    private TextView sentenceView, sentenceView1, sentenceView2, sentenceView3, sentenceView4;
    private TextView sentenceView6, sentenceView7, sentenceView8;

    public Introductory_Listening() {
    }

    public static Introductory_Listening newInstance(String param1, String param2) {
        Introductory_Listening fragment = new Introductory_Listening();
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
        View view = inflater.inflate(R.layout.fragment_introductory_listening, container, false);

        ImageView btnPre = view.findViewById(R.id.img_vw_intro_listening_pre);
        ImageView btnSpeak = view.findViewById(R.id.ic_speak);
        sentenceView = view.findViewById(R.id.text_sentence);
        sentenceView1 = view.findViewById(R.id.text_sentence1);
        sentenceView2 = view.findViewById(R.id.text_sentence2);
        sentenceView3 = view.findViewById(R.id.text_sentence3);
        sentenceView4 = view.findViewById(R.id.text_sentence4);
        sentenceView6 = view.findViewById(R.id.text_sentence6);
        sentenceView7 = view.findViewById(R.id.text_sentence7);
        sentenceView8 = view.findViewById(R.id.text_sentence8);

        btnOk = view.findViewById(R.id.btn_ok);
        wordContainer1 = view.findViewById(R.id.word_container1);
        wordContainer2 = view.findViewById(R.id.word_container2);

        // Set touch listener cho các button từ khóa
        for (int i = 0; i < wordContainer1.getChildCount(); i++) {
            View wordButton1 = wordContainer1.getChildAt(i);
            wordButton1.setOnTouchListener(new WordTouchListener());
        }
        for (int i = 0; i < wordContainer2.getChildCount(); i++) {
            View wordButton2 = wordContainer2.getChildAt(i);
            wordButton2.setOnTouchListener(new WordTouchListener());
        }

        // Set drag listener cho các TextView có chỗ trống
        sentenceView.setOnDragListener(new SentenceDragListener());
        sentenceView1.setOnDragListener(new SentenceDragListener());
        sentenceView2.setOnDragListener(new SentenceDragListener());
        sentenceView3.setOnDragListener(new SentenceDragListener());
        sentenceView4.setOnDragListener(new SentenceDragListener());
        sentenceView6.setOnDragListener(new SentenceDragListener());
        sentenceView7.setOnDragListener(new SentenceDragListener());
        sentenceView8.setOnDragListener(new SentenceDragListener());

        // Sự kiện click cho button OK
        btnOk.setOnClickListener(v -> {
            // Xử lý khi người dùng nhấn nút OK sau khi điền từ
        });

        // Sự kiện OnClickListener cho ImageView btnPre
        btnPre.setOnClickListener(v -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack();
            } else {
                getActivity().getOnBackPressedDispatcher().onBackPressed();
            }
        });

        // Sự kiện OnClickListener cho ImageView btnSpeak
        btnSpeak.setOnClickListener(v -> {
            playAudio();
        });

        return view;
    }

    private class WordTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDragAndDrop(null, shadowBuilder, view, 0);
                return true;
            }
            return false;
        }
    }

    private class SentenceDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View view, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DROP:
                    View draggedView = (View) event.getLocalState();
                    Button draggedButton = (Button) draggedView;
                    String word = draggedButton.getText().toString();

                    // Cập nhật TextView với từ được kéo thả
                    TextView sentenceView = (TextView) view;
                    String currentText = sentenceView.getText().toString();
                    currentText = currentText.replaceFirst("_+", word); // Thay thế "_" bằng từ mới
                    sentenceView.setText(currentText);


                    // Kiểm tra nếu tất cả các TextView đã được điền từ
                    if (isAllSentencesFilled()) {
                        isCompleted = true;
                        btnOk.setEnabled(true);
                        btnOk.setBackgroundColor(Color.parseColor("#033495"));
                    }
                    draggedView.setVisibility(View.INVISIBLE);
                    break;
            }
            return true;
        }

        private boolean isAllSentencesFilled() {
            // Kiểm tra tất cả các TextView không còn dấu "_" nào
            return !sentenceView.getText().toString().contains("_") &&
                    !sentenceView1.getText().toString().contains("_") &&
                    !sentenceView2.getText().toString().contains("_") &&
                    !sentenceView3.getText().toString().contains("_") &&
                    !sentenceView4.getText().toString().contains("_") &&
                    !sentenceView6.getText().toString().contains("_") &&
                    !sentenceView7.getText().toString().contains("_") &&
                    !sentenceView8.getText().toString().contains("_");
        }
    }

    private void playAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.music1);
        mediaPlayer.start();

        mediaPlayer.setOnCompletionListener(mp -> {
            mp.release();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
