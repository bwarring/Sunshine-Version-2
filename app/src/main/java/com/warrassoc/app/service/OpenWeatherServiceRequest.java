package com.warrassoc.app.service;

import javax.inject.Inject;

/**
 * Copyright 2017 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * <p>
 * Created by bwarr on 8/7/2017.
 */

public class OpenWeatherServiceRequest {
    private String baseUrl = "http://api.openweathermap.org/data/2.5/";
    private String type = "forecast";
    private String frequency = "daily";
    private String locationZip = "66061";
    private String mode = "json";
    private String units = "metric";
    private String count = "7";


    public OpenWeatherServiceRequest() {
        super();
    }

    @Inject
    public OpenWeatherServiceRequest(String baseUrl, String type, String frequency, String locationZip, String mode, String units, String count) {
        this.baseUrl = baseUrl;
        this.type = type;
        this.frequency = frequency;
        this.locationZip = locationZip;
        this.mode = mode;
        this.units = units;
        this.count = count;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getLocationZip() {
        return locationZip;
    }

    public void setLocationZip(String locationZip) {
        this.locationZip = locationZip;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
