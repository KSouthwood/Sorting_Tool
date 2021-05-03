package sorting;

public class Lines extends StringData{
    Lines(Mode mode) {
        super(mode);
    }

    void generate() {
        var map = mapValues(readFromScanner());
        var max = findMax(map.keySet());
        System.out.printf("Total lines: %d%n", size);
        System.out.printf("The longest line:%n%s%n(%d time(s), %3d%%).%n",
                max, map.get(max), percentage(map.get(max), size));
    }
}