package com.david.gridsim.Model;

import android.util.Log;

import com.david.gridsim.R;

public class GardenerItem extends GridCell {
    private int gardenerId;

    public GardenerItem(int rawServerValue, int row, int col) {
        super(rawServerValue, row, col);

        if (rawServerValue >= 1000000 && rawServerValue < 2000000) {
            this.resourceID = R.drawable.gardender_icon;
            this.cellType = "Gardener";
        } else if (rawServerValue >= 2000000 && rawServerValue < 3000000) {
            this.resourceID = R.drawable.shovel_icon;
            this.cellType = "Shovel";
        } else if (rawServerValue >= 10000000 && rawServerValue < 20000000) {
            this.resourceID = R.drawable.golfcart_icon;
            this.cellType = "Cart";
        } else {
            this.resourceID = R.drawable.blank;
            this.cellType = "Blank";
        }

        this.gardenerId = calculateGardenerID();
    }

    @Override
    public String getCellInfo() {
        return "Selected " + getCellType() + "\nLocation: (" + col + ", " + row +")" + "\nGardener ID: " + gardenerId;
    }

    private int calculateGardenerID() {
        // For 8-digit numbers (Cart)
        if (rawServerValue >= 10000000 && rawServerValue < 20000000) {
            return (rawServerValue / 10000) % 1000;
        }
        return (rawServerValue / 1000) % 1000;
    }
}
