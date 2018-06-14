package alifyz.com.roomsample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mWords != null ){
            WordEntity currentWord = mWords.get(position);
            holder.mWordText.setText(currentWord.getWord());
        }else {
            holder.mWordText.setText("Empty list");
        }
    }

    @Override
    public int getItemCount() {
        if(mWords != null) {
            return mWords.size();
        }else {
            return 0;
        }
    }

    void setWords(List<WordEntity> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mWordText;
        public ViewHolder(View view) {
            super(view);
            mWordText = view.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater inflater;
    private List<WordEntity> mWords;

    public WordAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }
}
