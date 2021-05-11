package sorting;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Arguments {
    private String dataType;    // set to the type of data we will process (default is "word")
    private boolean sortMode;   // true if we sort the data, false if we map it (default is true)
    private BufferedReader inputSource;
    private BufferedWriter outputSource;

    Arguments() {
        dataType = "word";
        sortMode = true;
        inputSource = new BufferedReader(new InputStreamReader(System.in));
        outputSource = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    String getDataType() {
        return dataType;
    }

    boolean isSortMode() {
        return sortMode;
    }

    BufferedReader getInputSource() {
        return inputSource;
    }

    BufferedWriter getOutputSource() {
        return outputSource;
    }

    void parseArguments(String[] args) {
        for (int index = 0; index < args.length; index++) {
            // gets the next argument on the command line or an empty string if we're past the end
            String nextArg = index + 1 < args.length ? args[index + 1] : "";
            switch (args[index]) {
                case "-sortingType":
                    if (setSortMode(nextArg)) {
                        index++;
                    }
                    break;
                case "-dataType":
                    if (setDataType(nextArg)) {
                        index++;
                    }
                    break;
                case "-inputFile":
                    if (setInputSource(nextArg)) {
                        index++;
                    }
                    break;
                case "-outputFile":
                    if (setOutputSource(nextArg)) {
                        index++;
                    }
                    break;
                default:
                    System.out.printf("\"%s\" is not a valid parameter. It will be skipped.%n",
                            args[index]);
                    break;
            }
        }
    }

    /**
     * Process the argument for the "-sortingType" option from the command line.
     *
     * @param argument the next item following the "-sortingType" option
     * @return false if the argument is another command line option
     */
    private boolean setSortMode(String argument) {
        boolean result = true;

        switch (argument) {
            case "byCount":
                sortMode = false;   // we'll map the data and count occurrences
                break;
            case "natural":
                sortMode = true;    // we'll sort the data into ascending/lexicographical order
                break;
            default:
                System.out.println("No sorting type defined!");
                if (!argument.isEmpty() && argument.startsWith("-")) {
                    result = false;
                }
                break;
        }

        return result;
    }

    /**
     * Process the argument for the "-dataType" option from the command line.
     *
     * @param argument the next item following the "-sortingType" option
     * @return false if the argument is another command line option
     */
    private boolean setDataType(String argument) {
        boolean result = true;

        switch (argument) {
            case "long":
            case "line":
            case "word":
                dataType = argument;
                break;
            default:
                System.out.println("No data type defined!");
                if (!argument.isEmpty() && argument.startsWith("-")) {
                    result = false;
                }
                break;
        }

        return result;
    }

    /**
     * Process the argument for the "-inputFile" option from the command line.
     *
     * @param argument the next item following the "-sortingType" option
     * @return false if the argument is another command line option
     */
    private boolean setInputSource(String argument) {
        boolean result = true;

        if (!argument.isEmpty() && argument.startsWith("-")) {
            result = false;
        } else {
            try {
                inputSource = Files.newBufferedReader(Path.of(argument));
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
        }

        return result;
    }

    /**
     * Process the argument for the "-outputFile" option from the command line.
     *
     * @param argument the next item following the "-sortingType" option
     * @return false if the argument is another command line option
     */
    private boolean setOutputSource(String argument) {
        boolean result = true;

        if (!argument.isEmpty() && argument.startsWith("-")) {
            result = false;
        } else {
            try {
                outputSource = Files.newBufferedWriter(Path.of(argument));
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
        }

        return result;
    }
}
