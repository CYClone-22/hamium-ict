package com.example.mentos.Community;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.mentos.R;

import java.io.IOException;

public class WritingBoard extends AppCompatActivity {

    private EditText writingTitle, writingContent;
    private Button submit;
    private ImageButton imageInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writing_board);

        writingTitle = findViewById(R.id.writeTitle);
        writingContent = findViewById(R.id.writeContent);
        submit = findViewById(R.id.submit);
        imageInsert = findViewById(R.id.image_insert);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //완료 버튼 클릭시 로직
                Toast.makeText(getApplicationContext(), "제출", Toast.LENGTH_SHORT).show();
            }
        });

        imageInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이미지 버튼 클릭시 로직
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ImageView imageView = findViewById(R.id.image);
            imageView.setImageBitmap(bitmap);
        }
    }

}
