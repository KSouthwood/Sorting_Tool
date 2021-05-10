package sorting;

import java.util.ArrayList;

public class Longs extends Data {
    Longs(Mode mode) {
        super(mode);
    }

    private void mergeSort(Long[] array, int leftIncl, int rightExcl) {
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

    private void merge(Long[] array, int left, int middle, int right) {
        int indexLeft = left;   // index for the left sub-array
        int indexRight = middle; // index for the right sub-array
        int indexTemp = 0;      // index for the temp sub-array

        Long[] temp = new Long[right - left]; // temporary array for merging

    /* get the next lesser element from one of two sub-arrays
       and then insert it in the array until one of the sub-arrays is empty */
        while (indexLeft < middle && indexRight < right) {
            if (array[indexLeft] <= array[indexRight]) {
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

    void generate() {
        var input = readFromScanner();

        ArrayList<Long> temp = new ArrayList<>();
        for (var entry : input) {
            try {
                temp.add(Long.parseLong(entry));
            } catch (NumberFormatException e) {
                System.out.printf("\"%s\" is not a long. It will be skipped.%n", entry);
            }
        }
        var array = temp.toArray(new Long[0]);
        size = array.length;

        if (mode.isSorted()) {
            mergeSort(array, 0, array.length);
            printResultsSorted(array, "numbers");
        } else {
            var map = mapValues(array);
            printResultsByCount(map, "numbers");
        }
    }
}
