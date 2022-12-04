package com.adventofcode.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Range;

public class DAY4 {

    public static void main(String[] args) throws IOException {


        var lines = Files.readAllLines(Path.of("src/main/java/com/adventofcode/day4/input.txt"));

        String example = """
2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8""";

        //lines = Arrays.stream(example.split("\n")).toList();

        System.out.println("First part");

        int sum = 0;
        for (var line : lines) {
            if (line.isEmpty()) continue;

            List<Range<Integer>> rangeList = Arrays.stream(line.split(",")).map(r -> {
                String[] split = r.split("-");
                return Range.between(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            }).toList();

           if(rangeList.get(0).containsRange(rangeList.get(1)) || rangeList.get(1).containsRange(rangeList.get(0))){
               sum++;
           }
        }
        System.out.println(sum);


        System.out.println("Second part");

         sum = 0;
        for (var line : lines) {
            if (line.isEmpty()) continue;

            List<Range<Integer>> rangeList = Arrays.stream(line.split(",")).map(r -> {
                String[] split = r.split("-");
                return Range.between(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            }).toList();

            if(rangeList.get(0).isOverlappedBy(rangeList.get(1)) || rangeList.get(1).isOverlappedBy(rangeList.get(0))){
                sum++;
            }
        }
        System.out.println(sum);
    }

}
