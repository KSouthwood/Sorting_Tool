package sorting;

import java.util.Set;

public class Longs extends LongData{
    Longs(Mode mode) {
        super(mode);
    }

    Long findMax(Set<Long> keys) {
        Long max = Long.MIN_VALUE;
        for (var key : keys) {
            if (key > max) {
                max = key;
            }
        }
        return max;
    }

    void generate() {
        var map = mapValues(readFromScanner());
        var max = findMax(map.keySet());
        System.out.printf("Total numbers: %d%n", size);
        System.out.printf("The greatest number: %s (%d time(s), %3d%%).%n",
                max, map.get(max), percentage(map.get(max), size));
    }
}
