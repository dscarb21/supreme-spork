package com.david.gridsim;

import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import com.david.gridsim.Model.SimulationGrid;

import org.json.JSONArray;

public class SimGridView {
    private SimulationGrid simulationGrid;

    public SimGridView() {
        simulationGrid = new SimulationGrid(16, 16); // Assuming a default 16x16 grid
    }

    public void attach(TextView tview, GridView gview) {
        simulationGrid.attach(tview, gview);
    }

    public void detach() {
        // Placeholder for EventBus or other cleanup tasks in the future
    }

    public void setUsingJSON(JSONArray arr) {
        try {
            simulationGrid.setUsingJSON(arr);
            simulationGrid.invalidateViews();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            Log.e("SimGridView", "Error processing JSON", e);
        }
    }
}
