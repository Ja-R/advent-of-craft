package food;

import java.time.LocalDate;
import java.util.function.Supplier;

public record Food(ExpirationDate expirationDate,
                   ApprovedForConsumption approvedForConsumption,
                   InspectorId inspectorId) {

    public boolean isEdible(Supplier<LocalDate> now) {
        return this.isOutdated(now) &&
                this.isApprovedForConsumption() &&
                this.hasInspector();
    }

    private boolean isOutdated(Supplier<LocalDate> now) {
        return this.expirationDate.isOutdated(now);
    }

    private boolean isApprovedForConsumption() {
        return this.approvedForConsumption.isApproved();
    }

    private boolean hasInspector() {
        return this.inspectorId != null;
    }

}
