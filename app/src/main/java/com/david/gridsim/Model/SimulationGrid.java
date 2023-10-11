package com.david.gridsim.Model;

import android.content.Intent;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import com.david.gridsim.DetailsActivity;
import com.david.gridsim.GridAdapter;
import com.david.gridsim.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class SimulationGrid {
    private List<GridCell> gridCells;
    private int numRows;
    private int numCols;
    private GridCellFactory gridCellFactory = new GridCellFactory();
    private int selectedCellIndex = -1; // -1 means no cell is selected initially

    private GridView gridView;
    private TextView textView;
    private GridAdapter gridAdapter;
    public boolean isPaused = false;

    GridCell selectedCell = null;

    public SimulationGrid(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.gridCells = new ArrayList<>(numRows * numCols);
    }

    public void attach(TextView tview, GridView gview) {
        this.textView = tview;
        this.gridView = gview;
        gridAdapter = new GridAdapter(gridView.getContext(), R.layout.grid_item, this);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            selectedCellIndex = position;
            GridCell clickedCell = getCell(position);
            selectedCell = clickedCell;
            String output = clickedCell.getCellInfo();
            textView.setText(output);
        });
    }

    public GridCell getSelectedCell() {
        return selectedCell;
    }

    public void showDetailsForSelected() {
        if (selectedCell != null) {
            Intent intent = new Intent(gridView.getContext(), DetailsActivity.class);
            intent.putExtra("cellInfo", selectedCell.getCellInfo());
            gridView.getContext().startActivity(intent);
        }
    }

    public void updateTextView(String info) {
        if (textView != null) {
            if (selectedCellIndex != -1) {
                GridCell selectedCell = getCell(selectedCellIndex);
                String cellInfo = selectedCell.getCellInfo();
                // Update the TextView with the new cellInfo
                textView.setText(cellInfo);
            }
        }
    }


    public void detach() {
        // Placeholder for any cleanup tasks in the future
    }

    public void invalidateViews() {
        if (gridView != null) {
            gridView.invalidateViews();
        } else {
            Log.e("SimulationGrid", "gridView NULL");
        }
    }

    public GridCell getCell(int index) {
        if (index >= 0 && index < gridCells.size()) {
            return gridCells.get(index);
        }
        return null;
    }

    public List<GridCell> getGridCells() {
        return gridCells;
    }


    public int size() {
        return gridCells.size();
    }

    public void setUsingJSON(JSONArray arr) throws JSONException {
        if (!isPaused) {
            gridCells.clear();
        }
        for (int i = 0; i < arr.length(); i++) {
            JSONArray row = arr.getJSONArray(i);
            for (int j = 0; j < row.length(); j++) {
                int value = row.getInt(j);
                GridCell cell = gridCellFactory.makeCell(value, i, j);
                if (!isPaused) {
                    gridCells.add(cell);
                }
            }
        }
    }
}
