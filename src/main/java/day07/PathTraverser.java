package day07;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class PathTraverser {
    private final int MAX_HEIGHT;
    private final int MAX_WIDTH;
    private final Set<Pair<Integer, Integer>> MANIFOLDS;
    private final Set<Pair<Integer, Integer>> touchedManifolds;
    private final Set<Pair<Integer, Integer>> seenPoints;
    private final Map<Pair<Integer, Integer>, Long> manifoldTimelines;

    public PathTraverser(int maxHeight, int maxWidth, Set<Pair<Integer, Integer>> manifolds) {
        MAX_HEIGHT = maxHeight;
        MAX_WIDTH = maxWidth;
        MANIFOLDS = manifolds;
        touchedManifolds = new HashSet<>();
        seenPoints = new HashSet<>();
        manifoldTimelines = new HashMap<>();
    }

    private boolean isInBounds(Pair<Integer, Integer> point) {
        return point.getLeft() < MAX_WIDTH && point.getRight() < MAX_HEIGHT && point.getLeft() >= 0;
    }

    public void traverse(Pair<Integer, Integer> start) {
        var current = start;
        while (isInBounds(current)) {
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

    public ArrayList<Map.Entry<Pair<Integer, Integer>, Long>> getTimelinesSorted() {
        var timelines = new ArrayList<>(manifoldTimelines.entrySet());
        timelines.sort(
                (o1, o2) -> {
                    int compareFirst = o1.getKey().getRight().compareTo(o2.getKey().getRight());
                    if (compareFirst != 0) {
                        return compareFirst;
                    }
                    return o1.getKey().getLeft().compareTo(o2.getKey().getRight());
                });
        return timelines;
    }

}
