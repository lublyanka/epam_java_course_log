package com.efimchick.ifmo.collections.countwords;


import java.util.*;

public class Words {

    public static final int MIN_WORD_LENGTH = 4;
    public static final int MIN_FREQUENCY_RATE = 10;

    public String countWords(List<String> lines) {

        HashMap<String, Integer> words = new HashMap<>();
        getWordsMap(lines, words);

        ValueComparator bvc = new ValueComparator(words);
        TreeMap<String, Integer> sortedMap = new TreeMap<>(bvc);

        sortedMap.putAll(words);
        //System.out.println(words);
        //System.out.println(sortedMap);
        //System.out.println(toString(sortedMap));
        return toString(sortedMap);
    }

    private static void getWordsMap(List<String> lines, HashMap<String, Integer> words) {
        for (String line : lines) {
            String[] split = line
                    .toLowerCase()
                    .replaceAll("[^a-zа-я]", " ")
                    .split(" ");
            for (String word : split) {
                {
                    words.putIfAbsent(word, 0);
                    words.put(word, words.get(word) + 1);
                }
            }
        }
    }

    public String toString(Map<String, Integer> map) {
        if (!map.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() >= MIN_FREQUENCY_RATE
                        && entry.getKey().length() >= MIN_WORD_LENGTH) {
                    sb.append(entry.getKey())
                            .append(" - ")
                            .append(entry.getValue());

                    sb.append("\n");
                }
            }
            return sb.toString().trim();
        } else return "";

    }

    public String toString(Map.Entry<String, Integer> entry) {
        if (!(entry ==null)) {
            StringBuilder sb = new StringBuilder();
            {
                if (entry.getValue() >= MIN_FREQUENCY_RATE
                        && entry.getKey().length() >= MIN_WORD_LENGTH) {
                    sb.append(entry.getKey())
                            .append(" - ")
                            .append(entry.getValue());

                    sb.append("\n");
                }
            }
            return sb.toString().trim();
        } else return "";

    }

    static class ValueComparator implements Comparator<String> {
        Map<String, Integer> base;

        public ValueComparator(Map<String, Integer> base) {
            this.base = base;
        }

        public int compare(String a, String b) {
            if (base.get(a) > base.get(b)) {
                return -1;
            } else if (Objects.equals(base.get(a), base.get(b))) {
                if (a.compareTo(b) > 0)
                    return 1;
                else return -1;
            }
            return 1;// returning 0 would merge keys
        }
    }
}
