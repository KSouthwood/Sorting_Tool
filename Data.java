package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

class Data {
    int size;
    String dataType;
    boolean isSorted;
    BufferedReader inputSource;
    BufferedWriter outputSource;

    public Data(Arguments arg) {
        this.dataType = arg.getDataType();
        this.isSorted = arg.isSortMode();
        this.inputSource = arg.getInputSource();
        this.outputSource = arg.getOutputSource();
    }

    static int percentage(int occurrences, int total) {
        return (int) (occurrences / (double) total * 100);
    }

    String[] readFromSource(boolean isSplit) {
        List<String> array = new ArrayList<>();
        String[] input = inputSource.lines().toArray(String[]::new);

        if (isSplit) {
            for (var line : input) {
                Collections.addAll(array, line.split("\\s+"));
            }
        }

        size = isSplit ? array.size() : input.length;

        return isSplit ? array.toArray(new String[0]) : input;
    }

    <T> Map<T, Integer> mapValues(T[] input) {
        Map<T, Integer> hashMap = new TreeMap<>();
        for (var entry : input) {
            hashMap.put(entry, hashMap.getOrDefault(entry, 0) + 1);
        }
        return hashMap;
    }

    /**
     * Override in sub-classes
     */
    void generate() {

    }

    <T> void printResultsByCount(Map<T, Integer> map, String output) {
        Map<T, Integer> linkedHashMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        try {
            outputSource.write(String.format("Total %s: %d%n", output, size));
            for (var key : linkedHashMap.keySet()) {
                outputSource.write(String.format("%s: %d time(s), %3d%%%n",
                        key, map.get(key), percentage(map.get(key), size)));
            }
            outputSource.flush();
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }

    <T> void printResultsSorted(T[] array, String output) {
        String separator = dataType.equals("line") ? "\n" : " ";
        try {
            outputSource.write(String.format("Total %s: %d%n", output, size));
            outputSource.write(String.format("Sorted data:%s", separator));
            for (var entry : array) {
                outputSource.write(String.format("%s%s", entry, separator));
            }
            outputSource.flush();
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
    }

    void mergeSort(String[] array, int leftIncl, int rightExcl) {
        // the base case: if sub-array contains <= 1 items, stop dividing because it's sorted
        if (rightExcl <= leftIncl + 1) {
            return;
        }

        /* divide: calculate the index of the middle element */
        int middle = leftIncl + (rightExcl - leftIncl) / 2;

        mergeSort(array, leftIncl, middle);  // conquer: sort the left sub-array
        mergeSort(array, middle, rightExcl); // conquer: sort the right sub-array

        /* combine: merge both sorted sub-arrays into sorted one */
        merge(array, leftIncl, middle, rightExcl);
    }

    private void merge(String[] array, int left, int middle, int right) {
        int indexLeft = left;   // index for the left sub-array
        int indexRight = middle; // index for the right sub-array
        int indexTemp = 0;      // index for the temp sub-array

        String[] temp = new String[right - left]; // temporary array for merging

    /* get the next lesser element from one of two sub-arrays
       and then insert it in the array until one of the sub-arrays is empty */
        while (indexLeft < middle && indexRight < right) {
            if (array[indexLeft].compareTo(array[indexRight]) <= 0) {
                temp[indexTemp] = array[indexLeft];
                indexLeft++;
            } else {
                temp[indexTemp] = array[indexRight];
                indexRight++;
            }
            indexTemp++;
        }

        /* insert all the remaining elements of the left sub-array in the array */
        for (; indexLeft < middle; indexLeft++, indexTemp++) {
            temp[indexTemp] = array[indexLeft];
        }

        /* insert all the remaining elements of the right sub-array in the array */
        for (; indexRight < right; indexRight++, indexTemp++) {
            temp[indexTemp] = array[indexRight];
        }

        /* effective copying elements from temp to array */
        System.arraycopy(temp, 0, array, left, temp.length);
    }

}
