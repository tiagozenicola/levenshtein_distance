package com.tiagozenicola.levenshtein.helper;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class LevenshteinDistanceRecursivelyTest {

    @Ignore // too slow!
    @Test
    public void loadTest() {
        for (int i = 0; i < 10000; i++) {
            Assert.assertEquals(4, LevenshteinDistanceRecursively.calculateDistance("banana", "abacate"));
            Assert.assertEquals(5, LevenshteinDistanceRecursively.calculateDistance("cachorro", "cavalo"));
            Assert.assertEquals(7, LevenshteinDistanceRecursively.calculateDistance("ovo", "macarrão"));
            Assert.assertEquals(8, LevenshteinDistanceRecursively.calculateDistance("sorvete", "advogado"));
            Assert.assertEquals(5, LevenshteinDistanceRecursively.calculateDistance("tom", "jerry"));
            Assert.assertEquals(2, LevenshteinDistanceRecursively.calculateDistance("book", "back"));
            Assert.assertEquals(3, LevenshteinDistanceRecursively.calculateDistance("kitten", "sitting"));
        }
    }

    @Test
    public void testingEmptyAndNullStrings() {
        Assert.assertEquals(0, LevenshteinDistanceRecursively.calculateDistance(null, null));
        Assert.assertEquals(0, LevenshteinDistanceRecursively.calculateDistance(null, ""));
        Assert.assertEquals(0, LevenshteinDistanceRecursively.calculateDistance("", null));
        Assert.assertEquals(0, LevenshteinDistanceRecursively.calculateDistance("", ""));
    }

    @Test
    public void testingEmptyStrings() {
        Assert.assertEquals(0, LevenshteinDistanceRecursively.calculateDistance("", ""));
        Assert.assertEquals(7, LevenshteinDistanceRecursively.calculateDistance("", "abacate"));
        Assert.assertEquals(8, LevenshteinDistanceRecursively.calculateDistance("cachorro", ""));
    }

    @Test
    public void testingNullStrings() {
        Assert.assertEquals(0, LevenshteinDistanceRecursively.calculateDistance(null, null));
        Assert.assertEquals(7, LevenshteinDistanceRecursively.calculateDistance(null, "abacate"));
        Assert.assertEquals(8, LevenshteinDistanceRecursively.calculateDistance("cachorro", null));
    }

    @Test
    public void testingVersion1() {
        Assert.assertEquals(4, LevenshteinDistanceRecursively.calculateDistance("banana", "abacate"));
        Assert.assertEquals(5, LevenshteinDistanceRecursively.calculateDistance("cachorro", "cavalo"));
        Assert.assertEquals(7, LevenshteinDistanceRecursively.calculateDistance("ovo", "macarrão"));
        Assert.assertEquals(8, LevenshteinDistanceRecursively.calculateDistance("sorvete", "advogado"));
        Assert.assertEquals(5, LevenshteinDistanceRecursively.calculateDistance("tom", "jerry"));
        Assert.assertEquals(2, LevenshteinDistanceRecursively.calculateDistance("book", "back"));
        Assert.assertEquals(3, LevenshteinDistanceRecursively.calculateDistance("kitten", "sitting"));
        Assert.assertEquals(17, LevenshteinDistanceRecursively.calculateDistance("caminhao", "eu estou de férias"));
    }

}
