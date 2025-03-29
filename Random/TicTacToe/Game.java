import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game {
    private final Board board;
    private final Player playerA;
    private final Player playerB;

    private int moveCount;
    private boolean gameOver;
    private Player currentPlayer;
    private final Scanner scanner = new Scanner(System.in);

    Game(Player playerA, Player playerB) {
        this.board = new Board();
        this.playerA = playerA;
        this.playerB = playerB;
        this.currentPlayer = playerA;
        this.moveCount = 0;
        this.gameOver = false;
    }

    Game(char playerSymbolA, char playerSymbolB) {
        this(new Player(playerSymbolA), new Player(playerSymbolB));
    }

    Game() {
        this('X', 'O');
    }

    public void reset() {
        board.clear();
        this.moveCount = 0;
        this.gameOver = false;
    }

    public void play() {
        if (gameOver) {
            System.out.println("Game Over");
            return;
        }

        try {
            System.out.println();
            board.draw(true);

            Move move = getMove();
            board.makeMove(move);

            Player winner = board.checkWinner();

            if (winner != null) {
                gameVictory(winner);
            } else {
                if (++moveCount >= Board.BOARD_SIZE * Board.BOARD_SIZE) {
                    gameDraw();
                }
                changePlayer();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input");
        } catch (NoSuchElementException e) {
            System.out.println("Invalid input");
            System.exit(-1);
        } catch (Exception e) {
            System.out.println("Unexpected error");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    public boolean gameOver() {
        return gameOver;
    }

    private void changePlayer() {
        if (currentPlayer == playerA)
            currentPlayer = playerB;
        else
            currentPlayer = playerA;
    }

    private Move getMove() {
        int x;
        int y;

        System.out.println("Player " + currentPlayer.getSymbol() + "'s move");

        System.out.print("x: ");
        while (!scanner.hasNextInt())
            scanner.next();
        x = scanner.nextInt();

        System.out.print("y: ");
        while (!scanner.hasNextInt())
            scanner.next();
        y = scanner.nextInt();

        return new Move(currentPlayer, x, y);
    }

    private void endGame() {
        System.out.println();
        board.draw(false);
        gameOver = true;
    }

    private void gameVictory(Player winner) {
        endGame();
        System.out.println("Player " + winner.getSymbol() + " won the game");
    }

    private void gameDraw() {
        endGame();
        System.out.println("Draw!");
    }
}