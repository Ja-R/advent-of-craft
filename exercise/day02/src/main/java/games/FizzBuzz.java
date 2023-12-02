package games;

public class FizzBuzz {

    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";
    private static final int FIZZ_NUMBER = 3;
    private static final int BUZZ_NUMBER = 5;
    private static final int MAX_RANGE = 100;
    private static final int MIN_RANGE = 0;

    private FizzBuzz() {
    }

    public static String convert(Integer number) throws OutOfRangeException {
        if (isOutOfRange(number)) {
            throw new OutOfRangeException();
        }

        if (isFizzBuzz(number)) {
            return FIZZ + BUZZ;
        }

        if (isFizz(number)) {
            return FIZZ;
        }

        if (isBuzz(number)) {
            return BUZZ;
        }

        return number.toString();
    }

    private static boolean isOutOfRange(Integer number) {
        return number <= MIN_RANGE || number > MAX_RANGE;
    }

    private static boolean isFizzBuzz(Integer number) {
        return isFizz(number) && isBuzz(number);
    }

    private static boolean isFizz(Integer number) {
        return isDivisibleBy(number, FIZZ_NUMBER);
    }

    private static boolean isBuzz(Integer number) {
        return isDivisibleBy(number, BUZZ_NUMBER);
    }

    private static boolean isDivisibleBy(Integer number, int divisor) {
        return number % divisor == 0;
    }

}
