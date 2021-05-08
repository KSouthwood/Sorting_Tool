package sorting;

import java.util.*;
import java.util.stream.Collectors;

class Data {
    Mode mode;
    int size;

    public Data(Mode mode) {
        this.mode = mode;
    }

    static int percentage(int occurrences, int total) {
        return (int) (occurrences / (double) total * 100);
    }

    ArrayList<String> readFromScanner() {
        final Scanner scanner = new Scanner(System.in);

        String regex = mode.isSplit() ? "\\s+" : "";
        int limit = mode.isSplit() ? 0 : 1;

        ArrayList<String> inputs = new ArrayList<>();

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            Collections.addAll(inputs, input.split(regex, limit));
        }

        size = inputs.size();

        return inputs;
    }

    <T> Map<T, Integer> mapValues(ArrayList<T> input) {
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

        System.out.printf("Total %s: %d%n", output, size);
        for (var key : linkedHashMap.keySet()) {
            System.out.printf("%s: %d time(s), %3d%%%n",
                    key, map.get(key), percentage(map.get(key), size));
        }
    }

    <T> void printResultsSorted(T[] array, String output) {
        String separator = mode.isSplit() ? " " : "\n";
        System.out.printf("Total %s: %d%n", output, size);
        System.out.printf("Sorted data:%s", separator);
        for (var entry : array) {
            System.out.printf("%s%s", entry, separator);
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
