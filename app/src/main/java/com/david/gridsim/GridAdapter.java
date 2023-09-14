package com.david.gridsim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

public class GridAdapter extends ArrayAdapter<Integer> {
    public GridAdapter(Context context, int resource, List<Integer> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.grid_item_image);
        int value = getItem(position);

        if (value == 0) {
            imageView.setImageResource(R.drawable.image1);
        } else {
            imageView.setImageResource(R.drawable.image2);
        }

        return convertView;
    }
}

