package sorting;

import java.util.*;

class Data {
    Mode mode;
    int size;

    public Data(Mode mode) {
        this.mode = mode;
    }

    static int percentage(int occurrences, int total) {
        return (int) (occurrences / (double) total * 100);
    }

    ArrayList<String> readFromScanner() {
        final Scanner scanner = new Scanner(System.in);

        String regex = mode.isSplit() ? "\\s+" : "";
        int limit = mode.isSplit() ? 0 : 1;

        ArrayList<String> inputs = new ArrayList<>();

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            Collections.addAll(inputs, input.split(regex, limit));
        }

        size = inputs.size();

        return inputs;
    }
}

class StringData extends Data {
    public StringData(Mode mode) {
        super(mode);
    }

    HashMap<String, Integer> mapValues(ArrayList<String> input) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (var in : input) {
            hashMap.put(in, hashMap.getOrDefault(in, 0) + 1);
        }
        return hashMap;
    }

    String findMax(Set<String> keys) {
        String max = "";
        for (var key : keys) {
            if (key.length() > max.length() ||
                    (key.length() == max.length() && key.compareTo(max) > 0)) {
                max = key;
            }
        }
        return max;
    }

}

class LongData extends Data {
    public LongData(Mode mode) {
        super(mode);
    }

    HashMap<Long, Integer> mapValues(ArrayList<String> input) {
        HashMap<Long, Integer> hashMap = new HashMap<>();
        for (var in : input) {
            var key = Long.valueOf(in);
            hashMap.put(key, hashMap.getOrDefault(key, 0) + 1);
        }
        return hashMap;
    }
}
