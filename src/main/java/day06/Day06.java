package day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;


public class Day06 {
    private static final BinaryOperator<Long> multiply = (x, y) -> x * y;

    private static ArrayList<ArrayList<String>> rotate(ArrayList<ArrayList<String>> grid) {
        int rows = grid.size();
        int cols = grid.getFirst().size();
        ArrayList<ArrayList<String>> rotated = new ArrayList<>();
        for (int col = 0; col < cols; col++) {
            ArrayList<String> newRow = new ArrayList<>();
            for (int row = rows - 1; row >= 0; row--) {
                newRow.add(grid.get(row).get(col));
            }
            rotated.add(newRow);
        }
        return rotated;
    }

    private static ArrayList<ArrayList<String>> parseInput(String input) {
        String[] lines = input.split("\n");
        ArrayList<ArrayList<String>> grid = Arrays.stream(lines)
                .map(line ->
                        Arrays.stream(line.trim().split("\\s+"))
                                .collect(Collectors.toCollection(ArrayList::new)))
                .collect(Collectors.toCollection(ArrayList::new));
        return rotate(grid);
    }

    public static Long processPart1(String input) {
        var parsed = parseInput(input);
        return parsed.stream().map(column -> {
            var binaryOperator = column.getFirst();
            return column.stream().
                    skip(1).
                    map(Long::valueOf).
                    reduce(binaryOperator.equals("+") ? Long::sum : multiply).orElse(0L);
        }).reduce(Long::sum).orElse(0L);
    }
}
