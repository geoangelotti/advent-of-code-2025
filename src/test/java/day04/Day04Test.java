package day04;

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
        var result = Day04.processPart1(INPUT);
        assertEquals(Integer.valueOf(13), result);
    }

    @Test
    void testProcessPart2() {
        var result = Day04.processPart2(INPUT);
        assertEquals(Integer.valueOf(43), result);
    }
}

