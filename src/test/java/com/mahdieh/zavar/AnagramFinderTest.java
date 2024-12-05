package com.mahdieh.zavar;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AnagramFinderTest {

    @Test
    public void testSortCharacters() {
        assertEquals("aekt", AnagramFinder.sortCharacters("akte"));
        assertEquals("aekt", AnagramFinder.sortCharacters("kate"));
        assertEquals("adilr", AnagramFinder.sortCharacters("aldri"));
    }

    @Test
    public void testFindAnagrams() {

        // Arrange
        List<String> words = Arrays.asList("akte", "kate", "teak", "aldri", "arild", "aller", "ralle", "alt", "tal");
        List<String> expected = Arrays.asList(
                "akte kate teak",
                "aldri arild",
                "aller ralle",
                "alt tal"
        );

        // Act
        List<String> result = AnagramFinder.findAnagrams(words);

        expected = new ArrayList<>(expected);
        result = new ArrayList<>(result);
        Collections.sort(expected);
        Collections.sort(result);

        // Assert
        assertEquals(expected, result);
    }
}
