package sorting;

public enum Mode {
    LONG_COUNT("long", true, false),
    LONG_SORT("long", true, true),
    WORD_COUNT("word", true, false),
    WORD_SORT("word", true, true),
    LINE_COUNT("line", false, false),
    LINE_SORT("line", false, true);

    private final String mode;
    private final boolean split;    // do we split the input lines?
    private final boolean sorted;

    Mode(String mode, boolean split, boolean sorted) {
        this.mode = mode;
        this.split = split;
        this.sorted = sorted;
    }

    public String getMode() {
        return mode;
    }

    public boolean isSplit() {
        return split;
    }

    public boolean isSorted() {
        return sorted;
    }
}
