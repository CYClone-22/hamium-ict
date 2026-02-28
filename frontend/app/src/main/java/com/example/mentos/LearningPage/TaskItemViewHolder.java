package com.example.mentos.LearningPage;

import android.graphics.Paint;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.R;

public class TaskItemViewHolder extends RecyclerView.ViewHolder {
    public TextView taskContent;
    public CheckBox taskCheckButton;

    public TaskItemViewHolder(@NonNull View itemView) {
        super(itemView);
        taskContent = itemView.findViewById(R.id.task_content);
        taskCheckButton = itemView.findViewById(R.id.task_check_button);

        taskCheckButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // 체크된 상태에서 텍스트에 줄 긋기
                taskContent.setPaintFlags(taskContent.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                // 체크된 상태에서 배경 색상 변경
                itemView.setBackgroundColor(itemView.getResources().getColor(R.color.colorLightGray));
            } else {
                // 체크 해제 상태에서 줄 긋기 제거
                taskContent.setPaintFlags(taskContent.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                // 체크 해제 상태에서 배경 색상 원래대로 복원
                itemView.setBackgroundColor(itemView.getResources().getColor(android.R.color.transparent));
            }
        });
    }
}
