package o.mg.day01;

import java.util.ArrayList;
import java.util.List;

public class Day01 {

    private static List<Pair<Character, Integer>> parseInput(String input) {
        String[] lines = input.split("\n");
        List<Pair<Character, Integer>> commands = new ArrayList<>();
        for (String line : lines) {
            char direction = line.charAt(0);
            int rotation = Integer.parseInt(line.substring(1));
            commands.add(new Pair<>(direction, rotation));
        }
        return commands;
    }

    public static Integer processPart1(String input) {
        var parsed = parseInput(input);
        int position = 50;
        int password = 0;
        for (var command : parsed) {
            if (command.first() == 'R') {
                position += command.second();
            } else if (command.first() == 'L') {
                position -= command.second();
            }
            position = ((position % 100) + 100) % 100;
            if (position == 0) {
                password += 1;
            }
        }
        return password;
    }

    public static Integer processPart2(String input) {
        var parsed = parseInput(input);
        int position = 50;
        int password = 0;

        for (var command : parsed) {
            int rotation = command.second();
            int start = position;
            int end;

            if (command.first() == 'R') {
                end = position + rotation;
                int firstMultiple = (start % 100 == 0) ? start + 100 : 100;
                if (firstMultiple <= end) {
                    password += ((end - firstMultiple) / 100) + 1;
                }
                position = end;
            } else if (command.first() == 'L') {
                end = position - rotation;
                int firstMultiple = (start % 100 == 0) ? start - 100 : 0;
                if (end <= firstMultiple) {
                    password += ((firstMultiple - end) / 100) + 1;
                }
                position = end;
            }

            position = ((position % 100) + 100) % 100;
        }
        return password;
    }
}
