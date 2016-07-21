package com.levenshtein.helper;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class LevenshteinDistanceWithDynamicProgrammingTest {

    @Ignore
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

    @Test
    public void testingAccent() {
        Assert.assertEquals(1, LevenshteinDistanceWithDynamicProgramming.calculateDistance("celular", "célula"));
        Assert.assertEquals(0, LevenshteinDistanceWithDynamicProgramming.calculateDistance("órgão", "orgao"));
        Assert.assertEquals(0,
                LevenshteinDistanceWithDynamicProgramming.calculateDistance("áéíóú ãẽĩõũ àèìòù äëïöü", "aeiou aeiou aeiou aeiou"));
    }

    @Test
    public void testingCaseSensitive() {
        Assert.assertEquals(0, LevenshteinDistanceWithDynamicProgramming.calculateDistance("casa", "CASA"));
        Assert.assertEquals(0, LevenshteinDistanceWithDynamicProgramming.calculateDistance("asdbqhbcnmmwk sdcfnwejqrfhxcvopipqwemkfnxc",
                "ASDBQHBCNMMWK SDCFNWEJQRFHXCVOPIPQWEMKFNXC"));
    }

    @Test
    public void testingEmptyAndNullStrings() {
        Assert.assertEquals(0, LevenshteinDistanceWithDynamicProgramming.calculateDistance(null, null));
        Assert.assertEquals(0, LevenshteinDistanceWithDynamicProgramming.calculateDistance(null, ""));
        Assert.assertEquals(0, LevenshteinDistanceWithDynamicProgramming.calculateDistance("", null));
        Assert.assertEquals(0, LevenshteinDistanceWithDynamicProgramming.calculateDistance("", ""));
    }

    @Test
    public void testingEmptyStrings() {
        Assert.assertEquals(0, LevenshteinDistanceWithDynamicProgramming.calculateDistance("", ""));
        Assert.assertEquals(7, LevenshteinDistanceWithDynamicProgramming.calculateDistance("", "abacate"));
        Assert.assertEquals(8, LevenshteinDistanceWithDynamicProgramming.calculateDistance("cachorro", ""));
    }

    @Test
    public void testingLargeWords() {
        Assert.assertEquals(10,
                LevenshteinDistanceWithDynamicProgramming.calculateDistance(
                        "palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1",
                        "palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2"));
        Assert.assertEquals(60, LevenshteinDistanceWithDynamicProgramming.calculateDistance(
                "palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1palavra1",
                "palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2palavra2"));
        Assert.assertEquals(478, LevenshteinDistanceWithDynamicProgramming.calculateDistance(
                "YPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGCYPMCBSFRGQAGOGC",
                "CLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLFCLHFUAJGJBDTWLF"));
    }

    @Test
    public void testingNullStrings() {
        Assert.assertEquals(0, LevenshteinDistanceWithDynamicProgramming.calculateDistance(null, null));
        Assert.assertEquals(7, LevenshteinDistanceWithDynamicProgramming.calculateDistance(null, "abacate"));
        Assert.assertEquals(8, LevenshteinDistanceWithDynamicProgramming.calculateDistance("cachorro", null));
    }

    @Test
    public void testingSpaces() {
        Assert.assertEquals(0,
                LevenshteinDistanceWithDynamicProgramming.calculateDistance("cachorro molhado", "   cachorro    molhado   "));
    }

    @Test
    public void testingWithSuccess() {
        Assert.assertEquals(3, LevenshteinDistanceWithDynamicProgramming.calculateDistance("kitten", "sitting"));
        Assert.assertEquals(4, LevenshteinDistanceWithDynamicProgramming.calculateDistance("banana", "abacate"));
        Assert.assertEquals(5, LevenshteinDistanceWithDynamicProgramming.calculateDistance("cachorro", "cavalo"));
        Assert.assertEquals(7, LevenshteinDistanceWithDynamicProgramming.calculateDistance("ovo", "macarrão"));
        Assert.assertEquals(8, LevenshteinDistanceWithDynamicProgramming.calculateDistance("sorvete", "advogado"));
        Assert.assertEquals(5, LevenshteinDistanceWithDynamicProgramming.calculateDistance("tom", "jerry"));
        Assert.assertEquals(2, LevenshteinDistanceWithDynamicProgramming.calculateDistance("book", "back"));
        Assert.assertEquals(3, LevenshteinDistanceWithDynamicProgramming.calculateDistance("kitten", "sitting"));
        Assert.assertEquals(16, LevenshteinDistanceWithDynamicProgramming.calculateDistance("caminhao pesado", "eu estou de férias"));
    }

}
