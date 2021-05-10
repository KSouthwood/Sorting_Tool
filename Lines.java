package sorting;

public class Lines extends Data{
    Lines(Mode mode) {
        super(mode);
    }

    void generate() {
        var input = readFromScanner();

        if (mode.isSorted()) {
            mergeSort(input, 0, input.length);
            printResultsSorted(input, "lines");
        } else
        {
            printResultsByCount(mapValues(input), "lines");
        }
    }
}
