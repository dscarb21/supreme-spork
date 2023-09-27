package com.david.gridsim.Model;

import com.david.gridsim.R;

public class GridCell {
    protected int rawServerValue;
    protected int row;
    protected int col;
    protected int resourceID;
    protected String cellType;

    public GridCell(int rawServerValue, int row, int col) {
        this.rawServerValue = rawServerValue;
        this.row = row;
        this.col = col;
        this.resourceID = R.drawable.blank;
        this.cellType = "Blank";
    }

    public Integer getResourceID() {
        return resourceID;
    }

    public String getCellType() {
        if (rawServerValue < 0 || rawServerValue >= 20000000) {
            throw new IllegalArgumentException("Invalid value: " + rawServerValue);
        } else if (rawServerValue > 4000 && rawServerValue < 1000000) {
            return "Reserved";
        } else {
            return cellType;
        }
    }

    public String getCellInfo() {
        return "Selected " + getCellType() + "\nLocation: (" + col + ", " + row +")";
    }
}
