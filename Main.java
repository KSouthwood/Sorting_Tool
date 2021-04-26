package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) {
        String type = parseArgs(args);
        var input = readFromScanner(type);
        var map = mapValues(input);
        var largest = type.equals("long") ? findMaximum(map.keySet()) : findLongest(map.keySet());
        String totalLine = "";
        String countLine = "";

        switch (type) {
            case "long":
                totalLine = "Total numbers: %d%n";
                countLine = "The greatest number: %s (%d time(s), %3d%%).%n";
                break;
            case "word":
                totalLine = "Total words: %d%n";
                countLine = "The longest word: %s (%d time(s), %3d%%).%n";
                break;
            case "line":
                totalLine = "Total lines: %d%n";
                countLine = "The longest line:%n%s%n(%d time(s), %3d%%).%n";
                break;
            default:
                break;
        }

        System.out.printf(totalLine, input.size());
        System.out.printf(countLine, largest, map.get(largest), percentage(map.get(largest), input.size()));
    }

    private static String parseArgs(String[] args) {
        String type = "word";

        if (args.length != 0) {
            if (args.length == 2 && args[0].equals("-dataType")) {
                // change value only if we get one of the other types
                switch (args[1]) {
                    case "long":
                        type = "long";
                        break;
                    case "line":
                        type = "line";
                        break;
                    default:
                        break;
                }
            }
        }

        return type;
    }

    private static int percentage(int occurrences, int total) {
        return (int) (occurrences / (double) total * 100);
    }

    private static ArrayList<String> readFromScanner(String type) {
        String regex = "\\s+";
        int limit = 0;

        if (type.equals("line")) {
            regex = "";
            limit = 1;
        }

        ArrayList<String> inputs = new ArrayList<>();

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            Collections.addAll(inputs, input.split(regex, limit));
        }

        return inputs;
    }

    private static HashMap<String, Integer> mapValues(ArrayList<String> input) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (var in : input) {
            hashMap.put(in, hashMap.getOrDefault(in, 0) + 1);
        }
        return hashMap;
    }

    private static String findLongest(Set<String> keys) {
        String longest = "";
        for (var in : keys) {
            if (in.length() > longest.length() ||
                    (in.length() == longest.length() && in.compareTo(longest) > 0)) {
                longest = in;
            }
        }
        return longest;
    }

    private static String findMaximum(Set<String> set) {
        long max = Long.MIN_VALUE;
        String key = "";

        for (var in : set) {
            long number = Long.parseLong(in);
            if (number > max) {
                max = number;
                key = in;
            }
        }
        return key;
    }
}