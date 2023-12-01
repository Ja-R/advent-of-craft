package food;

import java.util.UUID;

public record InspectorId(UUID id) {

    public static InspectorId generate() {
        return new InspectorId(UUID.randomUUID());
    }

}
