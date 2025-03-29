public class Player {
    private final char symbol;

    Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Player && ((Player) obj).symbol == symbol;
    }
}
