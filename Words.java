package sorting;

public class Words extends Data{
    Words(Mode mode) {
        super(mode);
    }

    void generate() {
        var input = readFromScanner();

        if (mode.isSorted()) {
            mergeSort(input, 0, input.length);
            printResultsSorted(input, "words");
        } else {
            printResultsByCount(mapValues(input), "words");
        }
    }
}
