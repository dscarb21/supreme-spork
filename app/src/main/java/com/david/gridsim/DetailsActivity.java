package com.david.gridsim;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView detailsTextView = findViewById(R.id.detailsTextView);
        String cellInfo = getIntent().getStringExtra("cellInfo");
        detailsTextView.setText(cellInfo);
    }

}