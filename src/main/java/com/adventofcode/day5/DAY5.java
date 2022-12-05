package com.adventofcode.day5;

import org.apache.commons.lang3.Range;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DAY5 {

    public static void main(String[] args) throws IOException {


        var lines = Files.readAllLines(Path.of("src/main/java/com/adventofcode/day5/input.txt"));

        String example = """
    [D]   \s
[N] [C]   \s
[Z] [M] [P]
 1   2   3\s

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2""";

       // lines = Arrays.stream(example.split("\n")).toList();

        System.out.println("First part");

        boolean readMoves = false;
        HashMap<Integer, LinkedList<Character>> stacks = new HashMap<>();
        for (var line : lines) {
            if (line.isEmpty()) {
                readMoves = true;
               System.out.println(stacks);
                continue;
            }
            int num = (line.length()+1)/4;
            if(!readMoves){
                for (int i = 1, j = 1 ; i <= num; i++, j+=4) {
                    char c = line.charAt(j);
                    if(!String.valueOf(c).matches("[\s0-9]")){
                        if(stacks.containsKey(i)){
                            stacks.get(i).addLast(c);
                        } else {
                            LinkedList<Character> list = new LinkedList<>();
                            list.addLast(c);
                            stacks.put(i, list);
                        }
                    }
                }
            } else {
                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(line);
                ArrayList<Integer> moves = new ArrayList<>();
                while(m.find()) {
                    moves.add(Integer.valueOf(m.group()));
                }
                for (int i = 0; i < moves.get(0); i++) {
                    stacks.get(moves.get(2)).addFirst(stacks.get(moves.get(1)).poll());
                }
//                System.out.println(stacks);

            }

        }
        System.out.println(stacks);
        StringBuilder result = new StringBuilder();
        for (var stack :
                stacks.entrySet())   {
           result.append(stack.getValue().peek());

        }
        System.out.println(result);


        System.out.println("Second part");

        readMoves = false;
        stacks = new HashMap<>();
        for (var line : lines) {
            if (line.isEmpty()) {
                readMoves = true;
                System.out.println(stacks);
                continue;
            }
            int num = (line.length()+1)/4;
            if(!readMoves){
                for (int i = 1, j = 1 ; i <= num; i++, j+=4) {
                    char c = line.charAt(j);
                    if(!String.valueOf(c).matches("[\s0-9]")){
                        if(stacks.containsKey(i)){
                            stacks.get(i).addLast(c);
                        } else {
                            LinkedList<Character> list = new LinkedList<>();
                            list.addLast(c);
                            stacks.put(i, list);
                        }
                    }
                }
            } else {
                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(line);
                ArrayList<Integer> moves = new ArrayList<>();
                while(m.find()) {
                    moves.add(Integer.valueOf(m.group()));
                }
                LinkedList<Character> buffer = new LinkedList<>();
                for (int i = 0; i < moves.get(0); i++) {
                    buffer.add(stacks.get(moves.get(1)).poll());

                }
                Iterator<Character> characterIterator = buffer.descendingIterator();
                while (characterIterator.hasNext()) {
                    stacks.get(moves.get(2)).addFirst(characterIterator.next());
                }

//                System.out.println(stacks);

            }

        }
        System.out.println(stacks);
        result = new StringBuilder();
        for (var stack :
                stacks.entrySet())   {
            result.append(stack.getValue().peek());

        }
        System.out.println(result);
    }

}
