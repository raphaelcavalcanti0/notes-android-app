package com.rcfin.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BancoDeDados extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "notes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "notes_title";
    private static final String COLUMN_TEXT = "notes_text";

    public BancoDeDados(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "
                + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_TEXT + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addNote(String title, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_TEXT, note);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Saved.", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;

        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    void editNote(String id, String title, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_TEXT, note);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Failed.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Saved.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteNote(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_NAME, "_id=?", new String[]{id});

        if (result == -1) {
            Toast.makeText(context, "Failed.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
