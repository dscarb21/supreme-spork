package com.david.gridsim;

import org.json.JSONArray;

public class GridUpdateEvent {
    private JSONArray gridData;
    private String cellInfo;

    public GridUpdateEvent(JSONArray gridData) {
        this.gridData = gridData;
    }

    public JSONArray getGridData() {
        return gridData;
    }

    public String getCellInfo() {
        return cellInfo;
    }
}
