public class Board {
    final static int BOARD_SIZE = 3;
    final static char EMPTY = ' ';

    private final char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private Move lastMove;

    Board() {
        this.clear();
    }

    public void draw() {
        draw(false);
    }

    public void draw(boolean drawCoordinates) {
        if (drawCoordinates) {
            System.out.print("  "); // padding
            for (int i = 0; i < BOARD_SIZE; i++)
                System.out.printf(" %d", i);
            System.out.println();
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (drawCoordinates)
                System.out.printf("%d |", i);
            else
                System.out.print("|");

            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
    }

    public void makeMove(Move move) throws IllegalArgumentException {
        if (move.getX() >= BOARD_SIZE || move.getY() >= BOARD_SIZE || move.getX() < 0 || move.getY() < 0)
            throw new IllegalArgumentException("Invalid move");

        if (board[move.getY()][move.getX()] != EMPTY)
            throw new IllegalArgumentException("Field is already occupied");

        board[move.getY()][move.getX()] = move.getPlayer().getSymbol();
        lastMove = move;
    }

    public void makeMove(Player player, int x, int y) throws IllegalArgumentException {
        makeMove(new Move(player, x, y));
    }

    public void clear() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public Player checkWinner() {
        boolean victory;

        // ROW CHECK
        victory = true;
        for (int x = 0; x < BOARD_SIZE; x++) {
            if (board[lastMove.getY()][x] != lastMove.getPlayer().getSymbol()) {
                victory = false;
                break;
            }
        }

        // COLUMN CHECK
        if (!victory) {
            victory = true;
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (board[y][lastMove.getX()] != lastMove.getPlayer().getSymbol()) {
                    victory = false;
                    break;
                }
            }
        }

        // PRIMARY DIAGONAL CHECK
        if (!victory && lastMove.getX() == lastMove.getY()) {
            victory = true;
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i][i] != lastMove.getPlayer().getSymbol()) {
                    victory = false;
                    break;
                }
            }
        }

        // SECONDARY DIAGONAL CHECK
        if (!victory && lastMove.getY() == BOARD_SIZE - lastMove.getX() - 1) {
            victory = true;
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i][BOARD_SIZE - i - 1] != lastMove.getPlayer().getSymbol()) {
                    victory = false;
                    break;
                }
            }
        }

        if (victory)
            return lastMove.getPlayer();

        return null;
    }
}