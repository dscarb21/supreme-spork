package com.david.gridsim.Model;

import com.david.gridsim.R;

public class Plant extends GridCell {
    public Plant(int rawServerValue, int row, int col) {
        super(rawServerValue, row, col);
    }
    public Integer getResourceID() {
        // Determine which plant image to use based on rawServerValue
        int val = getServerValue();
        if (val == 1000) {
            return R.drawable.tree;
        } else if (val >= 1000 && val <= 2000) {
            return R.drawable.bushes;
        } else if (val == 2002 || val == 2003 || val == 3000) {// Replace with your actual resource ID for bushes
            return R.drawable.clover; // Replace with your actual resource ID for clover
        } else {
            return R.drawable.blank; // Replace with your actual default resource ID
        }
    }

    @Override
    public String getCellType() {
        return "Plant";
    }

    @Override
    public String getCellInfo() {
        return "Plant at Row: " + getRow() + ", Col: " + getCol();
    }
}