package com.david.gridsim;

import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import com.david.gridsim.Model.GardenerItem;
import com.david.gridsim.Model.GridCell;
import com.david.gridsim.Model.SimulationGrid;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

public class SimGridView {
    private SimulationGrid simulationGrid;
    private EventBus eventBus;

    public SimGridView() {
        this(EventBus.getDefault());
    }

    public SimGridView(EventBus eventBus) {
        this.eventBus = eventBus;
        simulationGrid = new SimulationGrid(16, 16); // Assuming a default 16x16 grid
    }

    public void attach(TextView tview, GridView gview) {
        simulationGrid.attach(tview, gview);
        eventBus.register(this); // Register to EventBus
    }

    public void detach() {
        simulationGrid.detach();
        eventBus.unregister(this); // Unregister from EventBus
    }
    public boolean isPaused() {
        return simulationGrid.isPaused;
    }

    public void pauseUpdates() {
        simulationGrid.isPaused = true;
    }

    public void resumeUpdates() {
        simulationGrid.isPaused = false;
    }
    public GridCell getSelectedCell() {
        return simulationGrid.getSelectedCell();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataReceived(GridUpdateEvent event) {
        setUsingJSON(event.getGridData());
        updateTextView(event.getCellInfo());
    }

    private void updateTextView(String info) {
        simulationGrid.updateTextView(info);
    }

    public void showDetailsForSelected() {
        simulationGrid.showDetailsForSelected();
    }


    public void setUsingJSON(JSONArray arr) {
        try {
            simulationGrid.setUsingJSON(arr);
            simulationGrid.invalidateViews();
        } catch (Exception e) {
            // Log the exception for debugging purposes
            Log.e("SimGridView", "Error processing JSON", e);
        }
    }
}
