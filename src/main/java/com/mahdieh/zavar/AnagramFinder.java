package com.mahdieh.zavar;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class AnagramFinder {

    public static void main(String[] args) {
        String inputFilePath = "words-utf8.txt";
        String outputFilePath = "anagrams-output.txt";

        try {
            List<String> words = readWordsFromFile(inputFilePath);
            List<String> anagrams = findAnagrams(words);
            writeAnagramsToFile(anagrams, outputFilePath);

            log.info("Anagrams written to " + outputFilePath);

        } catch (IOException e) {
            log.error("An error occurred while reading or writing files", e);
        }
    }

    public static List<String> readWordsFromFile(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath)).stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toList());
    }

    public static List<String> findAnagrams(List<String> words) {
        //We can use linkedhashmap to keep the order of insertion
        Map<String, List<String>> anagramMap = words.stream()
                .collect(Collectors.groupingBy(AnagramFinder::sortCharacters));

        return anagramMap.values().stream()
                .filter(group -> group.size() > 1)
                .map(group -> String.join(" ", group))
                .collect(Collectors.toList());
    }

    public static void writeAnagramsToFile(List<String> anagrams, String filePath) throws IOException {
        Files.write(Paths.get(filePath), anagrams);
    }

    public static String sortCharacters(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
