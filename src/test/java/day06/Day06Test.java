package day06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {

    private static final String INPUT = """
123 328  51 64
 45 64  387 23
  6 98  215 314
*   +   *   +
""";

    @Test
    void testProcessPart1() {
        var result = Day06.processPart1(INPUT);
        assertEquals(Long.valueOf(4277556), result);
    }

    @Test
    void testProcessPart2() {
        var result = Day06.processPart2(INPUT);
        assertEquals(Long.valueOf(3263827), result);
    }

}

