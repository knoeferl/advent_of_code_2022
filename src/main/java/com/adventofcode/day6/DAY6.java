package com.adventofcode.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DAY6 {

    public static void main(String[] args) throws IOException {

        List<String> lines;
        if (false) {
            lines = Files.readAllLines(Path.of("src/main/java/com/adventofcode/day6/input.txt"));
        } else {
            String example = """
                    mjqjpqmgbljsphdztnvjfqwrcgsmlb
                    bvwbjplbgvbhsrlpgdmjqwftvncz
                    nppdvjthqldpwncqszvftbrmjlhg
                    nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg
                    zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw""";
            lines = Arrays.stream(example.split("\n")).toList();
        }

        System.out.println("First part");
        findStart(lines, 4, false);
        System.out.println("Second part");
        findStart(lines, 14, false);

    }

    private static void findStart(List<String> lines, int length, boolean verbose) {
        for (var line : lines) {
            if (line.isEmpty()) {
                continue;
            }
            ArrayList<Character> buffer = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                for (int j = 0; j < buffer.size(); j++) {
                    if (buffer.get(j) == line.charAt(i)) {
                        for (int k = 0; k <= j; k++) {
                            Character remove = buffer.remove(0);
                            if (verbose) {
                                System.out.println("removed " + remove + " because of " + line.charAt(i) + " at " + (i + 1));
                            }
                        }
                        break;
                    }
                }
                buffer.add(line.charAt(i));
                if (buffer.size() == length) {
                    System.out.println(i + 1);
                    buffer.clear();
                    break;
                }
            }
        }
    }

}
