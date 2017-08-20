package com.warassoc.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.sunshine.activity.R;
import com.warassoc.app.model.Forecast;

import java.util.List;

/**
 * Copyright 2017 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * <p>
 * Created by bwarr on 5/16/2017.
 */

public class ForecastListArrayAdapter extends ArrayAdapter<Forecast> {

    public ForecastListArrayAdapter(Context context, List<Forecast> list){
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Forecast forecast = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_forecast, parent, false);
        }

        // Lookup view for data population
        TextView tvDay = (TextView) convertView.findViewById(R.id.tvDay);
        TextView tvWeather = (TextView) convertView.findViewById(R.id.tvWeather);
        TextView tvTemp = (TextView) convertView.findViewById(R.id.tvTemp);
        // Populate the data into the template view using the data object
        tvDay.setText(forecast.getDay());
        tvWeather.setText(forecast.getWeather());
        tvTemp.setText(forecast.getHigh() + "/" + forecast.getLow());
        // Return the completed view to render on screen
        return convertView;
    }
}
