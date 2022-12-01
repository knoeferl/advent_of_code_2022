package com.adventofcode.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;

public class day1 {

    public static void main(String[] args) throws IOException {

        var lines = Files.readAllLines(Path.of("src/main/java/com/adventofcode/day1/input.txt"));

        HashMap<Integer, Integer> sums = new HashMap<>();
        int count = 0;
        for (var line : lines) {
            if (line.isEmpty()) {
                count++;
            } else {
                if (sums.containsKey(count)) {
                    sums.put(count, sums.get(count) + Integer.parseInt(line));
                } else {
                    sums.put(count, Integer.parseInt(line));
                }
            }
        }
        //fist answer
        System.out.println(sums.values().stream().max(Integer::compareTo).orElse(0));
        //second answer
        System.out.println(sums.values().stream().sorted(Comparator.reverseOrder()).limit(3).reduce(Integer::sum).orElse(0));
    }
}
