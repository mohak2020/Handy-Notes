package com.example.handynotes;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.handynotes.adapters.NotesAdapter;
import com.example.handynotes.database.NoteRepositrory;
import com.example.handynotes.model.Note;
import com.example.handynotes.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesAdapter.NotesAdapterOnclickListener,
        FloatingActionButton.OnClickListener {

    //ui components
    RecyclerView mRecycleView;

    //vars
    NotesAdapter mNoteAdapter;
    ArrayList<Note>mNotes = new ArrayList<>();
    NoteRepositrory mNoteRepositrory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.fab).setOnClickListener(this);


        mRecycleView = findViewById(R.id.note_recycle_view);

        mNoteRepositrory = new NoteRepositrory(this);

        retrieveNotes();
        initRecycleView();
        //insertFakeNotes();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Notes");


    }

    public void insertFakeNotes(){

        for(int i=0;i<50;i++){
            Note note = new Note();

            note.setTitle("title "+ i);
            note.setContent("content "+ i);
            note.setTimeStamp("Dec 2017");

            mNotes.add(note);

        }

        mNoteAdapter.notifyDataSetChanged();
    }

    private void retrieveNotes(){

        mNoteRepositrory.retrieveNotesTask().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                if(mNotes.size()>0){
                    mNotes.clear();
                }

                if(mNotes!=null){
                    mNotes.addAll(notes);
                }

                mNoteAdapter.notifyDataSetChanged();
            }
        });

    }


    public void initRecycleView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mNoteAdapter = new NotesAdapter(mNotes,this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecycleView);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecycleView.addItemDecoration(itemDecorator);
        mRecycleView.setAdapter(mNoteAdapter);
    }

    @Override
    public void onItemclick(int position) {

        Intent intent = new Intent(MainActivity.this,NoteActivity.class);
        intent.putExtra("selected_note",mNotes.get(position));
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,NoteActivity.class);
        startActivity(intent);
    }

    private void deleteNote(Note note){
        mNotes.remove(note);
        mNoteAdapter.notifyDataSetChanged();
        mNoteRepositrory.deleteNote(note);
    }


    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            deleteNote(mNotes.get(viewHolder.getAdapterPosition()));

        }
    };
}
