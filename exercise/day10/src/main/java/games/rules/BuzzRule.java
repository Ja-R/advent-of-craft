package games.rules;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class BuzzRule implements FizzBuzzGameRule {
    @Override
    public Predicate<Integer> match() {
        return number -> this.isDivisibleBy(number, 5);
    }

    @Override
    public Supplier<String> convert() {
        return () -> BUZZ;
    }

}
