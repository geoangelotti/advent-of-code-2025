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

    private static Long solve(ArrayList<ArrayList<String>> worksheet) {
        return worksheet.stream().map(column -> {
            var binaryOperator = column.getFirst();
            return column.stream().
                    skip(1).
                    map(Long::valueOf).
                    reduce(binaryOperator.equals("+") ? Long::sum : multiply).orElse(0L);
        }).reduce(Long::sum).orElse(0L);
    }

    private static ArrayList<ArrayList<String>> parseToCephalopodMath(String input) {
        var pattern = Pattern.compile("[+*]");
        var lines = input.split("\n");
        var matcher = pattern.matcher(lines[lines.length - 1]);
        var maxLength = input.length()/lines.length;
        ArrayList<Integer> indexes = new ArrayList<>();
        while (matcher.find()) {
            indexes.add(matcher.start());
        }
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        for (int i = 0; i < indexes.size(); i++) {
            ArrayList<String> column = new ArrayList<>();
            var index = indexes.get(i);
            column.add(lines[lines.length - 1].substring(index, index + 1));
            var end = (i == indexes.size() - 1) ? maxLength : indexes.get(i + 1) - 1;
            for (int k = index; k < end; k++) {
                StringBuilder number = new StringBuilder();
                for (int j = 0; j < lines.length - 1; j++) {
                    var current = StringUtils.rightPad(lines[j], maxLength, " ");
                    number.append(current.charAt(k));
                }
                column.add(number.toString().trim());
            }
            columns.add(column);
        }
        return columns;
    }

    public static Long processPart1(String input) {
        var parsed = parseInput(input);
        return solve(parsed);
    }

    public static Long processPart2(String input) {
        return null;
    }
}
