package com.warassoc.app.service;

/**
 * Copyright 2017 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * <p>
 * Created by bwarr on 8/7/2017.
 */
public interface OpenWeatherService {

    String retrieve(OpenWeatherServiceRequest request);
}
