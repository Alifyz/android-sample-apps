package alifyz.com.roomsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<List<WordEntity>> mAllWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getGetAllWords();
    }

    public LiveData<List<WordEntity>> getmAllWords() {
        return mAllWords;
    }

    public void insert(WordEntity wordEntity) {
        mRepository.insertWord(wordEntity);
    }
}
