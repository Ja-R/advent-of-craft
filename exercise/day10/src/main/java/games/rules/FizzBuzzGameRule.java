package games.rules;

import java.util.function.Predicate;
import java.util.function.Supplier;

public interface FizzBuzzGameRule {
    String FIZZ = "Fizz";
    String BUZZ = "Buzz";

    Predicate<Integer> match();

    Supplier<String> convert();

    default boolean isDivisibleBy(int number, int divisor) {
        return number % divisor == 0;
    }
}
