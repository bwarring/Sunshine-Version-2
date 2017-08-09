package com.warassoc.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.sunshine.app.R;
import com.warassoc.app.model.WeatherDetail;
import com.warassoc.app.util.DateUtility;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Copyright 2017 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * <p>
 * Created by bwarr on 8/9/2017.
 */

public class WeatherDetailListArrayAdapter extends ArrayAdapter<WeatherDetail> {


    public WeatherDetailListArrayAdapter(Context context, List<WeatherDetail> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        WeatherDetail weatherDetail = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_forecast, parent, false);
        }

        // Lookup view for data population
        TextView tvDay = (TextView) convertView.findViewById(R.id.tvDay);
        TextView tvWeather = (TextView) convertView.findViewById(R.id.tvWeather);
        TextView tvTemp = (TextView) convertView.findViewById(R.id.tvTemp);
        // Populate the data into the template view using the data object
        //for (Weather weather:weatherDetail.getWeather()){
        //    weather.getDescription();
        //}
        DateTime datetime = DateUtility.convertLongMillisecondsDateToTimezoneDate(weatherDetail.getDt());
        String dayStr = datetime.dayOfWeek().getAsText();
        tvDay.setText(dayStr);
        tvWeather.setText(weatherDetail.getWeather().get(0).getMain());
        Double tempMin = weatherDetail.getTemp().getMin();
        Double tempMax = weatherDetail.getTemp().getMax();
        String.valueOf(tempMin);
        tvTemp.setText("Low: " + String.valueOf(tempMin) + " High: " + String.valueOf(tempMax));
        // Return the completed view to render on screen
        return convertView;
    }
}
