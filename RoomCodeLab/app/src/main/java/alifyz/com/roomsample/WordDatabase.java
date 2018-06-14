package alifyz.com.roomsample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {WordEntity.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {

    private static WordDatabase mInstance;

    //SingleTon Pattern
    public static WordDatabase getmInstance(final Context context) {
        if(mInstance == null) {
            synchronized (WordDatabase.class) {
                if(mInstance == null) {
                    mInstance = Room.databaseBuilder(
                            context,
                            WordDatabase.class,
                            "word_database").build();
                }
            }
        }
        return mInstance;
    }

    public abstract WordDao wordDao();
}
