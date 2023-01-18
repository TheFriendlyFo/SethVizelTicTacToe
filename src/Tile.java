public class Tile {
    private final int id;
    private Player owner;

    /**
     * Constructor; all Space objects start off with a blank symbol
     */
    public Tile(int id) {
        this.id = id;
        owner = null;
    }

    public String toString() {
        return isBlank() ? String.valueOf(id) : owner.toString();
    }

    public boolean isBlank() {
        return owner == null;
    }

    public boolean occupySpace(Player occupant) {
        if (isBlank()) {
            owner = occupant;
            return true;
        }
        return false;
    }

    public void emptySpace() {
        owner = null;
    }

    public int getOwnerId() {
        return isBlank() ? -1 : owner.id();
    }
}