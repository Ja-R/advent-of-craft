import food.ApprovedForConsumption;
import food.ExpirationDate;
import food.Food;
import food.InspectorId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;

class EdibleTests {
    private static final LocalDate EXPIRATION_DATE_VALUE = of(2023, 12, 1);
    private static final ExpirationDate EXPIRATION_DATE = ExpirationDate.of(EXPIRATION_DATE_VALUE);
    private static final InspectorId INSPECTOR = InspectorId.generate();
    private static final LocalDate NOT_FRESH_DATE = EXPIRATION_DATE_VALUE.plusDays(7);
    private static final LocalDate FRESH_DATE = EXPIRATION_DATE_VALUE.minusDays(7);
    private static final ApprovedForConsumption APPROVED_FOR_CONSUMPTION = ApprovedForConsumption.approved();
    private static final ApprovedForConsumption NOT_APPROVED_FOR_CONSUMPTION = ApprovedForConsumption.notApproved();

    private static Stream<Arguments> notEdibleFood() {
        return Stream.of(
                Arguments.of(APPROVED_FOR_CONSUMPTION, INSPECTOR, NOT_FRESH_DATE),
                Arguments.of(NOT_APPROVED_FOR_CONSUMPTION, INSPECTOR, FRESH_DATE),
                Arguments.of(APPROVED_FOR_CONSUMPTION, null, FRESH_DATE),
                Arguments.of(NOT_APPROVED_FOR_CONSUMPTION, null, NOT_FRESH_DATE),
                Arguments.of(NOT_APPROVED_FOR_CONSUMPTION, null, FRESH_DATE)
        );
    }

    @ParameterizedTest
    @MethodSource("notEdibleFood")
    void food_is_not_edible(ApprovedForConsumption approvedForConsumption, InspectorId inspectorId, LocalDate now) {
        var food = new Food(
                EXPIRATION_DATE,
                approvedForConsumption,
                inspectorId
        );

        assertThat(food.isEdible(() -> now)).isFalse();
    }

    @Test
    void food_is_edible() {
        var food = new Food(
                EXPIRATION_DATE,
                APPROVED_FOR_CONSUMPTION,
                INSPECTOR
        );

        assertThat(food.isEdible(() -> FRESH_DATE)).isTrue();
    }
}
