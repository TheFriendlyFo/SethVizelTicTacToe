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

    /**
     * @return a string representation of the tile. If the tile has no owner, returns its ID cast
     * to a string, otherwise returns owner.toString()
     */
    public String toString() {
        return isBlank() ? String.valueOf(id) : owner.toString();
    }

    /**
     * @return if the tile has no owner
     */
    public boolean isBlank() {
        return owner == null;
    }

    /**
     * Attempts sets the tiles owner to occupant if the tile does not already have one
     * @param occupant the tiles new owner
     * @return whether the tile was successfully changed
     */
    public boolean occupySpace(Player occupant) {
        if (isBlank()) {
            owner = occupant;
            return true;
        }
        return false;
    }

    /**
     * sets the tile's owner to null
     */
    public void emptySpace() {
        owner = null;
    }

    /**
     * Attempts to return the tile's owner's ID, returning -1 if the tile has no owner
     * @return the tile's owner's ID
     */
    public int getOwnerId() {
        return isBlank() ? -1 : owner.id();
    }
}