package com.example.mentos.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.mentos.Home.Nav_main;
import com.example.mentos.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private AlertDialog dialog;
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS_NAME = "MentosPrefs";
    private static final String KEY_GUEST_ID = "guest_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.login);  // 액티비티의 레이아웃 설정

        sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);

        Button signinButton = findViewById(R.id.btn_sign_in);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });

        emailEditText = findViewById(R.id.email_text);
        passwordEditText = findViewById(R.id.password_text);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // 서버 응답에서 message와 guest_id를 가져옴
                    String message = response.getString("message");
                    int guestId = response.optInt("guest_id", -1); // guest_id가 없거나 잘못된 경우 -1로 설정

                    if (message != null && message.toLowerCase().contains("success")) {
                        // guest_id를 SharedPreferences에 저장
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt(KEY_GUEST_ID, guestId);
                        editor.apply();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("로그인에 성공했습니다.");
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                                // AlertDialog의 텍스트 뷰를 가져와서 중앙에 배치
                                TextView textView = alertDialog.findViewById(android.R.id.message);
                                if (textView != null) {
                                    textView.setGravity(Gravity.CENTER);  // 텍스트를 중앙에 배치
                                }

                                // 0.6초 후에 AlertDialog를 닫는 Handler 추가
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (alertDialog.isShowing()) {
                                            alertDialog.dismiss();
                                        }
                                        // Nav_main 액티비티로 이동
                                        Intent intent = new Intent(LoginActivity.this, Nav_main.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }, 400); // 0.4초(400ms) 지연
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("계정을 다시 확인하세요.")
                                        .setNegativeButton("다시 시도", null)
                                        .show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("LoginActivity", "JSON parsing error: " + e.getMessage());
                }
            }
        };

        try {
            LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(loginRequest);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("LoginActivity", "Failed to create LoginRequest: " + e.getMessage());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
