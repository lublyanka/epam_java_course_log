package com.efimchick.ifmo.util;

import com.efimchick.ifmo.Collecting;

import java.util.*;
import java.util.stream.Collectors;

public class TableContent {

    public final List<CourseResult> resultList = new ArrayList<>();
    public final Map<Person, String> personMarks;
    public final Map<Person, Double> personsTotals;
    public final TreeSet<String> tasks = new TreeSet<>();
    public final TreeMap<String, Double> tasksAverage = new TreeMap<>();
    public int personLength;
    public int totalLength;
    public static final int markLength = 4;

    enum FieldNames {
        STUDENT("Student"),
        TOTAL("Total"),
        MARK("Mark"),
        AVERAGE("Average");

        public final String label;

        FieldNames(String label) {
            this.label = label;
        }
    }

    public TableContent(List<CourseResult> courseResults) {
        resultList.addAll(courseResults);
        personMarks = new Collecting().defineMarks(resultList.stream());
        personsTotals = new Collecting().totalScores(resultList.stream());
        tasks.addAll(resultList.stream().flatMap(x -> x.getTaskResults().keySet().stream()).collect(Collectors.toSet()));
        tasksAverage.putAll(new Collecting().averageScoresPerTask(resultList.stream()));
        HashSet<Person> persons = resultList.stream().map(CourseResult::getPerson).collect(Collectors.toCollection(HashSet::new));
        personLength = 1 + persons.stream().map(x -> x.getLastName().length() + x.getFirstName().length()).max(Integer::compareTo).orElseThrow();
        int totalScoreLength = personsTotals.values().stream().map(x -> String.format("%.2f", x).length()).max(Integer::compareTo).orElseThrow();
        totalLength = Math.max(5, totalScoreLength);
    }
}
