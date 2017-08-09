package com.warassoc.app.util;

/**
 * Created by bwarr on 5/15/2017.
 */

public class RandomUtility {

    static public int random(int min, int max){
        java.util.Random random = new java.util.Random();
        return random.nextInt(max - min + 1) + min;
    }
}
