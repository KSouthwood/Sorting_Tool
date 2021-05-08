package sorting;

public class Lines extends Data{
    Lines(Mode mode) {
        super(mode);
    }

    void generate() {
        var input = readFromScanner();

        if (mode.isSorted()) {
            var array = input.toArray(new String[0]);
            mergeSort(array, 0, array.length);
            printResultsSorted(array, "lines");
        } else
        {
            printResultsByCount(mapValues(input), "lines");
        }
    }
}
