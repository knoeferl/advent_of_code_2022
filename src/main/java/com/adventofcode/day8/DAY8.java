package com.adventofcode.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DAY8 {

    public static void main(String[] args) throws IOException {

        List<String> lines;
        boolean useExample = false;
        boolean verbose = true;

        if (!useExample) {
            lines = Files.readAllLines(Path.of("src/main/java/com/adventofcode/day8/input.txt"));
        } else {
            String example = """
                    30373
                    25512
                    65332
                    33549
                    35390""";
            lines = Arrays.stream(example.split("\n")).toList();
        }

        System.out.println("First part");
        List<List<Tree>> field = lines.stream().map(line -> Arrays.stream(line.split("")).map(v -> new Tree(Integer.parseInt(v))).toList()).toList();
        firstPart(field,verbose);

        System.out.println("Second part");
        secondPart(field,verbose);

    }



    private static void firstPart(List<List<Tree>> field,  boolean verbose) {

        for (int i = 0; i < field.size(); i++) {
            for (int j = 0; j < field.get(i).size(); j++) {
                check(field,i,j);
                System.out.print( field.get(i).get(j));
            }
            System.out.println();
        }

       var k =  field.stream().flatMap(i-> i.stream().map(j-> j.visible).filter(t-> t)).count();
        System.out.println(k);
    }

    private static void secondPart(List<List<Tree>> field,  boolean verbose) {

        for (int i = 0; i < field.size(); i++) {
            for (int j = 0; j < field.get(i).size(); j++) {
                checkScenic(field,i,j);
                System.out.print( field.get(i).get(j).scenic);
            }
            System.out.println();
        }

        var k =  field.stream().flatMap(i-> i.stream().map(j-> j.scenic)).max(Integer::compareTo).get();
        System.out.println(k);
    }

    private static void checkScenic(List<List<Tree>> field, int i, int j) {
        Tree tree = field.get(i).get(j);
        int height = tree.height;
        if(i == 0 || j == 0 || i == field.size()-1 || j == field.size()-1){
            return;
        }
        int scenic = 1;
        int count = 0;
        for (int k = i+1; k < field.size(); k++) {
            int height1 = field.get(k).get(j).height;
            count++;
            if(height1 >= height) {
                break;
            }
        }
        scenic *= count;
        count = 0;
        for (int k = j+1; k < field.get(0).size(); k++) {
            int height1 = field.get(i).get(k).height;
            count++;
            if(height1 >= height) {
                break;
            }
        }
        scenic *= count;
        count = 0;
        for (int k = j-1; k >= 0; k--) {
            int height1 = field.get(i).get(k).height;
            count++;
            if(height1 >= height) {
                break;
            }
        }
        scenic *= count;
        count = 0;

        for (int k = i-1; k >= 0; k--) {
            int height1 = field.get(k).get(j).height;
            count++;
            if(height1 >= height) {
                break;
            }
        }
        scenic *= count;
        tree.scenic = scenic;
    }


    static void check(List<List<Tree>> field, int i, int j){
        Tree tree = field.get(i).get(j);
        int height = tree.height;
        if(i == 0 || j == 0 || i == field.size()-1 || j == field.size()-1){
            tree.visible = true;
            return;
        }
        boolean visible = true;
        for (int k = i+1; k < field.size(); k++) {
            int height1 = field.get(k).get(j).height;
            if(height1 >= height) {
                visible = false;
                break;
            }
        }
        if(visible) {
            tree.visible = true;
            return;
        }
        visible = true;
        for (int k = j+1; k < field.get(0).size(); k++) {
            int height1 = field.get(i).get(k).height;
            if(height1 >= height) {
                visible = false;
                break;
            }
        }
        if(visible) {
            tree.visible = true;
            return;
        }
        visible = true;
        for (int k = j-1; k >= 0; k--) {
            int height1 = field.get(i).get(k).height;
            if(height1 >= height) {
                visible = false;
                break;
            }
        }
        if(visible) {
            tree.visible = true;
            return;
        }
        visible = true;
        for (int k = i-1; k >= 0; k--) {
            int height1 = field.get(k).get(j).height;
            if(height1 >= height) {
                visible = false;
                break;
            }
        }
        if(visible) {
            tree.visible = true;
            return;
        }
//        System.out.println("not visible");
    }

    static class Tree{
        int height;
        boolean visible;
        int scenic = 0;

        Tree(int height){
            this.height = height;
            visible = false;
        }

        @Override
        public String toString() {
            return "{" + visible + '}';
        }
    }



}
