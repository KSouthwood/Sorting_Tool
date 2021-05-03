package sorting;

public enum Mode {
    LONGS("long", true, true),
    WORDS("word", true, false),
    LINES("line", false, false),
    SORT("sort", true, true);

    private final String mode;
    private final boolean split;    // do we split the input lines?
    private final boolean numbers;

    Mode(String mode, boolean split, boolean numbers) {
        this.mode = mode;
        this.split = split;
        this.numbers = numbers;
    }

    public String getMode() {
        return mode;
    }

    public boolean isSplit() {
        return split;
    }

    public boolean isNumbers() {
        return numbers;
    }
}
