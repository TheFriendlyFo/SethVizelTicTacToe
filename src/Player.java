public record Player(int id, boolean isAI) {
    private static final String[] symbols = {"31mX ", "34mO ", "32m! ", "33m$ ", "36m@ ", "35m* ", "31m# ", "34m# ", "32m# "};
    private static final String COLOR_SEQUENCE = (char) 27 + "[";

    public String toString() {
        return COLOR_SEQUENCE + symbols[isAI ? id + 6 : id] + COLOR_SEQUENCE + "39m";
    }
}