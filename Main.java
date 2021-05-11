package sorting;

public class Main {

    public static void main(final String[] args) {
        Arguments type = new Arguments();
        type.parseArguments(args);
        Data object;

        // getDataType() guaranteed to be one of three, so no default needed
        switch (type.getDataType()) {
            case "long":
                object = new Longs(type);
                break;
            case "line":
                object = new Lines(type);
                break;
            case "word":
            default:
                object = new Words(type);
                break;
        }

        object.generate();
    }
}