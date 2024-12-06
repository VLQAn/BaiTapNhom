package com.example.engjp_11;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;

public class Activity_Login extends AppCompatActivity {

    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 100;  // Request code for Google Sign-In

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView imageGoogle = findViewById(R.id.imageGG);
        ImageView imageFacebook = findViewById(R.id.imageFaceBook);

        // Thiết lập GoogleSignInClient
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()  // Yêu cầu quyền truy cập email
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        // Bấm vào biểu tượng Google để mở Google Sign-In
        imageGoogle.setOnClickListener(v -> signInWithGoogle());

        // Bấm vào biểu tượng Facebook để mở ứng dụng Facebook
        imageFacebook.setOnClickListener(v -> openFacebook());
    }

    // Mở giao diện đăng nhập của Google
    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Xử lý kết quả đăng nhập của Google
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    // Xử lý kết quả đăng nhập
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult();
            if (account != null) {
                // Đã đăng nhập thành công, có thể hiển thị thông tin tài khoản
                String email = account.getEmail();
                // Tiếp tục xử lý với email
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mở ứng dụng Facebook
    private void openFacebook() {
        Intent intent;
        try {
            // Mở ứng dụng Facebook nếu có
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/<page_id>"));
        } catch (Exception e) {
            // Nếu không có ứng dụng Facebook, mở Facebook trong trình duyệt
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
        }
        startActivity(intent);
    }
}
