package day03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day03 {

    private static List<String> parseInput(String input) {
        return Arrays.asList(input.split("\n"));
    }

    public static Integer processPart1(String input) {
        var lines = parseInput(input);
        var batteries = new ArrayList<Integer>();
        for (String line : lines) {
            var digits = line.split("");
            int largest = Integer.parseInt(digits[0] + digits[1]);
            int largestDigit = Math.max(Integer.parseInt(digits[0]), Integer.parseInt(digits[1]));
            for (int i = 2; i < digits.length; i++) {
                int currentDigit = Integer.parseInt(digits[i]);
                int value = largestDigit * 10 + currentDigit;
                if (value > largest) {
                    largest = value;
                }
                if (currentDigit > largestDigit) {
                    largestDigit = currentDigit;
                }
            }
            batteries.add(largest);
        }
        return batteries.stream().reduce(0, Integer::sum);
    }

    private static int smallestDigitIndex(String line) {
        var digits = Arrays.stream(line.split("")).map(Integer::parseInt).toList();
        int smallestIndex = 0;
        int smallest = digits.getFirst();
        for (int i = 1; i < digits.size(); i++) {
            if (digits.get(i) < smallest) {
                smallest = digits.get(i);
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    private static long candidateFrom(int number, int smallestDigit, long largest ) {
        String largestStr = String.valueOf(largest);
        String candidateStr = largestStr.substring(0, smallestDigit) + largestStr.substring(smallestDigit+1) + number;
        return Long.parseLong(candidateStr);
    }

    public static Long processPart2(String input) {
        var lines = parseInput(input);
        var batteries = new ArrayList<Long>();
        for (String line : lines) {
            var digits = line.split("");
            long largest = Long.parseLong(line.substring(0,12));
            int smallestIndex = smallestDigitIndex(line);
            for (int i = 12; i < digits.length; i++) {
                long candidate = candidateFrom(Integer.parseInt(digits[i]), smallestIndex, largest);
                IO.println("Candidate: " + candidate + " largest: " + largest);
                if (candidate > largest) {
                    largest = candidate;
                    smallestIndex = smallestDigitIndex(String.valueOf(largest));
                }
            }
            batteries.add(largest);
        }
        return batteries.stream().reduce(0L, Long::sum);
    }
}
