package o.mg.day01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Day01Test {

    private static final String INPUT = """
L68
L30
R48
L5
R60
L55
L1
L99
R14
L82
""";

    @Test
    void testProcessPart1() {
        Integer result = Day01.processPart1(INPUT);
        assertEquals(Integer.valueOf(3), result);
    }
}

