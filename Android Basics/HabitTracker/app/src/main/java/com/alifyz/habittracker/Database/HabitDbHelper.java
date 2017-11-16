package com.alifyz.habittracker.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.alifyz.habittracker.Database.HabitContract.HabitDataEntry;

/**
 * Created by Alifyz on 11/15/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "habit.db";
    public static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String CREATE_TABLE = "CREATE TABLE " + HabitDataEntry.TAB_NAME + " ("
                +HabitDataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +HabitDataEntry.COLUMN_NAME + " TEXT NOT NULL, "
                +HabitDataEntry.COLUMN_GENDER + " INTEGER NOT NULL, "
                +HabitDataEntry.COLUMN_HEIGHT + " INTEGER NOT NULL, "
                +HabitDataEntry.COLUMN_WEIGHT + " INTEGER NOT NULL);";

        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //TODO - Implement Update DB
    }
}
