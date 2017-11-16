package com.alifyz.habittracker.Database;

import android.provider.BaseColumns;

/**
 * Created by Alifyz on 11/15/2017.
 */

public final class HabitContract  {

    private HabitContract() {}

    public static final class HabitDataEntry implements BaseColumns {

        public static final String TAB_NAME = "habits";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_HEIGHT = "height";
        public static final String COLUMN_WEIGHT = "weight";

        public static final int GENDER_MAN = 0;
        public static final int GENDER_WOMAN = 1;
        public static final int GENDER_NOT_DECLARED = 2;

    }


}
