package com.example.mentos.AI.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mentos_challenge.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "cooking_plan";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CHAT_ROOM_ID = "chat_room_id";
    public static final String COLUMN_GOAL_NUMBER = "goal_number";
    public static final String COLUMN_DISH_NAME = "dish_name";
    public static final String COLUMN_INGREDIENTS = "ingredients";
    public static final String COLUMN_TOOLS = "tools";
    public static final String COLUMN_STEP_NUMBER = "step_number";
    public static final String COLUMN_STEP_DESCRIPTION = "step_description";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CHAT_ROOM_ID + " INTEGER, " +
                    COLUMN_GOAL_NUMBER + " INTEGER, " +
                    COLUMN_DISH_NAME + " TEXT, " +
                    COLUMN_INGREDIENTS + " TEXT, " +
                    COLUMN_TOOLS + " TEXT, " +
                    COLUMN_STEP_NUMBER + " INTEGER, " +
                    COLUMN_STEP_DESCRIPTION + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
