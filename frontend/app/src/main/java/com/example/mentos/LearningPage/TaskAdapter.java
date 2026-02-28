package com.example.mentos.LearningPage;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mentos.Model.Task;
import com.example.mentos.R;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Context context;
    private List<Task> taskList;

    public TaskAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskContent.setText(task.getContent());

        // Remove any previously set listeners to avoid multiple calls
        holder.taskCheckButton.setOnCheckedChangeListener(null);

        holder.taskCheckButton.setChecked(task.isCompleted());
        updateItemAppearance(holder, task);

        holder.taskCheckButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setCompleted(isChecked);
            updateItemAppearance(holder, task);

            // UI 업데이트를 안전하게 지연시킴
            new Handler(Looper.getMainLooper()).post(() -> {
                notifyItemChanged(holder.getAdapterPosition());
            });
        });
    }


    private void updateItemAppearance(TaskViewHolder holder, Task task) {
        if (task.isCompleted()) {
            holder.taskContent.setPaintFlags(holder.taskContent.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
        } else {
            holder.taskContent.setPaintFlags(holder.taskContent.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskContent;
        CheckBox taskCheckButton;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskContent = itemView.findViewById(R.id.task_content);
            taskCheckButton = itemView.findViewById(R.id.task_check_button);
        }
    }
}
