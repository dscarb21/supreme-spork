package com.david.gridsim;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private SimGridView simGridView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        gridView = findViewById(R.id.gridView);
        simGridView = new SimGridView();

        fetchGridData();
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("textViewContent", textView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textView.setText(savedInstanceState.getString("textViewContent"));
    }

    private void fetchGridData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://stman1.cs.unh.edu:6191/games";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                this::handleResponse,
                error -> {
                    // Handle the error
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(getApplicationContext(), "Network timeout or no connection", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Unknown error occurred", Toast.LENGTH_LONG).show();
                    }
                    Log.e("Volley Error", "Error occurred", error);
                }
        );

        queue.add(jsonObjectRequest);
        Log.e("JSON", jsonObjectRequest.toString());

    }

    private void handleResponse(JSONObject response) {
        Log.d("JSON", "Response received: " + response.toString());
        try {
            JSONArray grid = response.getJSONArray("grid");
            simGridView.setUsingJSON(grid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeButtons() {
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(v -> Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show());
        button2.setOnClickListener(v -> Toast.makeText(this, "Right", Toast.LENGTH_SHORT).show());
    }
}
