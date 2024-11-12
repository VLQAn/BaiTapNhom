package com.example.engjp_11;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.gms.common.AccountPicker;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import java.util.Arrays;

public class Activity_Login extends AppCompatActivity {

    private static final int REQUEST_CODE_PICK_ACCOUNT = 1001;
    private EditText emailEditText;
    private CallbackManager callbackManager;// Request code for Google Sign-In

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.setClientToken("c479b6a08110e72c620b46a93fc021e3");
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this.getApplication());
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        callbackManager = CallbackManager.Factory.create();

        // Bấm vào Google Icon
        findViewById(R.id.imageGG).setOnClickListener(v -> chooseAccount());

        // Bấm vào Facebook Icon
        findViewById(R.id.imageFaceBook).setOnClickListener(v -> signInWithFacebook());

        findViewById(R.id.btnContinue).setOnClickListener(v -> openNextActivity());

        // Dữ liệu mẫu


    }

    private void chooseAccount() {
        Intent intent = AccountPicker.newChooseAccountIntent(
                null, null, new String[]{"com.google"}, false, null, null, null, null);
        startActivityForResult(intent, REQUEST_CODE_PICK_ACCOUNT);
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

        if (requestCode == REQUEST_CODE_PICK_ACCOUNT && resultCode == RESULT_OK && data != null) {
            String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
            emailEditText.setText(accountName);  // Điền email vào EditText
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data); // Facebook callback
        }
    }


    private void openNextActivity() {
        Intent intent = new Intent(Activity_Login.this, Activity_Personal.class);
        startActivity(intent);
    }
}
