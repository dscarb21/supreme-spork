package com.david.gridsim.Model;

import android.util.Log;

import com.david.gridsim.R;

public class GardenerItem extends GridCell {
    public GardenerItem(int rawServerValue, int row, int col) {
        super(rawServerValue, row, col);
    }

    @Override
    public Integer getResourceID() {
        if (rawServerValue >= 1000000 && rawServerValue < 2000000) {
            return R.drawable.gardender_icon;
        } else if (rawServerValue >= 2000000 && rawServerValue < 3000000) {
            return R.drawable.shovel_icon;
        } else if (rawServerValue >= 10000000 && rawServerValue < 20000000) {
            return R.drawable.golfcart_icon;
        }
        return R.drawable.blank; // Default: Empty cell
    }

    @Override
    public String getCellType() {
        if (rawServerValue >= 1000000 && rawServerValue < 2000000) {
            return "Gardener";
        } else if (rawServerValue >= 2000000 && rawServerValue < 3000000) {
            return "Shovel";
        } else if (rawServerValue >= 10000000 && rawServerValue < 20000000) {
            return "Cart";
        }
        return "Empty Cell";
    }

    @Override
    public String getCellInfo() {
        int gardenerId = calculateGardenerID();
        return "Selected " + getCellType() + "\nLocation: (" + col + ", " + row +")" + "\nGardener ID: " + gardenerId;
    }

    private int calculateGardenerID() {
        Log.e("ServerVal", Integer.toString(rawServerValue));
        return (rawServerValue / 1000) % 1000; // Extracting the GID from the raw value
    }

}
