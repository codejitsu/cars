package cars;

import java.util.UUID;

/*
 *  Vehicle id.
 */
public class VehicleId {
    private final String id;

    private VehicleId(final String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static VehicleId createNewId() {
        final UUID uuid = UUID.randomUUID();

        return new VehicleId(uuid.toString());
    }
}
