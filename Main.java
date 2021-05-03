package sorting;

public class Main {

    public static void main(final String[] args) {
        var type = parseArgs(args);

        switch (type) {
            case LONGS:
                new Longs(type).generate();
                break;
            case WORDS:
                new Words(type).generate();
                break;
            case LINES:
                new Lines(type).generate();
                break;
            case SORT:
                new LongSort(type).generate();
            default:
                break;
        }
    }

    private static Mode parseArgs(String[] args) {
        Mode type = Mode.WORDS;

        for (int index = 0; index < args.length; index++) {
            if (args[index].equals("-sortIntegers")) {
                type = Mode.SORT;
                break;
            }

            if (args[index].equals("-dataType")) {
                index++;
                switch (args[index]) {
                    case "long":
                        type = Mode.LONGS;
                        break;
                    case "line":
                        type = Mode.LINES;
                        break;
                    case "-sortIntegers":
                        index--;
                        break;
                    default:
                        break;
                }
            }
        }

        return type;
    }
}