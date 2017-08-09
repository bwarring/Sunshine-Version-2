package com.warrassoc.app.service;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.inject.Inject;

/**
 * Copyright 2017 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * <p>
 * Created by bwarr on 8/8/2017.
 */
public class OpenWeatherServiceImpl extends AsyncTask<Object, Object, String> implements OpenWeatherService {

    private String forecastJsonStr;

    public OpenWeatherServiceImpl() {
        super();
    }

    @Inject
    public OpenWeatherServiceImpl(OpenWeatherServiceRequest request) {
        super();
    }

    @Override
    protected String doInBackground(Object... params) {

        // these two need to be declared outside the try/catch block
        // so they can be closed in the finally block
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        // Will contain the raw JSON response as a string.
        //String forecastJsonStr = null;
        try {
            // Construct URL for the OpenWeatherMap query
            // Possible parameters are available at OWM's forecast API page at
            // http://openweathermap.com/API#forcast

            //APPID ="2be6bb1f8190f9079a8d5270be7417c0";
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=66061&mode=json&units=metric&cnt=7&APPID=2be6bb1f8190f9079a8d5270be7417c0");

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // call service
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                // Nothing to do.
                return forecastJsonStr;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                forecastJsonStr = null;
            }
            forecastJsonStr = buffer.toString();
            //} catch (IOException e) {
            //    Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
            //    forecastJsonStr = null;
        } catch (Exception e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
            forecastJsonStr = null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }

            //return rootView;
        }
        return forecastJsonStr;
    }

    @Override
    protected void onPostExecute(String forecastJsonStr) {
        //((TextView) findViewById(R.id.textView2)).setText("");
        System.out.println(forecastJsonStr);
    }

    @Override
    public String retrieve(OpenWeatherServiceRequest request) {
        return null;
    }

    public String getForecastJsonStr() {
        return forecastJsonStr;
    }

    public void setForecastJsonStr(String forecastJsonStr) {
        this.forecastJsonStr = forecastJsonStr;
    }
}
