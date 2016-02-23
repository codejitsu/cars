package cars;

/**
 * Simple vehicle location for REST.
 */
public class SimpleLocation {
    private int space;
    private int level;

    public SimpleLocation() {
    }

    public SimpleLocation(final int level, final int space) {
        this.level = level;
        this.space = space;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleLocation that = (SimpleLocation) o;

        if (space != that.space) return false;
        return level == that.level;

    }

    @Override
    public int hashCode() {
        int result = space;
        result = 31 * result + level;
        return result;
    }
}
