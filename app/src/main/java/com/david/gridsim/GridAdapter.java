package com.david.gridsim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.david.gridsim.Model.SimulationGrid;
import com.david.gridsim.Model.GridCell;

public class GridAdapter extends ArrayAdapter<GridCell> {
    private SimulationGrid simulationGrid;

    public GridAdapter(Context context, int resource, SimulationGrid simulationGrid) {
        super(context, resource, simulationGrid.getGridCells());
        this.simulationGrid = simulationGrid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.grid_item_image);
        GridCell cell = getItem(position);
        imageView.setImageResource(cell.getResourceID());

        return convertView;
    }
}
