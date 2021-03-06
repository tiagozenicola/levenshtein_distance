package com.tiagozenicola.levenshtein.helper;

import org.apache.commons.lang3.StringUtils;

public class LevenshteinDistanceWithDynamicProgramming {

    public static int calculateDistance(String string1, String string2) {
        string1 = parse(string1);
        string2 = parse(string2);

        final int[][] matrix = new int[string1.length() + 1][string2.length() + 1];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = i;
        }

        for (int j = 0; j < matrix[0].length; j++) {
            matrix[0][j] = j;
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                int cost = 1;

                if (string1.charAt(i - 1) == string2.charAt(j - 1)) {
                    cost = 0;
                }

                matrix[i][j] = MathHelper.min(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1, matrix[i - 1][j - 1] + cost);
            }
        }

        return matrix[string1.length()][string2.length()];
    }

    private static String parse(String string) {
        if (string == null) {
            return "";
        }

        string = StringHelper.removeSpaces(string);

        return StringUtils.stripAccents(string.toLowerCase());
    }

}