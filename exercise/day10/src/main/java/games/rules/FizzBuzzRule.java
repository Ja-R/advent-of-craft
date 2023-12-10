package games.rules;

public class FizzBuzzRule implements FizzBuzzGameRule {
    @Override
    public boolean match(int number) {
        return this.isDivisibleBy(number, 15);
    }

    @Override
    public String convert() {
        return FIZZ + BUZZ;
    }

}
