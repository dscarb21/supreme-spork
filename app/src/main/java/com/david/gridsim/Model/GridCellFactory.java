package com.david.gridsim.Model;

public class GridCellFactory {
    public GridCell makeCell(int val, int row, int col) {
        // Define broad ranges of types of grid cells
        if (val == 0) {
            return new GridCell(val, row, col);
        } else if (val >= 1000 && val < 1000000) {
            return new Plant(val, row, col);
        } else if (val >= 1000000 && val < 20000000) {
            return new GardenerItem(val, row, col);
        }
        return new GridCell(val, row, col);
    }
}
