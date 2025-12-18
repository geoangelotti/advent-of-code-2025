package day06;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;


public class Day06 {
    private static final BinaryOperator<Long> multiply = (x, y) -> x * y;

    private static List<Pair<ArrayList<Long>, String>>parseInput(String input) {
        ArrayList<ArrayList<Long>> result = new ArrayList<>();
        var lines = input.split("\n");
        var lastLine = lines[lines.length - 1].split(" +");
        Arrays.stream(lastLine).forEach(column -> {
            result.add(new ArrayList<>());
        });
        for (int i = 0; i < lines.length-1; i++) {
            var columns = lines[i].split(" +");
            for (int j = 0; j < columns.length; j++) {
                var column = result.get(j);
                column.add(Long.parseLong(columns[j]));
                result.set(j, column);
            }
        }
        var parsed = new ArrayList<Pair<ArrayList<Long>, String>>();
        for (int j = 0; j < lastLine.length; j++) {
            parsed.add(Pair.of(result.get(j), lastLine[j]));
        }
        return parsed;
    };

    public static Long processPart1(String input) {
        var parsed = parseInput(input);
        return parsed.stream().map(row -> {
            var list = row.getLeft();
            var binaryOperator = row.getRight();
            return list.stream().reduce(binaryOperator.equals("+") ? Long::sum : multiply).orElse(0L);
        }).reduce(Long::sum).orElse(0L);
    }
}
