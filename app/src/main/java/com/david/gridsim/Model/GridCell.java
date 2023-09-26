package com.david.gridsim.Model;

public class GridCell {
    private int serverValue;
    private int row;
    private int col;

    public GridCell(int serverValue, int row, int col) {
        this.serverValue = serverValue;
        this.row = row;
        this.col = col;
    }

    public Integer getResourceID() {
        // Default: Empty (You can return a default image resource ID here)
        return null;
    }

    public String getCellType() {
        return "Default GridCell";
    }

    public String getCellInfo() {
        return "Row: " + row + ", Col: " + col;
    }

    public int getServerValue() {
        return serverValue;
    }

    public void setServerValue(int serverValue) {
        this.serverValue = serverValue;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
