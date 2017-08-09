package com.warassoc.app.service;

/**
 * Copyright 2017 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * <p>
 * Created by bwarr on 8/9/2017.
 */

public interface JacksonService {

    /**
     * @param object
     * @return
     */
    String toJson(Object object);

    /**
     * @param json
     * @param clazz
     * @return
     */
    <T> T toObject(String json, Class<T> clazz);

}
