package day05;

import day04.Day04;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Test {

    private static final String INPUT = """
3-5
10-14
16-20
12-18

1
5
8
11
17
32
""";

    @Test
    void testProcessPart1() {
        var result = Day05.processPart1(INPUT);
        assertEquals(Integer.valueOf(3), result);
    }

    @Test
    void testProcessPart2() {
        var result = Day05.processPart2(INPUT);
        assertEquals(Long.valueOf(14), result);
    }
}

