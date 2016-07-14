package com.levenshtein.helper;

public class LevenshteinDistanceRecursively {

    private static int calculateDistance(String word1, int word1Length, String word2, int word2Length) {

        if (word1Length == 0) {
            return word2Length;
        }

        if (word2Length == 0) {
            return word1Length;
        }

        int cost = 1;
        if (word1.charAt(word1Length - 1) == word2.charAt(word2Length - 1)) {
            cost = 0;
        }

        return MathHelper.min(calculateDistance(word1, word1Length - 1, word2, word2Length) + 1,
                calculateDistance(word1, word1Length, word2, word2Length - 1) + 1,
                calculateDistance(word1, word1Length - 1, word2, word2Length - 1) + cost);
    }

    public static int calculateDistance(String word1, String word2) {
        final int word1Length = word1 != null ? word1.length() : 0;
        final int word2Length = word2 != null ? word2.length() : 0;

        return calculateDistance(word1, word1Length, word2, word2Length);
    }

}
