package com.adventofcode.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DAY2 {


    public static void main(String[] args) throws IOException {

        var lines = Files.readAllLines(Path.of("src/main/java/com/adventofcode/day2/input.txt"));

        String example = """
        A Y
        B X
        C Z""";

      // lines = Arrays.stream(example.split("\n")).toList();

        System.out.println("First part");
        int sum = 0;
        for (var line : lines) {
            if(line.isEmpty()) continue;
            String[] split = line.split(" ");
            int score = score(split[0], split[1]);
            //printFight(split[0], decide, score);
            sum += score;
        }
        System.out.println(sum);

        System.out.println("Second part");
        sum = 0;
        for (var line : lines) {
            if(line.isEmpty()) continue;
            String[] split = line.split(" ");
            String decide = decide(split[0], split[1]);
            int score = score(split[0], decide);
            //printFight(split[0], decide, score);
            sum += score;
        }
        System.out.println(sum);
    }

    private static void printFight(String v1, String v2, int score) {
        String enemy = switch (v1){
            case "A" -> "ROCK";
            case "B" -> "PAIPER";
            case "C" -> "SCISSORS";
            default -> "UNKONW";
        };
        String me = switch (v2){
            case "X" -> "ROCK";
            case "Y" -> "PAIPER";
            case "Z" -> "SCISSORS";
            default -> "UNKONW";
        };
        System.out.printf("%s vs %s = %d\n", enemy, me, score);
    }
    /*
    ROCK     A X
    PAPER    B Y
    SCISSORS C Z
     */

    static String decide(String v1, String v2){
        return switch (v1) {
            case "A" -> switch (v2) {
                case "X" -> "Z";
                case "Y" -> "X";
                default -> "Y";
            };
            case "B" -> switch (v2) {
                case "X" -> "X";
                case "Y" -> "Y";
                default -> "Z";
            };
            case "C" -> switch (v2) {
                case "X" -> "Y";
                case "Y" -> "Z";
                default -> "X";
            };
            default -> "";
        };
    }

    static int score(String v1, String v2){
        return switch (v1) {
            case "A" -> switch (v2) {
                case "X" -> 3+1;
                case "Y" -> 6+2;
                default -> 0+3;
            };
            case "B" -> switch (v2) {
                case "X" -> 0+1;
                case "Y" -> 3+2;
                default -> 6+3;
            };
            case "C" -> switch (v2) {
                case "X" -> 6+1;
                case "Y" -> 0+2;
                default -> 3+3;
            };
            default -> 0;
        };
    }
}
