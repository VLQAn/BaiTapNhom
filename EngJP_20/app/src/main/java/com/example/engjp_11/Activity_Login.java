package com.example.engjp_11;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.Arrays;

public class Activity_Login extends AppCompatActivity {

    private static final int REQUEST_CODE_PICK_ACCOUNT = 1001;
    private EditText emailEditText;
    private static final String EXTRA_NAME = "EXTRA_NAME";
    private CallbackManager callbackManager;// Request code for Google Sign-In
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.setClientToken("c479b6a08110e72c620b46a93fc021e3");
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this.getApplication());
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.editTextEmail);
        callbackManager = CallbackManager.Factory.create();

        // Bấm vào Google Icon
        findViewById(R.id.imageGG).setOnClickListener(v -> chooseAccount());

        // Bấm vào Facebook Icon
        findViewById(R.id.imageFaceBook).setOnClickListener(v -> signInWithFacebook());

        findViewById(R.id.btnContinue).setOnClickListener(v -> openNextActivity());
        // Dữ liệu mẫu
        // Kết nối tới Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

    }



    private void chooseAccount() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()  // Đảm bảo yêu cầu quyền truy cập email
                .requestProfile()  // Thêm quyền truy cập profile
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Đăng xuất tài khoản hiện tại (nếu có)
        mGoogleSignInClient.signOut().addOnCompleteListener(task -> {
            // Sau khi sign out, mở giao diện chọn tài khoản
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, REQUEST_CODE_PICK_ACCOUNT);
        });
    }



    private void signInWithFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(Activity_Login.this, "Facebook login canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(Activity_Login.this, "Facebook login failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            String email = user.getEmail();
                            emailEditText.setText(email);
                        }
                    } else {
                        Toast.makeText(Activity_Login.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getFacebookEmail(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    if (object.has("email")) {
                        String email = object.getString("email");
                        emailEditText.setText(email);
                    } else {
                        Toast.makeText(Activity_Login.this, "This Facebook account does not have an associated email", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "email");
        request.setParameters(parameters);
        request.executeAsync();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_ACCOUNT && resultCode == RESULT_OK) {
            // Lấy tài khoản từ kết quả của Intent
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    String email = account.getEmail();
                    String name = account.getDisplayName(); // Lấy tên từ tài khoản Google

                    // Điền email vào EditText
                    emailEditText.setText(email);

                    // Log để kiểm tra dữ liệu lấy được
                    Log.d("GoogleSignIn", "Email: " + email);
                    Log.d("GoogleSignIn", "Name: " + name);

                    // Tiến hành kiểm tra và thêm tài khoản vào Firebase nếu cần
                    checkFirebaseUser(email, name);
                }
            } catch (ApiException e) {
                e.printStackTrace();
                Toast.makeText(this, "Google sign-in failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }




    private void checkFirebaseUser(String email, String name) {
        databaseReference.orderByChild("email").equalTo(email)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // Nếu email đã tồn tại, lấy tên
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                String userName = userSnapshot.child("name").getValue(String.class);
                                emailEditText.setText(userName); // Hiển thị tên từ Firebase nếu cần
                            }
                        } else {
                            // Nếu email chưa tồn tại, thêm vào Firebase
                            String userId = databaseReference.push().getKey();
                            if (userId != null) {
                                // Sử dụng tên từ tài khoản Google
                                databaseReference.child(userId).setValue(new User(email, name));
                                Toast.makeText(Activity_Login.this, "Email mới đã được lưu", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(Activity_Login.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void openNextActivity() {
        String email = emailEditText.getText().toString().trim();

        if (!email.isEmpty()) {
            // Lấy tên từ Firebase dựa trên email
            databaseReference.orderByChild("email").equalTo(email)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                    String name = userSnapshot.child("name").getValue(String.class);

                                    // Mở Activity_Personal và truyền tên
                                    Intent intent = new Intent(Activity_Login.this, Activity_Personal.class);
                                    intent.putExtra("EXTRA_NAME", name);
                                    startActivity(intent);
                                }
                            } else {
                                Toast.makeText(Activity_Login.this, "Không tìm thấy tên trong Firebase", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            Toast.makeText(Activity_Login.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "Vui lòng chọn email trước!", Toast.LENGTH_SHORT).show();
        }
    }

}
