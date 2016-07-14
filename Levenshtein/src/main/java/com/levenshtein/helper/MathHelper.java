package com.levenshtein.helper;

public class MathHelper {

    public static int min(Integer... integers) {
        int min = Integer.MAX_VALUE;

        for (Integer integer : integers) {
            min = Math.min(integer, min);
        }

        return min;
    }

}
