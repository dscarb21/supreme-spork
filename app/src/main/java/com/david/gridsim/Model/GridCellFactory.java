package com.david.gridsim.Model;
import java.util.HashMap;


public class GridCellFactory {
    private static final GridCellFactory INSTANCE = new GridCellFactory();
    private HashMap<Integer, GardenerItem> gardenerItems = new HashMap<>();

    public GridCellFactory() {}

    public GridCell makeCell(int val, int row, int col) {
        // Define broad ranges of types of grid cells
        if (val >= 1000000 && val < 20000000) {
            GardenerItem existingItem = gardenerItems.get(val);
            if (existingItem != null) {
                existingItem.updateLocation(row, col);
                return existingItem;
            } else {
                GardenerItem newItem = new GardenerItem(val, row, col);
                gardenerItems.put(val, newItem);
                return newItem;
            }
        } else if (val == 0) {
            return new GridCell(val, row, col);
        } else if (val >= 1000 && val < 1000000) {
            return new Plant(val, row, col);
        }
        return new GridCell(val, row, col);
    }
}
