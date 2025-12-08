package day02;

import shared.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class Day02 {
    private static List<Pair<Long, Long>> parseInput(String input) {
        String[] lines = input.split("\n");
        List<Pair<Long, Long>> ids = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            for (String part : parts) {
                String[] numbers = part.split("-");
                ids.add(new Pair<>(Long.parseLong(numbers[0]), Long.parseLong(numbers[1])));
            }
        }
        return ids;
    }

    private static Stream<Long> findMultiDigitNumbers(Pair<Long, Long> pair) {
        long start = pair.first();
        long end = pair.second();
        HashSet<Long> numbers = new HashSet<>();
        int minDigits = String.valueOf(start).length();
        int maxDigits = String.valueOf(end).length();
        for (int length = minDigits; length <= maxDigits; length++) {
            for (int patternLength = 1; patternLength < length; patternLength++) {
                if (length % patternLength != 0) continue;
                int repeats = length / patternLength;
                if (repeats < 2) continue;

                long minPattern = (patternLength == 1) ? 1 : (long) Math.pow(10, patternLength - 1);
                long maxPattern = (long) Math.pow(10, patternLength) - 1;

                for (long pattern = minPattern; pattern <= maxPattern; pattern++) {
                    String repeated = String.valueOf(pattern).repeat(repeats);
                    long number = Long.parseLong(repeated);
                    if (number >= start && number <= end) {
                        numbers.add(number);
                    }
                }
            }
        }
        return numbers.stream();
    }

    public static Long processPart1(String input) {
        var parsed = parseInput(input);
        return parsed.
                stream().
                flatMap(Day02::findMultiDigitNumbers).
                filter(n -> {
                            String repeated = String.valueOf(n);
                            String firstHalf = repeated.substring(0, repeated.length() / 2);
                            String secondHalf = repeated.substring(repeated.length() / 2);
                            return firstHalf.equals(secondHalf);
                        }
                ).
                reduce(0L, Long::sum);
    }

    public static Long processPart2(String input) {
        var parsed = parseInput(input);
        return parsed.
                stream().
                flatMap(Day02::findMultiDigitNumbers).
                reduce(0L, Long::sum);
    }
}