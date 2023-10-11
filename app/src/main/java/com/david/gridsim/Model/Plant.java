package com.david.gridsim.Model;

import com.david.gridsim.R;

import java.io.Serializable;

public class Plant extends GridCell  implements Serializable {
    public Plant(int rawServerValue, int row, int col) {
        super(rawServerValue, row, col);

        if (rawServerValue == 1000) {
            this.resourceID = R.drawable.tree;
            this.cellType = "Tree";
        } else if (rawServerValue > 1000 && rawServerValue < 2000) {
            this.resourceID = R.drawable.bushes;
            this.cellType = "Bushes";
        } else if (rawServerValue == 2002) {
            this.resourceID = R.drawable.clover;
            this.cellType = "Clover";
        } else if (rawServerValue == 2003) {
            this.resourceID = R.drawable.mushroom;
            this.cellType = "Mushroom";
        } else if (rawServerValue == 3000) {
            this.resourceID = R.drawable.sunflower;
            this.cellType = "Sunflower";
        } else {
            this.resourceID = R.drawable.blank;
            this.cellType = "Blank";
        }
    }
}
