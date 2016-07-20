package com.levenshtein.helper;

public class StringHelper {

    public static String removeSpaces(String string) {
        if (string == null) {
            return null;
        }

        return string.trim().replaceAll(" +", " ");
    }

}
