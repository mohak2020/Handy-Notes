package com.example.handynotes.async;

import android.os.AsyncTask;

import com.example.handynotes.database.NoteDAO;
import com.example.handynotes.model.Note;

public class InsertAsyncTask extends AsyncTask<Note,Void,Void> {

    private NoteDAO mNoteDAO;

    public InsertAsyncTask(NoteDAO noteDAO) {
        mNoteDAO = noteDAO;
    }

    @Override
    protected Void doInBackground(Note... notes) {

        mNoteDAO.insertNotes(notes);

        return null;
    }
}
