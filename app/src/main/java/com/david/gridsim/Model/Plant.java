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
        } else if (rawServerValue == 2002) {
            return R.drawable.clover;
        } else if (rawServerValue == 2003) {
            return R.drawable.mushroom;
        } else if (rawServerValue == 3000) {
            return R.drawable.sunflower;
        }
        return R.drawable.blank;
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
