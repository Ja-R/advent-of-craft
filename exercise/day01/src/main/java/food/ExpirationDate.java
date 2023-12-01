package food;

import java.time.LocalDate;
import java.util.function.Supplier;

public record ExpirationDate(LocalDate value) {

    public static ExpirationDate of(LocalDate value) {
        return new ExpirationDate(value);
    }

    public boolean isOutdated(Supplier<LocalDate> now) {
        return this.value.isAfter(now.get());
    }

}
