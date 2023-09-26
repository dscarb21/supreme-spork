package com.david.gridsim.Model;

import com.david.gridsim.R;

public class Plant extends GridCell {
    public Plant(int rawServerValue, int row, int col) {
        super(rawServerValue, row, col);
    }

    @Override
    public Integer getResourceID() {
        if (rawServerValue == 1000) {
            return R.drawable.tree;
        } else if (rawServerValue > 1000 && rawServerValue < 2000) {
            return R.drawable.bushes;
        } else if (rawServerValue == 2002 || rawServerValue == 2003 || rawServerValue == 3000) {
            // You can further differentiate between clover, mushroom, sunflower based on the value
            return R.drawable.blank; // Default plant image
        }
        return R.drawable.blank; // Default: Empty cell
    }

    @Override
    public String getCellType() {
        if (rawServerValue == 1000) {
            return "Tree";
        } else if (rawServerValue > 1000 && rawServerValue < 2000) {
            return "Bushes";
        } else if (rawServerValue == 2002 || rawServerValue == 2003 || rawServerValue == 3000) {
            return "Plant";
        }
        return "Empty Cell";
    }
}
