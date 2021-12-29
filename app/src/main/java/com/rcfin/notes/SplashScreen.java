package com.rcfin.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    private final Timer timer = new Timer();
    TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> gotoNotesActivity());
            }
        };

        timer.schedule(timerTask, 1000);
    }

    private void gotoNotesActivity() {
        Intent notesIntent = new Intent(getApplicationContext(), Notes.class);
        notesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(notesIntent);
        finish();
    }
}