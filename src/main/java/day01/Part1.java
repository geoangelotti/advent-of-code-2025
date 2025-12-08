import day01.Day01;

void main() throws IOException {
    try (var input = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("day01.txt"))) {
        String content = new String(input.readAllBytes(), StandardCharsets.UTF_8).trim();
        IO.println(Day01.processPart1(content));
    }
}