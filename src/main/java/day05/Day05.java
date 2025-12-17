package day05;

import org.apache.commons.lang3.Range;
import shared.Pair;

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
        return new Pair<>(freshIngredients, ingredients);
    }

    public static Integer processPart1(String input) {
        var parsed = parseInput(input);
        var freshIngredients = parsed.first();
        var ingredients = parsed.second();
        return (int) ingredients.filter(ingredient -> freshIngredients.stream()
                        .anyMatch(range -> range.contains(ingredient)))
                .count();
    }
}
