package com.levenshtein.helper;

public class LevenshteinDistanceWithDynamicProgramming {

    public static int calculateDistance(String string1, String string2) {
        if (string1 == null) {
            string1 = "";
        }

        if (string2 == null) {
            string2 = "";
        }

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

}