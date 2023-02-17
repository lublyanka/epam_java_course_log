package com.efimchick.ifmo.util;

import com.efimchick.ifmo.Collecting;

import java.util.*;

public class TableTransformer {
    private final List<CourseResult> resultList = new ArrayList<>();


    public void add(CourseResult courseResult) {
        resultList.add(courseResult);
    }

    public void add(TableTransformer tableTransformer) {
        resultList.addAll(tableTransformer.resultList);
    }

    private final static List<IMarks> iMarksList = new ArrayList<>() {
        {
            add((Double x) -> (x > 90 && x <= 100) ? Optional.of("A") : Optional.empty());
            add((Double x) -> (x >= 83 && x <= 90) ? Optional.of("B") : Optional.empty());
            add((Double x) -> (x >= 75 && x < 83) ? Optional.of("C") : Optional.empty());
            add((Double x) -> (x >= 68 && x < 75) ? Optional.of("D") : Optional.empty());
            add((Double x) -> (x > 60 && x < 68) ? Optional.of("E") : Optional.empty());
            add((Double x) -> (x <= 60) ? Optional.of("F") : Optional.empty());
        }
    };

    public static String getMark(double x) {
        return iMarksList.stream().map(mark -> mark.toString(x)).filter(Optional::isPresent).findFirst().orElseThrow().orElseThrow();
    }

    public String toString() {
        TableContent tableContent = new TableContent(resultList);

        String headers = renderHeaders(tableContent);
        String content = renderContent(tableContent);
        String average = renderAverage(tableContent);

        return new StringJoiner("\n").add(headers).add(content)
                .add(average).toString().strip();
    }

    private static String renderContent(TableContent tableContent) {
        return tableContent.resultList.stream()
                .map(x -> String.format(getFormat(tableContent),
                        renderPersonName(x),
                        tableContent.tasks.stream().map(task -> {
                                    int int21 = x.getTaskResults().getOrDefault(task, 0);
                                    return String.format(" %" + task.length() + "s ", int21);
                                })
                                .reduce(TableTransformer::joinRenderedColumns).orElseThrow(),
                        String.format("%.2f", tableContent.personsTotals.get(x.getPerson())),
                        tableContent.personMarks.get(x.getPerson())
                ))
                .sorted()
                .reduce((x1, x2) -> (new StringJoiner("\n").add(x1).add(x2).toString()))
                .orElseThrow();
    }


    private static String renderPersonName(CourseResult x) {
        return x.getPerson().getLastName() + " " + x.getPerson().getFirstName();
    }

    private static String renderHeaders(TableContent tableContent) {
        return String.format(getFormat(tableContent),
                TableContent.FieldNames.STUDENT.label,
                getDinamicHeaderContent(tableContent),
                TableContent.FieldNames.TOTAL.label,
                TableContent.FieldNames.MARK.label);
    }

    private String renderAverage(TableContent tableContent) {
        double averageScore = new Collecting().averageTotalScore(resultList.stream());
        return String.format(getFormat(tableContent),
                TableContent.FieldNames.AVERAGE.label,
                tableContent.tasksAverage.entrySet().stream()
                        .map((k) -> String.format(" %" + k.getKey().length() + "s ", String.format("%.2f", k.getValue())))
                        .reduce(TableTransformer::joinRenderedColumns).orElseThrow(),
                String.format("%.2f", averageScore),
                getMark(averageScore));
    }

    private static StringJoiner getDinamicHeaderContent(TableContent tableContent) {
        return new StringJoiner("", " ", " ")
                .add(tableContent.tasks.stream()
                        .reduce((x1, x2) -> x1 + " | " + x2).orElseThrow());
    }

    private static String getFormat(TableContent tableContent) {
        return "%-" + (tableContent.personLength) + "s |" +
                "%s|" +
                " %" + (tableContent.totalLength) + "s |" +
                " %" + (TableContent.markLength) + "s |";
    }

    private static String joinRenderedColumns(String x1, String x2) {
        return x1 + "|" + x2;
    }
}