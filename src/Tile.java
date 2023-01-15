public class Tile {
    private final int id;
    // static variable
    // instance variable
    private String symbol;

    /**
     * Constructor; all Space objects start off with a blank symbol
     */
    public Tile(int id) {
        this.id = id;
        symbol = String.valueOf(id);
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isBlank() {
        return symbol.equals(String.valueOf(id));
    }

    /**
     * Changes the symbol on the space to symbolOfOccupant and returns true,
     * but ONLY if it is currently BLANK.
     *
     * @param symbolOfOccupant the new symbol for the space.
     * @return true if the space was successfully changed, return false otherwise
     */
    public boolean occupySpace(String symbolOfOccupant) {
        symbol = symbolOfOccupant;
        return true;
    }
}