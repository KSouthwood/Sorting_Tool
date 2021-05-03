package sorting;

public class Words extends StringData{
    Words(Mode mode) {
        super(mode);
    }

    void generate() {
        var map = mapValues(readFromScanner());
        var max = findMax(map.keySet());
        System.out.printf("Total words: %d%n", size);
        System.out.printf("The longest word: %s (%d time(s), %3d%%).%n",
                max, map.get(max), percentage(map.get(max), size));
    }
}
