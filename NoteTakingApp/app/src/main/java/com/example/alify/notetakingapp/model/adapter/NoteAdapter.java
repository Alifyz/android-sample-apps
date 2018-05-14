package com.example.alify.notetakingapp.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.alify.notetakingapp.R;
import com.example.alify.notetakingapp.model.Note;
import java.util.ArrayList;


public class NoteAdapter extends BaseAdapter {

    ArrayList<Note> mNote = new ArrayList<>();
    Context mContext;

    public NoteAdapter( Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mNote.size();
    }

    @Override
    public Note getItem(int position) {
        return mNote.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.note_item, parent, false);
        }

        TextView mTitle = (TextView) convertView.findViewById(R.id.item_title);
        TextView mDescription = (TextView) convertView.findViewById(R.id.item_description);

        Note mCurrentNote = mNote.get(position);

        mTitle.setText(mCurrentNote.getTitle());
        mDescription.setText(mCurrentNote.getDescription());

        return convertView;
    }

    public void addNote(Note n) {
        mNote.add(n);
        notifyDataSetChanged();
    }
}
