package day05;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.stream.Stream;

public class Day05 {
    private static Pair<List<Range<Long>>, Stream<Long>> parseInput(String input) {
        var parts = input.split("\n\n");
        List<Range<Long>> freshIngredients = parts[0].lines().map(line -> {
            var nums = line.split("-");
            return Range.of(Long.parseLong(nums[0]), Long.parseLong(nums[1]));
        }).toList();
        Stream<Long> ingredients = parts[1].lines().map(Long::parseLong);
        return Pair.of(freshIngredients, ingredients);
    }

    public static Integer processPart1(String input) {
        var parsed = parseInput(input);
        var freshIngredients = parsed.getLeft();
        var ingredients = parsed.getRight();
        return (int) ingredients.filter(ingredient -> freshIngredients.stream()
                        .anyMatch(range -> range.contains(ingredient)))
                .count();
    }

    public static Long processPart2(String input) {
        var freshIngredients = parseInput(input).getLeft();
        List<Range<Long>> sorted = freshIngredients.stream()
                .sorted((a, b) -> Long.compare(a.getMinimum(), b.getMinimum()))
                .toList();

        long count = 0;
        long currentMax = Long.MIN_VALUE;

        for (Range<Long> range : sorted) {
            long start = Math.max(range.getMinimum(), currentMax + 1);
            long end = range.getMaximum();
            if (start <= end) {
                count += (end - start + 1);
                currentMax = Math.max(currentMax, end);
            }
        }

        return count;
    }
}
