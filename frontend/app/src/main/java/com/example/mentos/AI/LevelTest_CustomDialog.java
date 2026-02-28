package com.example.mentos.AI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.mentos.R;

public class LevelTest_CustomDialog extends Dialog {

    private ImageButton buttonClose;
    private Button option1, option2, option3, option4, option5;
    private ImageButton btnPrevious, btnNext;

    private Context context;
    private int dialogIndex;
    private DialogManager dialogManager;
    private OnLevelTestCompleteListener levelTestCompleteListener;

    private Button selectedButton = null;
    private int selectedOptionIndex = -1; // 사용자가 선택한 옵션 인덱스, -1은 아무것도 선택되지 않음을 의미

    public interface OnLevelTestCompleteListener {
        void onLevelTestComplete();
    }

    public LevelTest_CustomDialog(Context context, int dialogIndex, DialogManager dialogManager, OnLevelTestCompleteListener listener) {
        super(context);
        this.context = context;
        this.dialogIndex = dialogIndex;
        this.dialogManager = dialogManager;
        this.levelTestCompleteListener = listener; // 리스너 초기화
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = getLayoutResId(dialogIndex);
        setContentView(layoutResId);

        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        buttonClose = findViewById(R.id.button_close);
        option1 = findViewById(R.id.option_1);
        option2 = findViewById(R.id.option_2);
        option3 = findViewById(R.id.option_3);
        option4 = findViewById(R.id.option_4);
        option5 = findViewById(R.id.option_5);
        btnNext = findViewById(R.id.button_Next);
        btnPrevious = findViewById(R.id.button_Previous);

        setupDialog();

        buttonClose.setOnClickListener(v -> showCloseConfirmationDialog());

        if (btnPrevious != null) {
            btnPrevious.setOnClickListener(v -> {
                dialogManager.showPreviousDialog(dialogIndex);
                dismiss();
            });
        }

        if (btnNext != null) {
            btnNext.setOnClickListener(v -> {
                if (dialogIndex == 4) { // 마지막 다이얼로그 (leveltest_5)
                    dialogManager.calculateAndSaveLevel(); // 전체 점수 계산 및 저장
                    Log.d("LevelTest_CustomDialog", "Score save, and before the levelTestCompleteListener");

                    if (levelTestCompleteListener != null) {
                        levelTestCompleteListener.onLevelTestComplete(); // 콜백 호출
                    } else {
                        Log.d("LevelTest_CustomDialog", "levelTestCompleteListener = null");
                    }

                    // 점수와 레벨을 Toast로 표시
                    int totalScore = dialogManager.getTotalScore(); // 총 점수를 가져옵니다
                    int level = DialogManager.level; // 계산된 레벨
                    String toastMessage = "당신의 점수는 " + totalScore + "점입니다!\n당신의 레벨은 " + level + "입니다!";
                    Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show();

                    // 0.5초 후에 다이얼로그를 종료
                    new Handler().postDelayed(this::dismiss, 1500);
                } else {
                    dialogManager.showNextDialog(dialogIndex + 1);
                }
                dismiss(); // 다이얼로그 종료
            });
        }
    }

    private int getLayoutResId(int index) {
        switch (index) {
            case 0: return R.layout.leveltest_1;
            case 1: return R.layout.leveltest_2;
            case 2: return R.layout.leveltest_3;
            case 3: return R.layout.leveltest_4;
            case 4: return R.layout.leveltest_5;
            default: throw new IllegalArgumentException("Invalid dialog index");
        }
    }

    @Override
    public void show() {
        super.show();
        getWindow().getDecorView().startAnimation(AnimationUtils.loadAnimation(context, R.anim.fab_scale_up));
    }

    private void setupDialog() {
        int[] scores;
        switch (dialogIndex) {
            case 0:
                scores = new int[]{0, 0, 1, 0, 0}; // leveltest1.xml
                btnPrevious.setEnabled(false); // leveltest_1에서는 이전 버튼 비활성화
                break;
            case 1:
                scores = new int[]{0, 0, 0, 1, 0}; // leveltest2.xml
                break;
            case 2:
                scores = new int[]{0, 0, 1, 0, 0}; // leveltest3.xml
                break;
            case 3:
                scores = new int[]{0, 0, 0, 1, 0}; // leveltest4.xml
                break;
            case 4:
                scores = new int[]{1, 0, 0, 0, 0}; // leveltest5.xml
                break;
            default:
                scores = new int[]{0, 0, 0, 0, 0}; // 기본값
                break;
        }

        option1.setOnClickListener(v -> handleButtonClick(v, scores[0], 0));
        option2.setOnClickListener(v -> handleButtonClick(v, scores[1], 1));
        option3.setOnClickListener(v -> handleButtonClick(v, scores[2], 2));
        option4.setOnClickListener(v -> handleButtonClick(v, scores[3], 3));
        option5.setOnClickListener(v -> handleButtonClick(v, scores[4], 4));

        restoreSelection();
    }

