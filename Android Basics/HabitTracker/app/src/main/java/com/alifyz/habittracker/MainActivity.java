package com.alifyz.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.alifyz.habittracker.Database.HabitContract.HabitDataEntry;
import com.alifyz.habittracker.Database.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InsertData(this);
        ReadData(this);
    }


    public void InsertData(Context context) {
        HabitDbHelper dbHelper = new HabitDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitDataEntry.COLUMN_NAME, "Alify");
        values.put(HabitDataEntry.COLUMN_GENDER, 1);
        values.put(HabitDataEntry.COLUMN_HEIGHT, 175);
        values.put(HabitDataEntry.COLUMN_WEIGHT, 80);

        long row = db.insert(HabitDataEntry.TAB_NAME, null, values);
        if(row == -1) {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "New row added", Toast.LENGTH_SHORT).show();
        }

    }

    public void ReadData(Context context) {
        HabitDbHelper dbHelper = new HabitDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection =  {HabitDataEntry.COLUMN_NAME};
        String selection = HabitDataEntry.COLUMN_GENDER + "= ?";
        String[] args = {"1"};

        Cursor c = db.query(HabitDataEntry.TAB_NAME,
                projection,selection,args, null, null, null);

        c.moveToFirst();
        String names = c.getString(0);
        Toast.makeText(context,names, Toast.LENGTH_LONG).show();
    }
}
