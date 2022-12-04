package com.adventofcode.day3;


import com.adventofcode.day2.DAY2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;


public class DAY3 {

    public static void main(String[] args) throws IOException {

        var lines = Files.readAllLines(Path.of("src/main/java/com/adventofcode/day3/input.txt"));

        String example = """
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw""";

        //lines = Arrays.stream(example.split("\n")).toList();

        System.out.println("First part");

        int sum = 0;
        for (var line : lines) {
            if (line.isEmpty()) continue;
            String comp1 = line.substring(0, line.length() / 2);
            String comp2 = line.substring(line.length() / 2);

            String u = Arrays.stream(comp1.split("")).filter(c -> Arrays.asList(comp2.split("")).contains(c)).findAny().get();
            int value = getPriority(u);
            sum += value;
        }
        System.out.println(sum);


        System.out.println("Second part");
         sum = 0;
        for (int i = 0; i < lines.size(); i += 3) {
            HashMap<String, Integer> count = new HashMap<>();
            List<String> line1 = Arrays.stream(lines.get(i).split("")).distinct().toList();
            List<String> line2 = Arrays.stream(lines.get(i+1).split("")).distinct().toList();
            List<String> line3 = Arrays.stream(lines.get(i+2).split("")).distinct().toList();


            for (var l : line1) {
                if (count.containsKey(l)) {
                    int value = count.get(l) + 1;
                    count.put(l, value);
                } else count.put(l, 1);
            }
            for (var l : line2) {
                if (count.containsKey(l)) {
                    count.put(l, count.get(l) + 1);
                } else count.put(l, 1);
            }
            for (var l : line3) {
                if (count.containsKey(l)) {
                    count.put(l, count.get(l) + 1);
                } else count.put(l, 1);
            }

            List<Map.Entry<String, Integer>> first = count.entrySet().stream().filter(u -> u.getValue() == 3).toList();
            System.out.println(first);
            sum += getPriority(first.get(0).getKey());
        }
        System.out.println(sum);

    }

    private static int getPriority(String u) {
        char c = u.charAt(0);
        int value;
        if (c < 95) {
            value = c - 64 + 26;
        } else {
            value = c - 96;
        }
        System.out.println(c + " " + value);
        return value;
    }

}
