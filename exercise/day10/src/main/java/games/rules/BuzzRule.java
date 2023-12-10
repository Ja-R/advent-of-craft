package games.rules;

public class BuzzRule implements FizzBuzzGameRule {
    @Override
    public boolean match(int number) {
        return this.isDivisibleBy(number, 5);
    }

    @Override
    public String convert() {
        return BUZZ;
    }

}
