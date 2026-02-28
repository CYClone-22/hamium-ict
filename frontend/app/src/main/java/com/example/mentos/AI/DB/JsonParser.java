package com.example.mentos.AI.DB;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {
    public void parseJson(String jsonResponse) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray plansArray = jsonObject.getJSONArray("plans");

            for (int i = 0; i < plansArray.length(); i++) {
                JSONObject planObject = plansArray.getJSONObject(i);
                JSONObject cookingPlan = planObject.getJSONObject("cooking_plan");

                String dishName = cookingPlan.getString("dish_name");
                int id = cookingPlan.getInt("id");
                String ingredients = cookingPlan.getString("ingredients");
                String tools = cookingPlan.getString("tools");
                int step = cookingPlan.getInt("step");

                JSONArray methodsArray = cookingPlan.getJSONArray("methods");
                for (int j = 0; j < methodsArray.length(); j++) {
                    JSONObject method = methodsArray.getJSONObject(j);
                    int stepNumber = method.getInt("step_number");
                    String description = method.getString("description");

                    // Store data in database
                    saveToDatabase(id, dishName, ingredients, tools, step, stepNumber, description);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveToDatabase(int id, String dishName, String ingredients, String tools, int step, int stepNumber, String description) {
        // Placeholder method for saving to database
    }
}
