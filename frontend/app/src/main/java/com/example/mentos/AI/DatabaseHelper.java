package com.example.mentos.AI;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table and column names
    public static final String TABLE_PLANS = "plans";
    public static final String TABLE_METHODS = "methods";
    public static final String COLUMN_GOAL_NUMBER = "goal_number";
    public static final String COLUMN_DISH_NAME = "dish_name";
    public static final String COLUMN_INGREDIENTS = "ingredients";
    public static final String COLUMN_TOOLS = "tools";
    public static final String COLUMN_PLAN_GOAL_NUMBER = "plan_goal_number"; // Foreign key
    public static final String COLUMN_STEP_NUMBER = "step_number";    public static final String COLUMN_DESCRIPTION = "description";

    // Database version
    private static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, "mentos.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create plans table
        db.execSQL("CREATE TABLE " + TABLE_PLANS + " ("
                + COLUMN_GOAL_NUMBER + " INTEGER PRIMARY KEY, "
                + COLUMN_DISH_NAME + " TEXT, "
                + COLUMN_INGREDIENTS + " TEXT, "
                + COLUMN_TOOLS + " TEXT)");

        // Create methods table
        db.execSQL("CREATE TABLE " + TABLE_METHODS + " ("
                + COLUMN_STEP_NUMBER + " INTEGER, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_PLAN_GOAL_NUMBER + " INTEGER, "
                + "FOREIGN KEY(" + COLUMN_PLAN_GOAL_NUMBER + ") REFERENCES " + TABLE_PLANS + "(" + COLUMN_GOAL_NUMBER + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            // Drop older tables if they exist
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_METHODS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANS);
            // Create new tables
            onCreate(db);
        }
    }
}
