package games;

import games.rules.BuzzRule;
import games.rules.FizzBuzzGameRule;
import games.rules.FizzBuzzRule;
import games.rules.FizzRule;

import java.util.stream.Stream;

public class FizzBuzz {
    private static final int MIN = 0;
    private static final int MAX = 100;

    private FizzBuzz() {}

    public static String convert(int number) {
        validateNumber(number);
        return convertSafely(number);
    }

    private static void validateNumber(int number) {
        if (isOutOfRange(number)) {
            throw new OutOfRangeException();
        }
    }

    private static boolean isOutOfRange(int number) {
        return number <= MIN || number > MAX;
    }

    private static String convertSafely(int number) {
        return Stream.of(new FizzBuzzRule(), new FizzRule(), new BuzzRule())
                .filter(rule -> rule.match(number))
                .findFirst()
                .map(FizzBuzzGameRule::convert)
                .orElseGet(() -> String.valueOf(number));
    }
}
