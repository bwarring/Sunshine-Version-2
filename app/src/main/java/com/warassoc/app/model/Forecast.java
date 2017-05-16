package com.warassoc.app.model;

import com.warassoc.app.model.util.RandomUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bwarr on 5/15/2017.
 */

public class Forecast {
    private String day;
    private String weather;
    private int high;
    private int low;

    public Forecast(){
        super();
    }

    public Forecast(String day, String weather, int high, int low){
        super();
        this.day=day;
        this.weather=weather;
        this.high=high;
        this.low=low;
    }

    public static List<Forecast> list(){

        String[] days = new String[]{"Today", "Tomorrow", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday"};
        String[] weather = new String[]{"Sunny", "Sunny", "Rainy", "Rainy", "Cloudy", "Cloudy", "Sunny", "Sunny", "Sunny"};
        List<Forecast> forecastList = new ArrayList<Forecast>();
        int cnt = 9;
        Forecast forecast = null;
        for (int i=0; i<cnt; i++) {
            forecastList.add(new Forecast(days[i], weather[i], RandomUtility.random(75, 85), RandomUtility.random(60, 70)));
        }
        return forecastList;
    }

    public String toString(){
        return (getDay() + " - " + getWeather() + " - " + getHigh() + "/" + getLow());
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }
}
