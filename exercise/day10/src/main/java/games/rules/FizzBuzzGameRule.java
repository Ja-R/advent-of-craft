package games.rules;

public interface FizzBuzzGameRule {
    String FIZZ = "Fizz";
    String BUZZ = "Buzz";

    boolean match(int number);

    String convert();

    default boolean isDivisibleBy(int number, int divisor) {
        return number % divisor == 0;
    }
}
