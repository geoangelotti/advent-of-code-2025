package shared;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.function.Function;

public class Solver {
    public static <T> void processDay(String day, Function<String, T> solution) throws IOException {
        try (var input = Objects.requireNonNull(Solver.class.getClassLoader().getResourceAsStream(day))) {
            String content = new String(input.readAllBytes(), StandardCharsets.UTF_8).trim();
            IO.println(solution.apply(content));
        }
    }
}
