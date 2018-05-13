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
import android.widget.CheckBox;

import com.example.alify.notetakingapp.MainActivity;
import com.example.alify.notetakingapp.R;
import com.example.alify.notetakingapp.model.Note;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogNewNote extends DialogFragment {

    @BindView(R.id.btn_cancel)
    Button mCancelButton;

    @BindView(R.id.btn_ok)
    Button mOkButton;

    @BindView(R.id.title_dialog)
    TextInputEditText mTitle;

    @BindView(R.id.description_dialog)
    TextInputEditText mDescription;

    @BindView(R.id.ch_todo)
    CheckBox mIsTodo;

    @BindView(R.id.ch_important)
    CheckBox mIsImportant;

    @BindView(R.id.ch_idea)
    CheckBox mIsIdea;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_new_note, null);
        dialog.setView(dialogView);
        ButterKnife.bind(this, dialogView);

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Note newNote = new Note();
                newNote.setTitle(mTitle.getText().toString());
                newNote.setDescription(mDescription.getText().toString());
                newNote.setIdea(mIsIdea.isChecked());
                newNote.setImportant(mIsImportant.isChecked());
                newNote.setTodo(mIsTodo.isChecked());

                MainActivity callingActivity = (MainActivity)getActivity();
                callingActivity.createNewNote(newNote);

                dismiss();
            }
        });

        return dialog.create();
    }
}
