package com.efimchick.ifmo;

import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;
import com.efimchick.ifmo.util.TableTransformer;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Collecting {
    private List<CourseResult> courseResults;

    public int sum(IntStream stream) {
        return stream.sum();
    }

    public int production(IntStream stream) {
        return stream.reduce(1, (a, b) -> a * b);
    }

    public int oddSum(IntStream stream) {
        return stream.filter(a -> a % 2 != 0).sum();
    }

    public Map<Integer, Integer> sumByRemainder(int i, IntStream stream) {
        return stream
                .boxed()
                .collect(
                        Collectors.groupingBy(
                                x -> x % i,
                                Collectors.summingInt(x -> x)));

//        return stream
//                .collect(
//                        HashMap::new,
//                        (map, value) -> map.merge(value % i, value, Integer::sum),
//                        HashMap::putAll
//                        );
    }

    public Map<Person, Double> totalScores(Stream<CourseResult> stream) {

        courseResults = stream.collect(Collectors.toList());
        final int subjects = courseResults.stream()
                .flatMap(x -> x.getTaskResults().keySet().stream())
                .collect(Collectors.toSet()).size();

        return courseResults.stream().collect(Collectors.toMap(
                CourseResult::getPerson,
                x -> x.getTaskResults()
                        .values().stream()
                        .mapToDouble(Integer::intValue)
                        .reduce(0, Double::sum) / subjects)
        );
    }

    public double averageTotalScore(Stream<CourseResult> stream) {
        Map<Person, Double> mapa = totalScores(stream);

        return mapa.values().stream()
                .mapToDouble(x -> x)
                .reduce(Double::sum).orElseThrow()
                / mapa.keySet().size();
    }

    public Map<String, Double> averageScoresPerTask(Stream<CourseResult> stream) {
        courseResults = stream.collect(Collectors.toList());
        final int personListSize = (int) courseResults.stream().map(CourseResult::getPerson).distinct().count();
        Map<String, Integer> mapa = courseResults.stream().
                flatMap(x -> x.getTaskResults().entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.reducing(0, Map.Entry::getValue, (Integer::sum))));

        return mapa.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> x.getValue() * 1.0 / personListSize));
    }

    public String easiestTask(Stream<CourseResult> stream) {
        return averageScoresPerTask(stream)
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow().getKey();
    }


    public Map<Person, String> defineMarks(Stream<CourseResult> stream) {
        //Map<Person, Double> courseResults = totalScores(stream);

        return totalScores(stream).entrySet().stream().collect(Collectors.toMap(
                        Map.Entry::getKey,
                        x -> {
                            return TableTransformer.getMark(x.getValue());
                            /*Double sum = x.getValue();
                            return (sum > 90 && sum <= 100) ? "A" :
                                    (sum >= 83 && sum <= 90) ? "B" :
                                            (sum >= 75 && sum < 83) ? "C" :
                                                    (sum >= 68 && sum < 75) ? "D" :
                                                            (sum >= 60 && sum < 68) ? "E" : "F";*/
                        }
                )
        );
    }

    public Collector<CourseResult, TableTransformer, String> printableStringCollector() {

        return new Collector<>() {

            @Override
            public Supplier<TableTransformer> supplier() {
                return TableTransformer::new;
            }

            @Override
            public BiConsumer<TableTransformer, CourseResult> accumulator() {
                return TableTransformer::add;
            }

            @Override
            public BinaryOperator<TableTransformer> combiner() {
                return (l1, l2) -> {
                    l1.add(l2);
                    return l1;
                };
            }

            @Override
            public Function<TableTransformer, String> finisher() {
                return TableTransformer::toString;
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Set.of(Characteristics.UNORDERED);
            }
        };
    }
}