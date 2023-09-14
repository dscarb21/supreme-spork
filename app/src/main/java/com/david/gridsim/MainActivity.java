package com.david.gridsim;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Integer> items;
    private GridView gridView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>(256); // 16 x 16 = 256 cells
        gridView = findViewById(R.id.gridView);
        textView = findViewById(R.id.textView);

        fetchGridData();
        initializeButtons();

        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getString("textViewContent"));
        }
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

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this::handleResponse, this::handleError);

        queue.add(jsonObjectRequest);
    }

    private void handleResponse(JSONObject response) {
        try {
            populateGrid(response);
            setGridAdapter();
            setGridClickListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleError(VolleyError error) {
        Log.e("Volley Error", "Error occurred", error);
        Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void populateGrid(JSONObject response) throws Exception {
        JSONArray grid = response.getJSONArray("grid");
        for (int i = 0; i < grid.length(); i++) {
            JSONArray row = grid.getJSONArray(i);
            for (int j = 0; j < row.length(); j++) {
                items.add(row.getInt(j));
            }
        }
    }

    private void setGridAdapter() {
        GridAdapter adapter = new GridAdapter(this, R.layout.grid_item, items);
        gridView.setAdapter(adapter);
    }

    private void setGridClickListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int clickedValue = items.get(position);
                String output = "Value: " + clickedValue + "\nPosition: " + position;
                if (clickedValue != 0) {
                    output = "Non-Zero " + output;
                }
                textView.setText(output);
                Log.d("gridView", Integer.toString(position));
            }
        });

    }

    private void initializeButtons() {
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(v -> Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show());
        button2.setOnClickListener(v -> Toast.makeText(this, "Right", Toast.LENGTH_SHORT).show());
    }
}
