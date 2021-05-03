package sorting;

import java.util.Arrays;

public class LongSort extends Data {

    public LongSort(Mode mode) {
        super(mode);
    }

    private static void mergeSort(long[] array, int leftIncl, int rightExcl) {
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

    private static void merge(long[] array, int left, int middle, int right) {
        int indexLeft = left;   // index for the left sub-array
        int indexRight = middle; // index for the right sub-array
        int indexTemp = 0;      // index for the temp sub-array

        long[] temp = new long[right - left]; // temporary array for merging

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
        var array = readFromScanner().stream().mapToLong(Long::valueOf).toArray();
        mergeSort(array, 0, array.length);
        System.out.printf("Total numbers: %d%n", size);
        System.out.printf("Sorted data: %s%n",
                Arrays.toString(array).replaceAll(",", "")
                        .replaceAll("\\[", "")
                        .replaceAll("]", ""));

    }
}
