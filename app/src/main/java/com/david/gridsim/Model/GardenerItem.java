package com.david.gridsim.Model;

import com.david.gridsim.R;

import java.io.Serializable;
import java.util.LinkedList;

public class GardenerItem extends GridCell implements Serializable{
    private int gardenerId;
    private LinkedList<String> movementHistory = new LinkedList<>();
    private int lastRow = -1;  // Initialize to invalid values
    private int lastCol = -1;


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


    public void updateLocation(int newRow, int newCol) {
        // Only add if coords are different from most recent entry
        if (newRow != lastRow || newCol != lastCol) {
            java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
            String historyEntry = "(" + newRow + ", " + newCol + ") " + "[" + timestamp + "]";
            movementHistory.add(historyEntry);

            lastRow = newRow;
            lastCol = newCol;
        }
    }

    @Override
    public String getCellInfo() {
        StringBuilder info = new StringBuilder("Selected " + getCellType() + "\nLocation: (" + col + ", " + row +")" + "\nGardener ID: " + gardenerId);
        for (String entry : movementHistory) {
            info.append("\n").append(entry);
        }
        return info.toString();
    }


    private int calculateGardenerID() {
        // For 8-digit numbers (Cart)
        if (rawServerValue >= 10000000 && rawServerValue < 20000000) {
            return (rawServerValue / 10000) % 1000;
        }
        return (rawServerValue / 1000) % 1000;
    }
}
