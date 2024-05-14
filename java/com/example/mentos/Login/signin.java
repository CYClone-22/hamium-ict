package com.example.mentos.Login;

import static com.example.mentos.R.id.maleButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mentos.R;

public class signin extends AppCompatActivity {
    TextView back;
    EditText emailId, pwd, pwdCheck, birth;
    Button male, female, submit;
    RadioGroup radioGroup;
    String gender = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        //기입항목
        emailId = findViewById(R.id.sign_email);
        pwd = findViewById(R.id.sign_pwd);
        pwdCheck = findViewById(R.id.sign_pwd_check);
        birth = findViewById(R.id.singin_birth);
        radioGroup = findViewById(R.id.radioGroup);

        //성별 기입
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(R.id.maleButton == 1) {gender = "남";}
                else { gender = "여";}
            }
        });

        //회원가입 버튼
        submit = findViewById(R.id.signin_button);
        submit.setOnClickListener(v -> {
            Intent intent = new Intent(this, login.class);
            startActivity((intent));
        });
    }
}