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

    public static Long processPart2(String input) {
        return null;
    }
}
