package com.example.handynotes.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.handynotes.async.DeleteAsyncTask;
import com.example.handynotes.async.InsertAsyncTask;
import com.example.handynotes.async.UpdateAsyncTask;
import com.example.handynotes.model.Note;

import java.util.List;

public class NoteRepositrory {

    NoteDatabase mNoteDatabase;

    public NoteRepositrory(Context context) {

        mNoteDatabase = NoteDatabase.getInstance(context);
    }

    public void insertNoteTask(Note note){

        new InsertAsyncTask(mNoteDatabase.getNoteDAO()).execute(note);

    }

    public void updateNote(Note note){
        new UpdateAsyncTask(mNoteDatabase.getNoteDAO()).execute(note);
    }

    public LiveData<List<Note>> retrieveNotesTask(){
        return mNoteDatabase.getNoteDAO().getNotes();
    }

    public void deleteNote(Note note){

        new DeleteAsyncTask(mNoteDatabase.getNoteDAO()).execute(note);

    }
}
