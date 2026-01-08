package day07;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Day07 {
    private static ArrayList<ArrayList<Character>> parseInput(String input) {
        var grid = new ArrayList<ArrayList<Character>>();
        var lines = input.split("\n");
        for (String line : lines) {
            var row = new ArrayList<Character>();
            for (Character c : line.toCharArray()) {
                row.add(c);
            }
            grid.add(row);
        }
        return grid;
    }

    private static Pair<Pair<Integer, Integer>, Set<Pair<Integer, Integer>>> getStartAndManifolds(
            ArrayList<ArrayList<Character>> grid) {
        Pair<Integer, Integer> start = Pair.of(-1, -1);
        Set<Pair<Integer, Integer>> manifolds = new HashSet<>();
        for (int j = grid.size() - 1; j >= 0; j--) {
            for (int i = grid.get(j).size() - 1; i >= 0; i--) {
                if (grid.get(j).get(i).equals('S')) {
                    start = Pair.of(i, j);
                }
                if (grid.get(j).get(i).equals('^')) {
                    manifolds.add(Pair.of(i, j));
                }
            }
        }
        return Pair.of(start, manifolds);
    }

    public static Integer processPart1(String input) {
        var grid = parseInput(input);
        var startAndManifolds = getStartAndManifolds(grid);
        var start = startAndManifolds.getLeft();
        var manifolds = startAndManifolds.getRight();
        var traverser = new PathTraverser(grid.size(), grid.size(), manifolds);
        traverser.traverse(start);
        return traverser.getTouchedManifoldsCount();
    }
}
