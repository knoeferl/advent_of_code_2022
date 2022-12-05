package com.adventofcode.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class day1 {

    public static void main(String[] args) throws IOException {

        var lines = Files.readAllLines(Path.of("src/main/java/com/adventofcode/day1/input.txt"));

        List<Integer> sums = new ArrayList<>();
        int sum = 0;
        for (var line : lines) {
            if (line.isEmpty()) {
                sums.add(sum);
                sum = 0;
            } else {
                sum += Integer.parseInt(line);
            }
        }
        if (sum != 0) sums.add(sum);
        //fist answer
        System.out.println(sums.stream().max(Integer::compareTo).orElse(0));
        //second answer
        System.out.println(sums.stream().sorted(Comparator.reverseOrder()).limit(3).reduce(Integer::sum).orElse(0));
    }
}
