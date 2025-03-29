import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        boolean playing = true;
        Scanner scanner = new Scanner(System.in);

        while (playing) {
            while (!game.gameOver())
                game.play();

            playing = false;
            System.out.print("\nNew game?\n(y - yes | n - no) : ");

            boolean validInput = false;

            while (!validInput && scanner.hasNextLine()) {
                switch (scanner.nextLine()) {
                    case "n", "N", "no", "NO":
                        validInput = true;
                        break;
                    case "y", "Y", "yes", "YES":
                        playing = true;
                        validInput = true;
                        game.reset();
                        break;
                }
            }
        }
    }
}