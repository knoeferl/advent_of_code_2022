package com.adventofcode.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static com.adventofcode.day2.DAY2.OPTIONS.*;
import static com.adventofcode.day2.DAY2.OPTIONS.ROCK;
import static com.adventofcode.day2.DAY2.RESULT.*;

public class DAY2 {

    public static void main(String[] args) throws IOException {

        var lines = Files.readAllLines(Path.of("src/main/java/com/adventofcode/day2/input.txt"));

        String example = """
                A Y
                B X
                C Z""";

        // lines = Arrays.stream(example.split("\n")).toList();

        System.out.println("First part");
        fistPart(lines);

        System.out.println("Second part");
        secondPart(lines);
    }

    private static void fistPart(List<String> lines) {
        int sum = 0;
        for (var line : lines) {
            if (line.isEmpty()) continue;
            String[] split = line.split(" ");
            OPTIONS enemy = getOptions(split[0]);
            OPTIONS me = getOptions(split[1]);
            int score = score(enemy, me);
            //System.out.printf("%s vs %s = %d\n", enemy, me, score);
            sum += score;
        }
        System.out.println(sum);
    }

    private static void secondPart(List<String> lines) {
        int sum;
        sum = 0;
        for (var line : lines) {
            if (line.isEmpty()) continue;
            String[] split = line.split(" ");
            OPTIONS enemy = getOptions(split[0]);
            OPTIONS me = decide(enemy, getResult(split[1]));
            int score = score(enemy, me);
            //System.out.printf("%s vs %s = %d\n", enemy, me, score);
            sum += score;
        }
        System.out.println(sum);
    }

    enum OPTIONS {
        ROCK(1), PAIPER(2), SCISSORS(3);

        public final int value;

        OPTIONS(int value) {
            this.value = value;
        }
    }

    static public OPTIONS getOptions(String value) {
        return switch (value) {
            case "A", "X" -> ROCK;
            case "B", "Y" -> PAIPER;
            case "C", "Z" -> SCISSORS;
            default -> null;
        };
    }

    enum RESULT {
        LOSS(0), DRAW(3), WIN(6);

        public final int value;

        RESULT(int value) {
            this.value = value;
        }
    }

    static public RESULT getResult(String value) {
        return switch (value) {
            case "X" -> LOSS;
            case "Y" -> DRAW;
            case "Z" -> WIN;
            default -> null;
        };
    }

    static OPTIONS decide(OPTIONS v1, RESULT v2) {
        return switch (v1) {
            case ROCK -> switch (v2) {
                case LOSS -> SCISSORS;
                case DRAW -> ROCK;
                case WIN -> PAIPER;
            };
            case PAIPER -> switch (v2) {
                case LOSS -> ROCK;
                case DRAW -> PAIPER;
                case WIN -> SCISSORS;
            };
            case SCISSORS -> switch (v2) {
                case LOSS -> PAIPER;
                case DRAW -> SCISSORS;
                case WIN -> ROCK;
            };
        };
    }

    static int score(OPTIONS v1, OPTIONS v2) {
        return switch (v1) {
            case ROCK -> switch (v2) {
                case ROCK -> DRAW.value + ROCK.value;
                case PAIPER -> WIN.value + PAIPER.value;
                case SCISSORS -> LOSS.value + SCISSORS.value;
            };
            case PAIPER -> switch (v2) {
                case ROCK -> LOSS.value + ROCK.value;
                case PAIPER -> DRAW.value + PAIPER.value;
                case SCISSORS -> WIN.value + SCISSORS.value;
            };
            case SCISSORS -> switch (v2) {
                case ROCK -> WIN.value + ROCK.value;
                case PAIPER -> LOSS.value + PAIPER.value;
                case SCISSORS -> DRAW.value + SCISSORS.value;
            };
        };
    }
}
