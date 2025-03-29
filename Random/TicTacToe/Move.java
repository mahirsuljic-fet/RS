public class Move {
    final private Player player;
    final private int x;
    final private int y;

    public Move(Player player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public Player getPlayer() {
        return player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
