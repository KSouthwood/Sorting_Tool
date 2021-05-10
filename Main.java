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
        String mode = "word";

        for (int index = 0; index < args.length; index++) {
            switch (args[index]) {
                case "-sortingType":
                    switch (index + 1 < args.length ? args[++index] : "") {
                        case "byCount":
                            sorted = false;
                            break;
                        case "natural":
                            sorted = true;
                            break;
                        case "-dataType":   // handle case where dataType follows sortingType without
                            index--;        // specifying a valid option
                        default:
                            System.out.println("No sorting type defined!");
                            break;
                    }
                    break;
                case "-dataType":
                    switch (index + 1 < args.length ? args[++index] : "") {
                        case "long":
                        case "line":
                        case "word":
                            mode = args[index];
                            break;
                        case "-sortingType":    // handle case where sortingType follows dataType without
                            index--;            // specifying a valid option
                        default:
                            System.out.println("No data type defined!");
                            break;
                    }
                    break;
                default:
                    System.out.printf("\"%s\" is not a valid parameter. It will be skipped.%n",
                            args[index]);
                    break;
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