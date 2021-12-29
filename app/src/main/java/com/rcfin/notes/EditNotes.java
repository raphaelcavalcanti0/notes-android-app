package com.rcfin.notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditNotes extends AppCompatActivity {

    ImageView actionBarLeft, actionBarRight;
    EditText title, note;
    TextView actionBarText;
    FloatingActionButton saveBtn;
    BancoDeDados db;
    String _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        actionBarLeft = findViewById(R.id.actionBarLeft);
        actionBarLeft.setVisibility(View.VISIBLE);

        actionBarRight = findViewById(R.id.actionBarRight);
        actionBarRight.setVisibility(View.VISIBLE);
        actionBarRight.setOnClickListener(v -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(EditNotes.this);
            alert.setTitle("Confirmar exclusão");
            alert.setMessage("Deseja excluir essa anotação?");
            alert.setPositiveButton("Sim", (dialog, which) -> deleteNote());
            alert.setNegativeButton("Não", null);
            alert.create();
            alert.show();
        });

        actionBarText = findViewById(R.id.actionBarText);
        actionBarText.setText(R.string.edit_notes);

        title = findViewById(R.id.editNotesTitle);
        note = findViewById(R.id.editNotesNote);

        if (getIntent().hasExtra("id")) {
            _id = getIntent().getStringExtra("id");
            title.setText(getIntent().getStringExtra("title"));
            note.setText(getIntent().getStringExtra("note"));
        }

        saveBtn = findViewById(R.id.edit_save_btn);

        saveBtn.setOnClickListener(v -> editNote());

        actionBarLeft.setOnClickListener(v -> finish());
    }

    private void editNote() {
        db = new BancoDeDados(EditNotes.this);
        EditText title = findViewById(R.id.editNotesTitle);
        EditText note = findViewById(R.id.editNotesNote);

        if (title.getText().toString().trim().isEmpty() && note.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Digite algo para salvar.", Toast.LENGTH_SHORT).show();
        } else {
            db.editNote(_id, title.getText().toString().trim(), note.getText().toString().trim());
            finish();
        }
    }

    private void deleteNote() {
        db = new BancoDeDados(EditNotes.this);
        db.deleteNote(_id);
        finish();
    }

}