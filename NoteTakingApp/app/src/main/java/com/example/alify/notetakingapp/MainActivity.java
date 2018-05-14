package com.example.alify.notetakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.alify.notetakingapp.model.Note;
import com.example.alify.notetakingapp.model.adapter.NoteAdapter;
import com.example.alify.notetakingapp.ui.DialogNewNote;
import com.example.alify.notetakingapp.ui.DialogShowNote;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private NoteAdapter mAdapter;

    @BindView(R.id.listview)
    ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAdapter = new NoteAdapter(this);
        mList.setAdapter(mAdapter);


        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note tempNote = mAdapter.getItem(position);
                DialogShowNote dialog = new DialogShowNote();
                dialog.getSelectedNote(tempNote);
                dialog.show(getSupportFragmentManager(), "Show");
            }
        });
    }

    public void createNewNote(Note newNote) {
        mAdapter.addNote(newNote);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_add); {
            DialogNewNote dialog = new DialogNewNote();
            dialog.show(getSupportFragmentManager(), "");
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