    private void handleButtonClick(View view, int points, int index) {
        // 현재 선택된 버튼의 점수를 저장
        dialogManager.saveScore(dialogIndex, points);

        if (selectedButton != null && selectedButton != view) {
            resetButtonState(selectedButton);
        }

        selectedButton = (Button) view;
        selectedOptionIndex = index;

        // 선택된 버튼의 점수와 상태를 업데이트
        updateScore(points);

        view.setBackgroundResource(R.drawable.leveltest_rounded_button_purple);
        ((Button) view).setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
    }

    private void resetButtonState(Button button) {
        button.setBackgroundResource(R.drawable.leveltest_rounded_button_lightgray);
        button.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));
    }

    private void updateScore(int points) {
        // 다이얼로그의 점수 업데이트
        dialogManager.selectedScores[dialogIndex] = points;
    }

    private void showCloseConfirmationDialog() {
        new AlertDialog.Builder(context)
                .setTitle("진행 저장")
                .setMessage("제출 전 창을 닫으면 진행도가 저장되지 않습니다. 닫으시겠습니까?")
                .setPositiveButton("예", (dialog, which) -> dismiss())
                .setNegativeButton("아니오", null)
                .show();
    }

    private void restoreSelection() {
        // 선택된 옵션을 복원
        Button[] buttons = {option1, option2, option3, option4, option5};
        int selectedScore = dialogManager.selectedScores[dialogIndex];

        if (selectedScore > 0) {
            int index = selectedScore - 1;
            if (index >= 0 && index < buttons.length) {
                selectedButton = buttons[index];
                selectedButton.setBackgroundResource(R.drawable.leveltest_rounded_button_purple);
                selectedButton.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
                selectedOptionIndex = index;
            }
        }
    }

    public static class DialogManager {
        private Context context;
        private int[] selectedScores = new int[5]; // 각 다이얼로그에서 선택된 점수 저장
        private OnLevelTestCompleteListener listener;

        // 'public static'으로 level 변수 선언
        public static int level;

        public DialogManager(Context context, OnLevelTestCompleteListener listener) {
            this.context = context;
            this.listener = listener;
        }

        public void showDialog(int dialogIndex) {
            if (dialogIndex >= 0 && dialogIndex < 5) {
                LevelTest_CustomDialog dialog = new LevelTest_CustomDialog(context, dialogIndex, this, listener);
                dialog.show();
            } else {
                Log.e("DialogManager", "Invalid dialog index: " + dialogIndex);
            }
        }

        public void showPreviousDialog(int dialogIndex) {
            if (dialogIndex > 0) {
                LevelTest_CustomDialog dialog = new LevelTest_CustomDialog(context, dialogIndex - 1, this, listener);
                dialog.show();
                dialog.getWindow().setWindowAnimations(R.style.DialogSlideInFromLeft); // 왼쪽으로 슬라이드 애니메이션
            }
        }

        public void showNextDialog(int dialogIndex) {
            if (dialogIndex < 5) {
                LevelTest_CustomDialog dialog = new LevelTest_CustomDialog(context, dialogIndex, this, listener);
                dialog.show();
                dialog.getWindow().setWindowAnimations(R.style.DialogSlideInFromRight); // 오른쪽으로 슬라이드 애니메이션
            }
        }

        public void saveScore(int dialogIndex, int score) {
            if (dialogIndex >= 0 && dialogIndex < selectedScores.length) {
                selectedScores[dialogIndex] = score;
            } else {
                Log.e("DialogManager", "Invalid dialog index: " + dialogIndex);
            }
        }

        public void calculateAndSaveLevel() {
            int totalScore = 0;
            for (int score : selectedScores) {
                totalScore += score;
            }

            int calculatedLevel;
            if (totalScore >= 0 && totalScore <= 1) {
                calculatedLevel = 1;
            } else if (totalScore >= 2 && totalScore <= 3) {
                calculatedLevel = 2;
            } else if (totalScore >= 4 && totalScore <= 5) {
                calculatedLevel = 3;
            } else {
                calculatedLevel = 0; // 기본값 또는 오류 처리
            }

            // 'public static level' 변수에 계산된 level을 저장
            level = calculatedLevel;

            SharedPreferences prefs = context.getSharedPreferences("MentosPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("totalScore", totalScore);
            editor.putInt("level", calculatedLevel);
            editor.apply();

            Log.i("DialogManager", "Total Score: " + totalScore);
            Log.i("DialogManager", "Level: " + level);
        }

        // Total Score를 가져오는 메서드 추가
        public int getTotalScore() {
            int totalScore = 0;
            for (int score : selectedScores) {
                totalScore += score;
            }
            return totalScore;
        }
    }
}
