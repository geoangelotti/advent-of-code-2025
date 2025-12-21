package day04;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class Day04 {
    private static List<List<Character>> parseInput(String input) {
        var lines = input.lines().toList();
        var grid = new ArrayList<List<Character>>();
        for (var line : lines) {
            var row = new ArrayList<Character>();
            for (var ch : line.toCharArray()) {
                row.add(ch);
            }
            grid.add(row);
        }
        return grid;
    }

    private static boolean isRollReachable(List<List<Character>> grid, int r, int c) {
        int count = 0;
        count += get(grid, r - 1, c - 1);
        count += get(grid, r - 1, c);
        count += get(grid, r - 1, c + 1);
        count += get(grid, r, c - 1);
        count += get(grid, r, c + 1);
        count += get(grid, r + 1, c - 1);
        count += get(grid, r + 1, c);
        count += get(grid, r + 1, c + 1);
        return count < 4;
    }

    private static int get(List<List<Character>> grid, int r, int c) {
        try {
            return grid.get(r).get(c) == '@' ? 1 : 0;
        } catch (IndexOutOfBoundsException ex) {
            return 0;
        }
    }

    private static Integer countRolls(List<List<Character>> grid) {
        return countRollsAndPositions(grid).getLeft();
    }

    private static Pair<Integer, List<Pair<Integer, Integer>>> countRollsAndPositions(List<List<Character>> grid) {
        int count = 0;
        var positions = new ArrayList<Pair<Integer, Integer>>();
        int rows = grid.size();
        int cols = grid.getFirst().size();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid.get(r).get(c) == '@' && isRollReachable(grid, r, c)) {
                    count += 1;
                    positions.add(Pair.of(r, c));
                }
            }
        }
        return Pair.of(count, positions);
    }

    private static Integer recursivelyCollect(List<List<Character>> grid) {
        var currentCountAndPositions = countRollsAndPositions(grid);
        if (currentCountAndPositions.getLeft() == 0) return 0;
        cleanGrid(grid, currentCountAndPositions.getRight());
        return recursivelyCollect(grid) + currentCountAndPositions.getLeft();
    }

    private static void cleanGrid(List<List<Character>> grid, List<Pair<Integer, Integer>> positions) {
        for (var pos : positions) {
            grid.get(pos.getLeft()).set(pos.getRight(), '.');
        }
    }

    public static Integer processPart1(String input) {
        var grid = parseInput(input);
        return countRolls(grid);
    }

    public static Integer processPart2(String input) {
        var grid = parseInput(input);
        return recursivelyCollect(grid);
    }
}
