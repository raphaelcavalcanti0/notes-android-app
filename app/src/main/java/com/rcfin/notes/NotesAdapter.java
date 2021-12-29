package com.rcfin.notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private Context context;
    private ArrayList note_id, note_title, note_note;

    NotesAdapter(Context context,
                 ArrayList note_id,
                 ArrayList note_title,
                 ArrayList note_note) {

        this.context = context;
        this.note_id = note_id;
        this.note_title = note_title;
        this.note_note = note_note;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.notes_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.note_title_txt.setText(String.valueOf(note_title.get(position)));
        holder.note_note_txt.setText(String.valueOf(note_note.get(position)));
        holder.mainLayout.setOnClickListener(v -> {
            Intent editIntent = new Intent(context, EditNotes.class);
            editIntent.putExtra("id", String.valueOf(note_id.get(position)));
            editIntent.putExtra("title", String.valueOf(note_title.get(position)));
            editIntent.putExtra("note", String.valueOf(note_note.get(position)));
            context.startActivity(editIntent);
        });
    }

    @Override
    public int getItemCount() {
        return note_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView note_title_txt, note_note_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            note_title_txt = itemView.findViewById(R.id.note_title);
            note_note_txt = itemView.findViewById(R.id.note_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }

}

