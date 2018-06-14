package alifyz.com.roomsample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {

    private WordDao mWordDaoRepository;
    private LiveData<List<WordEntity>> getAllWords;

    public WordRepository(Application application) {
        WordDatabase database = WordDatabase.getmInstance(application);
        mWordDaoRepository = database.wordDao();
        getAllWords = mWordDaoRepository.getAllWords();
    }

    public LiveData<List<WordEntity>> getGetAllWords() {
        return getAllWords;
    }

    public void insertWord(WordEntity word) {
        new insertAsync(mWordDaoRepository).execute(word);
    }

    private static class insertAsync extends AsyncTask<WordEntity, Void, Void> {
        private WordDao mAsyncDao;

        public  insertAsync(WordDao dao) {
            this.mAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(WordEntity... wordEntities) {
            mAsyncDao.insertWord(wordEntities[0]);
            return null;
        }
    }
}
