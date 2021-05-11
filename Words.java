package sorting;

public class Words extends Data{
    Words(Arguments arg) {
        super(arg);
    }

    void generate() {
        var input = readFromSource(true);

        if (isSorted) {
            mergeSort(input, 0, input.length);
            printResultsSorted(input, "words");
        } else {
            printResultsByCount(mapValues(input), "words");
        }
    }
}
