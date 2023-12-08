package password;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PasswordTest {

    @Test
    void is_invalid_when_does_not_contains_at_least_one_number() {
        Assertions.assertThatThrownBy(() -> new Password("Abcde.g"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void is_invalid_when_has_less_than_8_characters() {
        Assertions.assertThatThrownBy(() -> new Password("Ab345.7"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void is_invalid_when_does_not_contains_at_least_one_capital_letter() {
        Assertions.assertThatThrownBy(() -> new Password("ab3456.8"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void is_invalid_when_does_not_contains_at_least_one_lowercase_letter() {
        Assertions.assertThatThrownBy(() -> new Password("AB3456.8"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ab3456,8", "Ab3456?8"})
    void is_invalid_when_does_not_contains_at_least_one_specific_special_character(String valueWithInvalidSpecialCharacter) {
        Assertions.assertThatThrownBy(() -> new Password(valueWithInvalidSpecialCharacter))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Ab3456.8", "Ab3456*8", "Ab3456#8" , "Ab3456@8", "Ab3456$8", "Ab3456%8", "Ab3456&8"
    })
    void is_valid_when_value_filled_all_criteria(String validValue) {
        Password password = new Password(validValue);

        Assertions.assertThat(password.value())
                .isEqualTo(validValue);
    }
}
