package alifyz.com.roomsample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWord(WordEntity word);

    @Delete
    void deleteWord(WordEntity word);

    @Query("DELETE  FROM word_table")
    void deleteWords();

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<WordEntity>> getAllWords();

}
