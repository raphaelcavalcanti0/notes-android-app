package com.rcfin.notes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;


public class Notes extends AppCompatActivity {

    RecyclerView recyclerView;
    BancoDeDados db;
    ImageView actionBarLeft;
    ImageView actionBarRight;

    ArrayList<String> note_id, note_title, note_note;

    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        db = new BancoDeDados(Notes.this);

        actionBarLeft = findViewById(R.id.actionBarLeft);
        actionBarLeft.setVisibility(View.GONE);

        actionBarRight = findViewById(R.id.actionBarRight);
        actionBarRight.setVisibility(View.GONE);

        FloatingActionButton fab = findViewById(R.id.add_btn);
        recyclerView = findViewById(R.id.recycler);

        note_id = new ArrayList<>();
        note_title = new ArrayList<>();
        note_note = new ArrayList<>();
        displayData();

        notesAdapter = new NotesAdapter(Notes.this, note_id, note_title, note_note);
        recyclerView.setAdapter(notesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Notes.this));

        fab.setOnClickListener(view -> {
            Intent addNotes = new Intent(getApplicationContext(), AddNotes.class);
            startActivity(addNotes);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        note_id = new ArrayList<>();
        note_title = new ArrayList<>();
        note_note = new ArrayList<>();
        displayData();

        notesAdapter = new NotesAdapter(Notes.this, note_id, note_title, note_note);
        recyclerView.setAdapter(notesAdapter);
    }

    void displayData() {
        Cursor cursor = db.readAllData();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                note_id.add(cursor.getString(0));
                note_title.add(cursor.getString(1));
                note_note.add(cursor.getString(2));
            }
        }
    }

}