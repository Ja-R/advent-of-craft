package games;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class FizzBuzz {
    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int FIZZ = 3;
    private static final int BUZZ = 5;
    private static final int FIZZBUZZ = 15;

    private static final Map<Predicate<Integer>, String> mapping;

    static {
        mapping = new LinkedHashMap<>();
        mapping.put(i -> is(FIZZBUZZ, i), "FizzBuzz");
        mapping.put(i -> is(FIZZ, i), "Fizz");
        mapping.put(i -> is(BUZZ, i), "Buzz");
    }

    public static Optional<String> convert(Integer input) {
        return Optional.ofNullable(input)
                .filter(FizzBuzz::isNotOutOfRange)
                .map(FizzBuzz::convertSafely);
    }

    private static String convertSafely(Integer input) {
        return mapping.entrySet()
                .stream()
                .filter(f -> f.getKey().test(input))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElseGet(input::toString);
    }

    private static boolean is(Integer divisor, Integer input) {
        return input % divisor == 0;
    }

    private static boolean isNotOutOfRange(Integer input) {
        return !(input <= MIN || input > MAX);
    }
}
