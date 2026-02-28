package com.example.mentos.AI.Route;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GoalResponse {
    @SerializedName("plans")
    private List<Plan> plans;

    @SerializedName("message")  // 메시지 필드 추가
    private String message;

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    public String getMessage() { // 메시지 반환 메서드 추가
        return message;
    }

    public void setMessage(String message) { // 메시지 설정 메서드 추가
        this.message = message;
    }

    public static class Plan {
        @SerializedName("cooking_plan")
        private CookingPlan cookingPlan;

        @SerializedName("goal_number")
        private int goalNumber;

        @SerializedName("plan_id")
        private int planId;

        public CookingPlan getCookingPlan() {
            return cookingPlan;
        }

        public void setCookingPlan(CookingPlan cookingPlan) {
            this.cookingPlan = cookingPlan;
        }

        public int getGoalNumber() {
            return goalNumber;
        }

        public void setGoalNumber(int goalNumber) {
            this.goalNumber = goalNumber;
        }

        public int getPlanId() {
            return planId;
        }

        public void setPlanId(int planId) {
            this.planId = planId;
        }

        public static class CookingPlan {
            @SerializedName("dish_name")
            private String dishName;

            @SerializedName("id")
            private int id;

            @SerializedName("ingredients")
            private String ingredients;

            @SerializedName("methods")
            private List<Method> methods;

            @SerializedName("step")
            private int step;

            @SerializedName("tools")
            private String tools;

            public String getDishName() {
                return dishName;
            }

            public void setDishName(String dishName) {
                this.dishName = dishName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIngredients() {
                return ingredients;
            }

            public void setIngredients(String ingredients) {
                this.ingredients = ingredients;
            }

            public List<Method> getMethods() {
                return methods;
            }

            public void setMethods(List<Method> methods) {
                this.methods = methods;
            }

            public int getStep() {
                return step;
            }

            public void setStep(int step) {
                this.step = step;
            }

            public String getTools() {
                return tools;
            }

            public void setTools(String tools) {
                this.tools = tools;
            }

            public static class Method {
                @SerializedName("description")
                private String description;

                @SerializedName("step_number")
                private int stepNumber;

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public int getStepNumber() {
                    return stepNumber;
                }

                public void setStepNumber(int stepNumber) {
                    this.stepNumber = stepNumber;
                }
            }
        }
    }
}
