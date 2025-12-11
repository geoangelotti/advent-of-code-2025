package day04;

import day03.Day03;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04Test {

    private static final String INPUT = """
..@@.@@@@.
@@@.@.@.@@
@@@@@.@.@@
@.@@@@..@.
@@.@@@@.@@
.@@@@@@@.@
.@.@.@.@@@
@.@@@.@@@@
.@@@@@@@@.
@.@.@@@.@.
""";

    @Test
    void testProcessPart1() {
        Integer result = Day04.processPart1(INPUT);
        assertEquals(Integer.valueOf(13), result);
    }
}

