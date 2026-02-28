package com.example.mentos.AI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentos.AI.Route.GoalResponse;
import com.example.mentos.R;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanViewHolder> {

    private List<GoalResponse.Plan> plans;

    public PlanAdapter(List<GoalResponse.Plan> plans) {
        this.plans = plans;
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_plan, parent, false);
        return new PlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        GoalResponse.Plan plan = plans.get(position);

        // 디버깅 로그
        Log.d("PlanAdapter", "Binding plan at position " + position);
        Log.d("PlanAdapter", "GoalNumber: " + plan.getGoalNumber());

        // Get CookingPlan object
        GoalResponse.Plan.CookingPlan cookingPlan = plan.getCookingPlan();

        if (cookingPlan != null) {
            Log.d("PlanAdapter", "DishName: " + cookingPlan.getDishName());
            Log.d("PlanAdapter", "Ingredients: " + cookingPlan.getIngredients());
            Log.d("PlanAdapter", "Tools: " + cookingPlan.getTools());
            Log.d("PlanAdapter", "Methods: " + cookingPlan.getMethods());

            holder.goalNumberTextView.setText("Goal: " + plan.getGoalNumber());
            holder.dishNameTextView.setText("Dish: " + cookingPlan.getDishName());
            holder.ingredientsTextView.setText("Ingredients: " + cookingPlan.getIngredients());
            holder.toolsTextView.setText("Tools: " + cookingPlan.getTools());

            StringBuilder methodsBuilder = new StringBuilder();
            if (cookingPlan.getMethods() != null) {
                for (GoalResponse.Plan.CookingPlan.Method method : cookingPlan.getMethods()) {
                    methodsBuilder.append("Step ").append(method.getStepNumber()).append(": ").append(method.getDescription()).append("\n");
                }
            }
            holder.methodsTextView.setText("Methods:\n" + methodsBuilder.toString());
        } else {
            // CookingPlan이 null일 경우 처리
            Log.d("PlanAdapter", "CookingPlan is null for goal number: " + plan.getGoalNumber());
            holder.dishNameTextView.setText("Dish: N/A");
            holder.ingredientsTextView.setText("Ingredients: N/A");
            holder.toolsTextView.setText("Tools: N/A");
            holder.methodsTextView.setText("Methods: N/A");
        }
    }

    @Override
    public int getItemCount() {
        return plans != null ? plans.size() : 0;
    }

    public void setPlans(List<GoalResponse.Plan> newPlans) {
        this.plans = newPlans;
        notifyDataSetChanged();
    }

    public static class PlanViewHolder extends RecyclerView.ViewHolder {
        public TextView goalNumberTextView;
        public TextView dishNameTextView;
        public TextView ingredientsTextView;
        public TextView toolsTextView;
        public TextView methodsTextView;

        public PlanViewHolder(@NonNull View itemView) {
            super(itemView);
            goalNumberTextView = itemView.findViewById(R.id.goal_number_text_view);
            dishNameTextView = itemView.findViewById(R.id.dish_name_text_view);
            ingredientsTextView = itemView.findViewById(R.id.ingredients_text_view);
            toolsTextView = itemView.findViewById(R.id.tools_text_view);
            methodsTextView = itemView.findViewById(R.id.methods_text_view);
        }
    }
}
