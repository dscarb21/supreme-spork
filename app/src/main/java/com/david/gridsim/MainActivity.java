package com.david.gridsim;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private SimGridView simGridView;
    private TextView textView;
    private Poller poller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        gridView = findViewById(R.id.gridView);
        simGridView = new SimGridView();

        poller = Poller.getInstance();
        poller.startPolling(this);

        initializeButtons();

        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getString("textViewContent"));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        simGridView.attach(textView, gridView);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("textViewContent", textView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textView.setText(savedInstanceState.getString("textViewContent"));
    }

    private void initializeButtons() {
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(v -> simGridView.showDetailsForSelected());
        button2.setOnClickListener(v -> {
            if (simGridView.isPaused()) {
                simGridView.resumeUpdates();
                Toast.makeText(this, "Updates Resumed", Toast.LENGTH_SHORT).show();
            } else {
                simGridView.pauseUpdates();
                Toast.makeText(this, "Updates Paused", Toast.LENGTH_SHORT).show();
            }
        });
    }

}