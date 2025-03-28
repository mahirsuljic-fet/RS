import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    char[][] board = {
        { ' ', ' ', ' ' },
        { ' ', ' ', ' ' },
        { ' ', ' ', ' ' }
    };

    Scanner scanner = new Scanner(System.in);
    int x, y;
    boolean player = true; // false - o ; true - x

    while (true) {
      char currentChar = player ? 'x' : 'o';

      for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 3; ++j)
          System.out.printf("|%c", board[i][j]);
        System.out.println("|");
      }

      System.out.printf("\nPlayer %c\n", currentChar);
      System.out.print("Input x coord: ");
      x = scanner.nextInt();
      System.out.print("Input y coord: ");
      y = scanner.nextInt();

      if (x < 0 || x > 2 || y < 0 || y > 2) {
        System.out.println("\nInvalid coordinate!");
        continue;
      }

      if (board[y][x] != ' ') {
        System.out.println("\nSpace occupied!");
        continue;
      }

      board[y][x] = currentChar;

      boolean victory;

      // row check
      victory = true;
      for (int i = 0; i < 3; ++i) {
        if (board[y][i] != currentChar) {
          victory = false;
          break;
        }
      }

      // column check
      if (!victory) {
        victory = true;
        for (int i = 0; i < 3; ++i) {
          if (board[i][x] != currentChar) {
            victory = false;
            break;
          }
        }
      }

      // primary diagonal check
      if (!victory) {
        victory = true;
        for (int i = 0; i < 3; ++i) {
          if (board[i][i] != currentChar) {
            victory = false;
            break;
          }
        }
      }

      // secondary diagonal check
      if (!victory) {
        victory = true;
        for (int i = 0; i < 3; ++i) {
          if (board[i][3 - i - 1] != currentChar) {
            victory = false;
            break;
          }
        }
      }

      if (victory) {
        System.out.printf("Player %c won\n", currentChar);
        break;
      }

      player = !player;
    }

    scanner.close();
  }
}
