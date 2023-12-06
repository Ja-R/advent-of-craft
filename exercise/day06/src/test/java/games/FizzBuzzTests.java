package games;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FizzBuzzTests {

    @ParameterizedTest
    @ValueSource(ints = {1, 67, 82})
    void returns_the_given_number_when_number_is_not_fizz_buzz(int number) {
        assertThat(FizzBuzz.convert(number))
                .isEqualTo(String.valueOf(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 66, 99})
    void returns_fizz_for_multiple_of_3(int number) {
        assertThat(FizzBuzz.convert(number))
                .isEqualTo("Fizz");
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 50, 85})
    void returns_buzz_for_multiple_of_5(int number) {
        assertThat(FizzBuzz.convert(number))
                .isEqualTo("Buzz");
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 30, 45})
    void returns_fizzbuzz_for_multiple_of_15(int number) {
        assertThat(FizzBuzz.convert(number))
                .isEqualTo("FizzBuzz");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 101, -1})
    void fails_when_number_is_out_of_range(int number) {
        assertThatThrownBy(() -> FizzBuzz.convert(number))
                .isInstanceOf(OutOfRangeException.class);
    }

}
