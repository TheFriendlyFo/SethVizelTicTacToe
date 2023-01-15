public class Player {
    private static final String[] symbols = {"31mX ", "34mO ", "32m! ", "33m$ ", "36m@ ", "35m# "};
    private static final String COLOR_SEQUENCE = (char) 27 + "[";
    private final String symbol;

    public Player(int id) {
        this.symbol = COLOR_SEQUENCE + symbols[id] + COLOR_SEQUENCE + "39m";
    }

    public String getSymbol() {
        return symbol;
    }
}