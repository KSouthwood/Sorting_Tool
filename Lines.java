package sorting;

public class Lines extends Data{
    Lines(Arguments arg) {
        super(arg);
    }

    void generate() {
        var input = readFromSource(false);

        if (isSorted) {
            mergeSort(input, 0, input.length);
            printResultsSorted(input, "lines");
        } else
        {
            printResultsByCount(mapValues(input), "lines");
        }
    }
}
