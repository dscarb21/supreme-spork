package com.david.gridsim.Model;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class SimulationGrid {
    private List<GridCell> gridCells;
    private int numRows;
    private int numCols;

    public SimulationGrid(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.gridCells = new ArrayList<>(numRows * numCols);
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

    public int getNumCols() {
        return numCols;
    }

    public void setCell(int index, GridCell cell) {
        if (index >= 0 && index < gridCells.size()) {
            gridCells.set(index, cell);
        }
    }

    public void setCell(int row, int col, GridCell cell) {
        int index = row * numCols + col;
        setCell(index, cell);
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
                GridCell cell = new GridCell(value, i, j);
                gridCells.add(cell);
            }
        }
    }
}
