package sorting;

public class Main {

    public static void main(final String[] args) {
        var type = parseArgs(args);
        Data object;

        switch (type) {
            case LONG_COUNT:
            case LONG_SORT:
                object = new Longs(type);
                break;
            case LINE_COUNT:
            case LINE_SORT:
                object = new Lines(type);
                break;
            case WORD_SORT:
            case WORD_COUNT:
            default:
                object = new Words(type);
                break;
        }

        object.generate();
    }

    private static Mode parseArgs(String[] args) {
        // set the default state
        boolean sorted = true;
        String  mode   = "word";

        for (int index = 0; index < args.length; index++) {
            if (args[index].equals("-sortingType")) {
                switch (args[++index]) {
                    case "byCount":
                        sorted = false;
                        break;
                    case "-dataType":   // in case we have option but no argument
                        index--;        // decrement index for the loop and fall through
                    case "natural":     // to set the default mode
                    default:
                        sorted = true;
                        break;

                }
            }

            if (args[index].equals("-dataType")) {
                switch (args[++index]) {
                    case "long":
                    case "line":
                        mode = args[index];
                        break;
                    case "-sortingType":    // in case we have option but no argument
                        index--;            // decrement index for the loop and fall through
                    case "word":            // to set the default mode
                    default:
                        mode = "word";
                        break;
                }
            }
        }

        Mode type;

        switch (mode) {
            case "long":
                type = sorted ? Mode.LONG_SORT : Mode.LONG_COUNT;
                break;
            case "line":
                type = sorted ? Mode.LINE_SORT : Mode.LINE_COUNT;
                break;
            case "word":
            default:
                type = sorted ? Mode.WORD_SORT : Mode.WORD_COUNT;
                break;
        }

        return type;
    }
}