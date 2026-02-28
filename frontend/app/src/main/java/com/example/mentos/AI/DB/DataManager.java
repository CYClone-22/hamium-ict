package com.example.mentos.AI.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataManager {

    private DatabaseHelper dbHelper;

    public DataManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void saveToDatabase(int chatRoomId, int goalNumber, String dishName, String ingredients, String tools, int stepNumber, String stepDescription) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_CHAT_ROOM_ID, chatRoomId);
        values.put(DatabaseHelper.COLUMN_GOAL_NUMBER, goalNumber);
        values.put(DatabaseHelper.COLUMN_DISH_NAME, dishName);
        values.put(DatabaseHelper.COLUMN_INGREDIENTS, ingredients);
        values.put(DatabaseHelper.COLUMN_TOOLS, tools);
        values.put(DatabaseHelper.COLUMN_STEP_NUMBER, stepNumber);
        values.put(DatabaseHelper.COLUMN_STEP_DESCRIPTION, stepDescription);

        db.insert(DatabaseHelper.TABLE_NAME, null, values);
        db.close();
    }
}
