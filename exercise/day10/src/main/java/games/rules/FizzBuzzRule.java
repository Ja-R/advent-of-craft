package games.rules;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class FizzBuzzRule implements FizzBuzzGameRule {
    @Override
    public Predicate<Integer> match() {
        return number -> this.isDivisibleBy(number, 15);
    }

    @Override
    public Supplier<String> convert() {
        return () -> FIZZ + BUZZ;
    }

}
