package sorting;

public class Words extends Data{
    Words(Mode mode) {
        super(mode);
    }

    void generate() {
        var input = readFromScanner();

        if (mode.isSorted()) {
            String[] array = new String[0];
            input.toArray(array);
            mergeSort(array, 0, array.length);
            printResultsSorted(array, "words");
        } else {
            printResultsByCount(mapValues(input), "words");
        }
    }
}
