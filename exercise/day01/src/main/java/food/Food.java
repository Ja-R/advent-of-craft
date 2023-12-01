package food;

import java.time.LocalDate;
import java.util.function.Supplier;

public record Food(ExpirationDate expirationDate,
                   ApprovedForConsumption approvedForConsumption,
                   InspectorId inspectorId) {

    public boolean isEdible(Supplier<LocalDate> now) {
        return this.isFresh(now) &&
                this.isApprovedForConsumption() &&
                this.hasInspector();
    }

    private boolean isFresh(Supplier<LocalDate> now) {
        return this.expirationDate.isNotOutdated(now);
    }

    private boolean isApprovedForConsumption() {
        return this.approvedForConsumption.isApproved();
    }

    private boolean hasInspector() {
        return this.inspectorId != null;
    }

}
