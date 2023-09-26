package com.david.gridsim.Model;

import com.david.gridsim.R;

public class GridCell {
    protected int rawServerValue;
    protected int row;
    protected int col;

    public GridCell(int rawServerValue, int row, int col) {
        this.rawServerValue = rawServerValue;
        this.row = row;
        this.col = col;
    }

    public Integer getResourceID() {
        return R.drawable.blank; // Default: Empty cell
    }

    public String getCellType() {
        return "Empty Cell";
    }

    public String getCellInfo() {
        return "Row: " + row + ", Col: " + col;
    }
}
