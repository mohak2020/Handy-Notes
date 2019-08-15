package com.example.handynotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.handynotes.adapters.NotesAdapter;
import com.example.handynotes.model.Note;
import com.example.handynotes.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //ui components
    RecyclerView mRecycleView;

    //vars
    NotesAdapter mNoteAdapter;
    ArrayList<Note>mNotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mRecycleView = findViewById(R.id.note_recycle_view);

        initRecycleView();
        insertFakeNotes();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Notes");


    }

    public void insertFakeNotes(){

        for(int i=0;i<50;i++){
            Note note = new Note();

            note.setTitle("title "+ i+1);
            note.setTimeStamp("Dec 2017");

            mNotes.add(note);

        }

        mNoteAdapter.notifyDataSetChanged();
    }


    public void initRecycleView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mNoteAdapter = new NotesAdapter(mNotes);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecycleView.addItemDecoration(itemDecorator);
        mRecycleView.setAdapter(mNoteAdapter);
    }
}
