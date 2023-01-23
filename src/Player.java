public record Player(int ID, boolean isAI) {
    private static final String[] symbols = {"31mX ", "34mO ", "32m! ", "33m$ ", "36m@ ", "35m* ", "31mC ", "34m#C ", "32mC "};
    private static final String COLOR_SEQUENCE = (char) 27 + "[";

    public String toString() {
        return COLOR_SEQUENCE + symbols[isAI ? ID + 6 : ID] + COLOR_SEQUENCE + "39m";
    }
}