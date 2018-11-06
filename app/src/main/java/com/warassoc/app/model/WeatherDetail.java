package com.warassoc.app.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2017 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * <p>
 * Created by bwarr on 8/9/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "dt",
        "temp",
        "pressure",
        "humidity",
        "weather",
        "speed",
        "deg",
        "clouds",
        "rain"
})
public class WeatherDetail {
    @JsonProperty("dt")
    private Integer dt;
    @JsonProperty("dt_txt")
    private String dateText;

    // ****
    @JsonProperty("main")
    private WeatherDetailMain detailMain;
    @JsonProperty("weather")
    private java.util.List<WeatherDetailWeather> detailWeatherList;
    @JsonProperty("clouds")
    private WeatherDetailClouds clouds;
    @JsonProperty("wind")
    private WeatherDetailWind wind;
    @JsonProperty("rain")
    private WeatherDetailRain rain;
    @JsonProperty("snow")
    private WeatherDetailSnow snow;

    /*
    @JsonProperty("temp")
    private Temp temp;
    @JsonProperty("pressure")
    private Double pressure;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("list")
    private java.util.List<Weather> weatherList = null;
    @JsonProperty("speed")
    private Double speed;
    @JsonProperty("deg")
    private Integer deg;
    @JsonProperty("clouds")
    private Integer clouds;
    @JsonProperty("rain")
    private Double rain;
    */
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    //@JsonProperty("dt")
    public Integer getDt() {
        return dt;
    }

    //@JsonProperty("dt")
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public String getDateText() {
        return dateText;
    }

    public void setDateText(String dateText) {
        this.dateText = dateText;
    }

    public WeatherDetailMain getDetailMain() {
        return detailMain;
    }

    public void setDetailMain(WeatherDetailMain detailMain) {
        this.detailMain = detailMain;
    }

    public List<WeatherDetailWeather> getDetailWeatherList() {
        return detailWeatherList;
    }

    public void setDetailWeatherList(List<WeatherDetailWeather> detailWeatherList) {
        this.detailWeatherList = detailWeatherList;
    }

    public WeatherDetailClouds getClouds() {
        return clouds;
    }

    public void setClouds(WeatherDetailClouds clouds) {
        this.clouds = clouds;
    }

    public WeatherDetailWind getWind() {
        return wind;
    }

    public void setWind(WeatherDetailWind wind) {
        this.wind = wind;
    }

    public WeatherDetailRain getRain() {
        return rain;
    }

    public void setRain(WeatherDetailRain rain) {
        this.rain = rain;
    }

    public WeatherDetailSnow getSnow() {
        return snow;
    }

    public void setSnow(WeatherDetailSnow snow) {
        this.snow = snow;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "WeatherDetail{" +
                "dt=" + dt +
                ", dateText='" + dateText + '\'' +
                ", detailMain=" + detailMain +
                ", detailWeatherList=" + detailWeatherList +
                ", clouds=" + clouds +
                ", wind=" + wind +
                ", rain=" + rain +
                ", snow=" + snow +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
