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

    public static VehicleId createNewId() {
        final UUID uuid = UUID.randomUUID();

        return new VehicleId(uuid.toString());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleId vehicleId = (VehicleId) o;

        return id != null ? id.equals(vehicleId.id) : vehicleId.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
