package com.levenshtein.helper;

import org.junit.Assert;
import org.junit.Test;

public class LevenshteinDistanceWithDynamicProgrammingTest {

    @Test
    public void testingVersion1() {
        Assert.assertEquals(3, LevenshteinDistanceWithDynamicProgramming.calculateDistance("kitten", "sitting"));
        Assert.assertEquals(4, LevenshteinDistanceWithDynamicProgramming.calculateDistance("banana", "abacate"));
        Assert.assertEquals(5, LevenshteinDistanceWithDynamicProgramming.calculateDistance("cachorro", "cavalo"));
        Assert.assertEquals(7, LevenshteinDistanceWithDynamicProgramming.calculateDistance("ovo", "macarrão"));
        Assert.assertEquals(8, LevenshteinDistanceWithDynamicProgramming.calculateDistance("sorvete", "advogado"));
        Assert.assertEquals(5, LevenshteinDistanceWithDynamicProgramming.calculateDistance("tom", "jerry"));
        Assert.assertEquals(2, LevenshteinDistanceWithDynamicProgramming.calculateDistance("book", "back"));
        Assert.assertEquals(3, LevenshteinDistanceWithDynamicProgramming.calculateDistance("kitten", "sitting"));
        Assert.assertEquals(17, LevenshteinDistanceWithDynamicProgramming.calculateDistance("caminhao", "eu estou de férias"));
    }

    @Test
    public void testingNullStrings() {
        Assert.assertEquals(0, LevenshteinDistanceWithDynamicProgramming.calculateDistance(null, null));
        Assert.assertEquals(7, LevenshteinDistanceWithDynamicProgramming.calculateDistance(null, "abacate"));
        Assert.assertEquals(8, LevenshteinDistanceWithDynamicProgramming.calculateDistance("cachorro", null));
    }

    @Test
    public void testingEmptyStrings() {
        Assert.assertEquals(0, LevenshteinDistanceWithDynamicProgramming.calculateDistance("", ""));
        Assert.assertEquals(7, LevenshteinDistanceWithDynamicProgramming.calculateDistance("", "abacate"));
        Assert.assertEquals(8, LevenshteinDistanceWithDynamicProgramming.calculateDistance("cachorro", ""));
    }

    @Test
    public void testingEmptyAndNullStrings() {
        Assert.assertEquals(0, LevenshteinDistanceWithDynamicProgramming.calculateDistance(null, null));
        Assert.assertEquals(0, LevenshteinDistanceWithDynamicProgramming.calculateDistance(null, ""));
        Assert.assertEquals(0, LevenshteinDistanceWithDynamicProgramming.calculateDistance("", null));
        Assert.assertEquals(0, LevenshteinDistanceWithDynamicProgramming.calculateDistance("", ""));
    }

    @Test
    public void loadTest() {
        for (int i = 0; i < 1000000; i++) {
            Assert.assertEquals(4, LevenshteinDistanceWithDynamicProgramming.calculateDistance("banana", "abacate"));
            Assert.assertEquals(5, LevenshteinDistanceWithDynamicProgramming.calculateDistance("cachorro", "cavalo"));
            Assert.assertEquals(7, LevenshteinDistanceWithDynamicProgramming.calculateDistance("ovo", "macarrão"));
            Assert.assertEquals(8, LevenshteinDistanceWithDynamicProgramming.calculateDistance("sorvete", "advogado"));
            Assert.assertEquals(5, LevenshteinDistanceWithDynamicProgramming.calculateDistance("tom", "jerry"));
            Assert.assertEquals(2, LevenshteinDistanceWithDynamicProgramming.calculateDistance("book", "back"));
            Assert.assertEquals(3, LevenshteinDistanceWithDynamicProgramming.calculateDistance("kitten", "sitting"));
        }
    }


}
