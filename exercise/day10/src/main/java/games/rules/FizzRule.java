package games.rules;

public class FizzRule implements FizzBuzzGameRule {
    @Override
    public boolean match(int number) {
        return this.isDivisibleBy(number, 3);
    }

    @Override
    public String convert() {
        return FIZZ;
    }

}
