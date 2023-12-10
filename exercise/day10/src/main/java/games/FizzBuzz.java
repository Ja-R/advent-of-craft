package games;

import games.rules.BuzzRule;
import games.rules.FizzBuzzRule;
import games.rules.FizzRule;

import java.util.stream.Stream;

public class FizzBuzz {
    private static final int MIN = 0;
    private static final int MAX = 100;

    private FizzBuzz() {}

    public static String convert(int input) {
        validateNumber(input);
        return convertSafely(input);
    }

    private static void validateNumber(int input) {
        if (isOutOfRange(input)) {
            throw new OutOfRangeException();
        }
    }

    private static boolean isOutOfRange(int input) {
        return input <= MIN || input > MAX;
    }

    private static String convertSafely(int input) {
        return Stream.of(new FizzBuzzRule(), new FizzRule(), new BuzzRule())
                .filter(rule -> rule.match().test(input))
                .findFirst()
                .map(rule -> rule.convert().get())
                .orElseGet(() -> String.valueOf(input));
    }
}
