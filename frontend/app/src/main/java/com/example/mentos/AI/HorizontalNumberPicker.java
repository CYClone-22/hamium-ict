package com.example.mentos.AI;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;

public class HorizontalNumberPicker extends NumberPicker {

    public HorizontalNumberPicker(Context context) {
        super(context);
        init();
    }

    public HorizontalNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 설정 및 스타일 조정
        this.setOrientation(HORIZONTAL);
    }

    @Override
    public void setOrientation(int orientation) {
        // 가로 방향으로 설정되도록 무시
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 레이아웃 조정 로직
        super.onLayout(changed, l, t, r, b);
        // 가로 방향으로 스크롤 가능하도록 조정
    }
}
