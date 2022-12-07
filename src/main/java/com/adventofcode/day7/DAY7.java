package com.adventofcode.day7;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class DAY7 {

    public static void main(String[] args) throws IOException {

        List<String> lines;
        boolean useExample = false;
        boolean verbose = true;

        if (!useExample) {
            lines = Files.readAllLines(Path.of("src/main/java/com/adventofcode/day7/input.txt"));
        } else {
            String example = """
                    $ cd /
                    $ ls
                    dir a
                    14848514 b.txt
                    8504156 c.dat
                    dir d
                    $ cd a
                    $ ls
                    dir e
                    29116 f
                    2557 g
                    62596 h.lst
                    $ cd e
                    $ ls
                    584 i
                    $ cd ..
                    $ cd ..
                    $ cd d
                    $ ls
                    4060174 j
                    8033020 d.log
                    5626152 d.ext
                    7214296 k""";
            lines = Arrays.stream(example.split("\n")).toList();
        }

        System.out.println("First part");

        Dir parent = createStructure(lines, 4, verbose);
        System.out.println(parent);
        Set<Objects> allDirs = getAll(parent.content);
        System.out.println(allDirs.stream().map(Objects::getSize).filter(size -> size.compareTo(new BigInteger("100000"))<0).reduce(BigInteger::add));
        System.out.println("Second part");
        BigInteger totalSpace = new BigInteger("70000000");
        BigInteger neededSpace = new BigInteger("30000000");
        BigInteger usedSpace = parent.getSize();
        BigInteger freeSpace = totalSpace.subtract(usedSpace);
        BigInteger toFreeSpace = neededSpace.subtract(freeSpace);
        System.out.println("usedSpace= " + usedSpace + " toFreeSpace = " + toFreeSpace + " freeSpace = " + freeSpace);
        System.out.println(allDirs.stream().map(Objects::getSize).filter(size->size.compareTo(toFreeSpace)>=0).sorted().findFirst().get());



    }



    private static Dir createStructure(List<String> lines, int length, boolean verbose) {

        Dir current = new Dir("/", null);
        Dir parent = current;
        for (var line : lines) {
            if (line.isEmpty()) {
                continue;
            }
            if (line.startsWith("$ cd")) {
                if(line.contains("$ cd /")) continue;
                String goTo = line.substring(5);
                if(goTo.equals("..")){
                    current = current.getParent();
                } else {
                    current = (Dir) current.content.stream().filter(u-> u instanceof Dir && u.getName().equals(goTo)).findFirst().get();
                }

            }else if (line.startsWith("$ ls")){
                continue;

            } else if (line.startsWith("dir")) {
                String name = line.substring(4);
                current.content.add(new Dir(name, current));
            } else {
                var split = line.split(" ");
                current.content.add(new File( split[1],  current, new BigInteger(split[0])));
            }
        }
        return parent;


    }

    static Set<Objects> getAll(Set<Objects> set){
        Set<Objects> collect = set.stream().filter(o -> o instanceof Dir).collect(Collectors.toSet());
        Set<Objects> result = new HashSet<>();
        for (Objects o : collect) {
            result.add(o);
            result.addAll(getAll(((Dir) o).content));
        }
        return result;
    }

    static interface Objects {
        BigInteger getSize();

        String getName();
        Dir getParent();
    }

    static class Dir implements  Objects{

        public Set<Objects> content;
        public String name;
        public Dir parent;

        Dir(String name, Dir parent){
            this.name = name;
            content = new HashSet<>();
            this.parent = parent;
        }

        public BigInteger getSize(){
            return content.stream().map(Objects::getSize).reduce(BigInteger::add).orElse(BigInteger.ZERO);
        }
        public String getName() {
            return name;
        }

        @Override
        public Dir getParent() {
            return parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Dir dir = (Dir) o;
            return name.equals(dir.name) && java.util.Objects.equals(parent, dir.parent);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(name, parent);
        }

        @Override
        public String toString() {
            return "\nDir{" +
                    "name='" + name + '\'' +
                    ",size= " + getSize() +
                    ",\ncontent=" + content +
                    '}';
        }
    }

    static class File implements Objects{
        public BigInteger size;
        public String name;
        public Dir parent;

        File(String name, Dir parent, BigInteger size){
            this.size = size;
            this.name = name;
            this.parent = parent;
        }

        public BigInteger getSize(){
            return size;
        }
        public String getName() {
            return name;
        }

        @Override
        public Dir getParent() {
            return parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            File file = (File) o;
            return size.equals(file.size) && name.equals(file.name) && parent.equals(file.parent);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(size, name, parent);
        }

        @Override
        public String toString() {
            return "File{" +
                    "name='" + name + '\'' +
                    ", size=" + size +
                    '}';
        }
    }

}
