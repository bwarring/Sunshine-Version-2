package com.warassoc.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Copyright 2017 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * <p>
 * Created by bwarr on 11/6/2018.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "3h"
})
public class WeatherDetailRain {
    @JsonProperty("3h")
    private Double last3Hours; //Rain volume for last 3 hours

    public Double getLast3Hours() {
        return last3Hours;
    }

    public void setLast3Hours(Double last3Hours) {
        this.last3Hours = last3Hours;
    }

    @Override
    public String toString() {
        return "WeatherDetailRain{" +
                "last3Hours=" + last3Hours +
                '}';
    }
}
