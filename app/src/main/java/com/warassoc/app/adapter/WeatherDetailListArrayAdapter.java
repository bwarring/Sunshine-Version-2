package com.warassoc.app.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.sunshine.activity.R;
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
    public View getView(int position, View weatherView, ViewGroup parent) {
        // Get the data item for this position
        WeatherDetail weatherDetail = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (weatherView == null) {
            weatherView = LayoutInflater.from(getContext()).inflate(R.layout.item_forecast, parent, false);
        }
        // alternate colors in listview
        // https://stackoverflow.com/questions/18917214/change-background-colour-of-current-listview-item-in-adapter-getview-method
        /*
        if (position % 2 == 0) {
            view.setBackgroundResource(R.drawable.artists_list_backgroundcolor);
        } else {
            view.setBackgroundResource(R.drawable.artists_list_background_alternate);
        }
         */

        // Lookup view for data population
        TextView tvDay = (TextView) weatherView.findViewById(R.id.tvDay);
        tvDay.setTypeface(null, Typeface.BOLD);
        TextView tvWeather = (TextView) weatherView.findViewById(R.id.tvWeather);
        tvWeather.setTypeface(null, Typeface.BOLD);
        TextView tvTemp = (TextView) weatherView.findViewById(R.id.tvTemp);
        tvTemp.setTypeface(null, Typeface.BOLD);
        // Populate the data into the template view using the data object
        //for (Weather weather:weatherDetail.getWeather()){
        //    weather.getDescription();
        //}
        DateTime datetime = DateUtility.convertLongMillisecondsDateToTimezoneDate(weatherDetail.getDt());
        String dayStr = datetime.dayOfWeek().getAsText();
        tvDay.setText(dayStr);
        tvWeather.setText(weatherDetail.getWeather().get(0).getMain());
        if (tvWeather.getText().length() > 0) {
            String wthr = tvWeather.getText().toString().toLowerCase();
            if (wthr.contains("rain")) {
                weatherView.setBackgroundResource(R.drawable.weather_list_rain_color);
            } else {
                if (wthr.contains("clouds")) {
                    weatherView.setBackgroundResource(R.drawable.weather_list_clouds_color);
                } else {
                    weatherView.setBackgroundResource(R.drawable.weather_list_sunny_color);
                }
            }
        }
        Double tempMin = weatherDetail.getTemp().getMin();
        Double tempMax = weatherDetail.getTemp().getMax();
        String.valueOf(tempMin);
        tvTemp.setText("Low: " + String.valueOf(tempMin) + " High: " + String.valueOf(tempMax));
        // Return the completed view to render on screen
        return weatherView;
    }
}
