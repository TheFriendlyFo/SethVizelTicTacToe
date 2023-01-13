public class Tile {
    // static variable
    public static final String BLANK = " ";

    // instance variable
    private String symbol;
    private final int id;

    /**
     * Constructor; all Space objects start off with a blank symbol
     */
    public Tile(int id) {
        this.id = id;
        symbol = BLANK;
    }

    public String getSymbol() {
        return symbol.equals(BLANK) ? String.valueOf(id) : symbol;
    }

    /**
     * Changes the symbol on the space to symbolOfOccupant and returns true,
     * but ONLY if it is currently BLANK.
     *
     * @param symbolOfOccupant the new symbol for the space.
     * @return true if the space was successfully changed, return false otherwise
     */
    public boolean occupySpace(String symbolOfOccupant) {
        if (symbol.equals(BLANK)) {
            symbol = symbolOfOccupant;
            return true;
        }
        return false;
    }
}