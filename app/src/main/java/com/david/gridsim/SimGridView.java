package com.david.gridsim;

import android.util.Log;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.david.gridsim.Model.GridCell;
import com.david.gridsim.Model.SimulationGrid;

import org.json.JSONArray;

public class SimGridView {

    private GridView gridView;
    private TextView textView;
    private GridAdapter gridAdapter;
    private SimulationGrid simulationGrid;

    public SimGridView() {
        simulationGrid = new SimulationGrid(16, 16); // Assuming a default 16x16 grid
    }

    public void attach(TextView tview, GridView gview) {
        this.textView = tview;
        this.gridView = gview;

        gridAdapter = new GridAdapter(gridView.getContext(), R.layout.grid_item, simulationGrid);
        gridView.setAdapter(gridAdapter);

        // Set up the click listener for the GridView
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            GridCell clickedCell = simulationGrid.getCell(position);
            String output = "Type: " + clickedCell.getCellType() + "\n" + clickedCell.getCellInfo();
            textView.setText(output);
        });
    }

    public void detach() {
        // Placeholder for EventBus or other cleanup tasks in the future
    }

    public SimulationGrid getSimulationGrid() {
        return this.simulationGrid;
    }


    public void setUsingJSON(JSONArray arr) {
        try {
            simulationGrid.setUsingJSON(arr);

            if (gridView != null) {
                gridView.invalidateViews(); // Refresh the GridView
            } else {
                Log.e("SimGridView", "gridView is null. Did you forget to call attach()?");
            }

            // Update the selected item information if needed
            if (gridView != null && gridView.getSelectedItemPosition() != AdapterView.INVALID_POSITION) {
                int selectedPosition = gridView.getSelectedItemPosition();
                GridCell selectedCell = simulationGrid.getCell(selectedPosition);
                String output = "Selected " + selectedCell.getCellType() + "\n" + selectedCell.getCellInfo();
                textView.setText(output);
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            Log.e("SimGridView", "Error processing JSON", e);
        }
    }


}
