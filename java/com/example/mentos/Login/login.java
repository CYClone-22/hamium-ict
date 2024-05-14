package com.example.mentos.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mentos.Home.home;
import com.example.mentos.R;

public class login extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button LoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);  // 액티비티의 레이아웃 설정

        Button LoginButton = findViewById(R.id.login_button);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, home.class);
                startActivity(intent);
            }
        });

        Button SigninButton = findViewById(R.id.btn_sign_in);
        SigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, signin.class);
                startActivity(intent);
            }
        });


//        emailEditText = findViewById(R.id.email_edittext);
//        passwordEditText = findViewById(R.id.password_edittext);
//        LoginButton = findViewById(R.id.login_button);
//
//        LoginButton.setOnClickListener(new View.OnCickListener() {
//            @Override
//            public void OnClick(View view) {
//                //로그인 버튼 클릭 시 처리
//                String email = emailEditText.getText().toString();
//                String password = passwordEditText.getText().toString();
//
//
//            }
//        })
    }
}
