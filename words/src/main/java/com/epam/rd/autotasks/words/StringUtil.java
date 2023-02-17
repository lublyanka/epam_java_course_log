package com.epam.rd.autotasks.words;

import java.util.Arrays;
import java.util.StringJoiner;

public class StringUtil {
    public static int countEqualIgnoreCaseAndSpaces(String[] words, String sample) {
        if (words == null || words.length == 0 || sample == null)
            return 0;
        String sampleStrip = sample.strip();
        return (int) Arrays.stream(words).filter(x -> x.strip().equalsIgnoreCase(sampleStrip)).count();
    }

    public static String[] splitWords(String text) {
        if (text == null || text.isEmpty() || text.isBlank())
            return null;
        String[] str = text.split("[,.;:?!\\s]");
        String[] result = Arrays.stream(str).filter(x -> !x.isEmpty()).toArray(String[]::new);
        return result.length == 0 ? null : result;
    }

    public static String convertPath(String path, boolean toWin) {
        if (path == null || path.isEmpty())
            return null;
        if (path.matches("^[/~]+.*(\\\\|~).*|.*//+.*")
                //path.matches(".*[/~]+.*")&&(path.contains("\\")||path.lastIndexOf('~') > 0)
                //|| path.matches(".*//+.*")
                //|| path.contains("C:")&&path.contains("/")
                || path.matches("^C:.*((C:.*)|/.*\\\\?)")
            //|| path.contains("C:") && path.lastIndexOf("C:\\") > 0
        )
            return null;

        String result = path;
        if (toWin) {
            result = path.replaceFirst("^~", "C:\\/User")
                    .replaceFirst("^/", "C:\\/")
                    .replace('/', '\\');
            return result;
        } else {
            result = path.replaceFirst("^C:\\\\User", "~")
                    .replaceFirst("^C:\\\\", "\\/")
                    .replace('\\', '/');
            return result;
        }
    }

    public static String joinWords(String[] words) {
        if (words == null)
            return null;
        StringJoiner buffer = new StringJoiner(", ");
        for (String row : words) {
            if (!row.isEmpty())
                buffer.add(row);
        }
        return buffer.length() == 0 ? null : "[" + buffer + "]";
    }

    public static void main(String[] args) {
        System.out.println("Test 1: countEqualIgnoreCaseAndSpaces");
        String[] words = new String[]{" WordS    \t", "words", "w0rds", "WOR  DS",};
        String sample = "words   ";
        int countResult = countEqualIgnoreCaseAndSpaces(words, sample);
        System.out.println("Result: " + countResult);
        int expectedCount = 2;
        System.out.println("Must be: " + expectedCount);

        System.out.println("Test 2: splitWords");
        String text = "   ,, first, second!!!! third";
        String[] splitResult = splitWords(text);
        System.out.println("Result : " + Arrays.toString(splitResult));
        String[] expectedSplit = new String[]{"first", "second", "third"};
        System.out.println("Must be: " + Arrays.toString(expectedSplit));

        System.out.println("Test 3: convertPath");
        String unixPath = "/some/unix/path";
        String convertResult = convertPath(unixPath, true);
        System.out.println("Result: " + convertResult);
        String expectedWinPath = "C:\\some\\unix\\path";
        System.out.println("Must be: " + expectedWinPath);

        System.out.println("Test 4: joinWords");
        String[] toJoin = new String[]{"go", "with", "the", "", "FLOW"};
        String joinResult = joinWords(toJoin);
        System.out.println("Result: " + joinResult);
        String expectedJoin = "[go, with, the, FLOW]";
        System.out.println("Must be: " + expectedJoin);
    }
}