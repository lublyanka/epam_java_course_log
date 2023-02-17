package com.efimchick.ifmo.streams.countwords;


import java.util.*;
import java.util.stream.Collectors;

public class Words {
    public static final int MIN_WORD_LENGTH = 4;
    public static final int MIN_FREQUENCY_RATE = 10;

    public String countWords(List<String> lines) {
        Map<String, Long> words = getWordsMap(lines);

        return words.entrySet().stream()
                .filter(w -> w.getValue() >= MIN_FREQUENCY_RATE)
                .sorted(Map.Entry.comparingByKey())
                .sorted((v1, v2) -> Math.toIntExact((v2.getValue() - v1.getValue())))
                .map(this::toString)
                .collect(Collectors.joining()).strip();
    }

    private String toString(Map.Entry<String, Long> entry) {

        return entry.getKey() + " - " + entry.getValue() + "\n";
    }

    private static Map<String, Long> getWordsMap(List<String> lines) {
        return lines.stream()
                .map(l -> l.toLowerCase()
                        .replaceAll("[^a-zа-я]", " ")
                        .split(" "))
                .flatMap(w -> Arrays.stream(w)
                        .filter(x -> x.length() >= MIN_WORD_LENGTH))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
    }
}
