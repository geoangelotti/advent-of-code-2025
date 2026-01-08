package day07;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;

public class PathTraverser {
    private final int MAX_HEIGHT;
    private final int MAX_WIDTH;
    private final Set<Pair<Integer, Integer>> MANIFOLDS;
    private final Set<Pair<Integer, Integer>> touchedManifolds;
    private final Set<Pair<Integer, Integer>> seenPoints;

    public PathTraverser(int maxHeight, int maxWidth, Set<Pair<Integer, Integer>> manifolds) {
        MAX_HEIGHT = maxHeight;
        MAX_WIDTH = maxWidth;
        MANIFOLDS = manifolds;
        touchedManifolds = new HashSet<>();
        seenPoints = new HashSet<>();
    }

    public void traverse(Pair<Integer, Integer> start) {
        var current = start;
        while (current.getLeft() < MAX_WIDTH && current.getRight() < MAX_HEIGHT && current.getLeft() >= 0) {
            var next = Pair.of(current.getLeft(), current.getRight() + 1);
            if (seenPoints.contains(next)) {
                break;
            }
            seenPoints.add(next);
            if (MANIFOLDS.contains(next)) {
                touchedManifolds.add(next);
                traverse(Pair.of(next.getLeft() - 1, next.getRight()));
                traverse(Pair.of(next.getLeft() + 1, next.getRight()));
                break;
            }
            current = next;
        }
    }

    public Integer getTouchedManifoldsCount() {
        return touchedManifolds.size();
    }

}
