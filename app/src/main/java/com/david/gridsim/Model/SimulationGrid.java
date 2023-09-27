package com.david.gridsim.Model;

import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

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
    private GridView gridView;
    private TextView textView;
    private GridAdapter gridAdapter;

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
            GridCell clickedCell = getCell(position);
            String output = clickedCell.getCellInfo();
            textView.setText(output);
        });

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

    public GridCell getCell(int row, int col) {
        int index = row * numCols + col;
        return getCell(index);
    }

    public int getNumRows() {
        return numRows;
    }

    public List<GridCell> getGridCells() {
        return gridCells;
    }


    public int getNumCols() {
        return numCols;
    }

    public void setCell(int index, GridCell cell) {
        if (index >= 0 && index < gridCells.size()) {
            gridCells.set(index, cell);
        }
    }

    public int size() {
        return gridCells.size();
    }

    public void setUsingJSON(JSONArray arr) throws JSONException {
        gridCells.clear();
        for (int i = 0; i < arr.length(); i++) {
            JSONArray row = arr.getJSONArray(i);
            for (int j = 0; j < row.length(); j++) {
                int value = row.getInt(j);
                GridCell cell = gridCellFactory.makeCell(value, i, j);
                gridCells.add(cell);
            }
        }
    }
}
