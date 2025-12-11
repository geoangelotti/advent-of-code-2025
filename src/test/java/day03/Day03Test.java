package day03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Test {

    private static final String INPUT = """
987654321111111
811111111111119
234234234234278
818181911112111
""";

    @Test
    void testProcessPart1() {
        var result = Day03.processPart1(INPUT);
        assertEquals(Integer.valueOf(357), result);
    }

    @Test
    void testProcessPart2() {
        var result = Day03.processPart2(INPUT);
        assertEquals(Long.valueOf(3121910778619L), result);
    }

}

