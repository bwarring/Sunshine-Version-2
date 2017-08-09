package com.warassoc.app.type;

/**
 * Copyright 2017 Warring Associates LLC
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * <p>
 * Created by bwarr on 8/9/2017.
 */

public enum TimezoneType {
    /**
     * AMERICA_EASTERN [id: America/New_York]
     */
    AMERICA_EASTERN("America/New_York"),

    /**
     * AMERICA_CENTRAL [id: America/Chicago]
     */
    AMERICA_CENTRAL("America/Chicago"),

    /**
     * AMERICA_MOUNTAIN [id: America/Denver]
     */
    AMERICA_MOUNTAIN("America/Denver"),

    /**
     * AMERICA_WESTERN [id: America/Los_Angeles]
     */
    AMERICA_WESTERN("America/Los_Angeles");

    private String id;

    /**
     * @param id
     */
    private TimezoneType(String id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
