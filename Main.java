package sorting;

import java.util.*;

public class Main {
    private static final HashMap<Long, Integer> numbers = new HashMap<>();
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        long max = Long.MIN_VALUE;
        int count = 0;

        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            count++;
            if (number > max) {
                max = number;
            }
            numbers.put(number, numbers.getOrDefault(number, 0) + 1);
        }

        System.out.printf("Total numbers: %d%n", count);
        System.out.printf("The greatest number: %d (%d time(s)).%n",
                max, numbers.get(max));
    }
}
