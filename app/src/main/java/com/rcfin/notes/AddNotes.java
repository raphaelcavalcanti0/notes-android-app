package com.rcfin.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddNotes extends AppCompatActivity {

    ImageView actionBarLeft;
    ImageView actionBarRight;
    TextView actionBarText;
    FloatingActionButton saveBtn;
    BancoDeDados db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        actionBarLeft = findViewById(R.id.actionBarLeft);
        actionBarLeft.setVisibility(View.VISIBLE);

        actionBarRight = findViewById(R.id.actionBarRight);
        actionBarRight.setVisibility(View.GONE);

        actionBarText = findViewById(R.id.actionBarText);
        actionBarText.setText(R.string.add_notes);

        saveBtn = findViewById(R.id.save_btn);

        saveBtn.setOnClickListener(v -> newNote());

        actionBarLeft.setOnClickListener(v -> finish());
    }

    private void newNote() {
        db = new BancoDeDados(AddNotes.this);
        EditText title = findViewById(R.id.addNotesTitle);
        EditText note = findViewById(R.id.addNotesNote);

        if (title.getText().toString().trim().isEmpty() && note.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Digite algo para salvar.", Toast.LENGTH_SHORT).show();
        } else {
            db.addNote(title.getText().toString().trim(), note.getText().toString().trim());
            finish();
        }
    }
}