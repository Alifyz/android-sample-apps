package com.example.alify.notetakingapp.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alify.notetakingapp.R;
import com.example.alify.notetakingapp.model.Note;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogShowNote extends DialogFragment {

    private Note mCurrentNote;

    TextView mTitle;
    TextInputEditText mDescription;
    Button mOkButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View showNoteView = inflater.inflate(R.layout.dialog_note, null);
        dialog.setView(showNoteView);

        mTitle = (TextView) showNoteView.findViewById(R.id.note_title);
        mDescription = (TextInputEditText) showNoteView.findViewById(R.id.note_description);

        mTitle.setText(mCurrentNote.getTitle());
        mDescription.setText(mCurrentNote.getDescription());
        mOkButton = (Button) showNoteView.findViewById(R.id.note_ok);

        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return dialog.create();
    }

    public void getSelectedNote(Note selectedNote) {
        mCurrentNote = selectedNote;
    }
}
