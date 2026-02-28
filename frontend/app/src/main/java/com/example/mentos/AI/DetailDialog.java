package com.example.mentos.AI;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mentos.R;

public class DetailDialog extends Dialog {

    private ImageView imageView;
    private TextView textView;
    private ImageButton btnNext, btnPrevious;

    private int[] imageResources;
    private String[] textContents;
    private int currentIndex = 0;

    public DetailDialog(Context context, int[] imageResources, String[] textContents) {
        super(context);
        this.imageResources = imageResources;
        this.textContents = textContents;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_detail);

        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        imageView = findViewById(R.id.dialog_image);
        textView = findViewById(R.id.dialog_text);
        btnNext = findViewById(R.id.button_Next);
        btnPrevious = findViewById(R.id.button_Previous);

        updateContent();

        btnNext.setOnClickListener(v -> {
            if (currentIndex < imageResources.length - 1) {
                currentIndex++;
                updateContent();
            }
        });

        btnPrevious.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateContent();
            }
        });
    }

    private void updateContent() {
        imageView.setImageResource(imageResources[currentIndex]);
        textView.setText(textContents[currentIndex]);
    }
}
