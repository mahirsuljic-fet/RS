// Nista puno vezano za predmet, ali sam se malo igrao sa patternima
// da se naviknem na Java sintaksu i specificnosti jezika

import java.util.Scanner;

public class Pattern {
  private static final String star = "* ";
  private static final String gap = "  ";
  private static final int N_MIN = 3;

  private static void pattern1(int n) {
    for (int row = 0; row < n; ++row) {
      for (int col = 0; col <= row; ++col)
        System.out.print(star);
      System.out.println();
    }
  }

  private static void pattern2(int n) {
    for (int row = n; row > 0; --row) {
      for (int col = 0; col < row; ++col)
        System.out.print(star);
      System.out.println();
    }
  }

  private static void pattern3(int n) {
    for (int row = 0; row < n; ++row) {
      for (int col = row; col < n + row; ++col)
        System.out.print(col % 2 == 0 ? star : gap);
      System.out.println();
    }
  }

  private static void pattern4(int n) {
    final char gap = ' ';
    final char mdiag = '\\';
    final char rdiag = '/';
    final char center = 'X';
    final char corner = '+';
    final char hborder = '-';
    final char vborder = '|';
    final char intersect = '*';

    for (int row = 0; row < n; ++row) {
      boolean left = true;

      for (int col = 0; col < n; ++col) {
        if (row == 0 || row == n - 1) {
          if (col == 0 || (col == n - 1 && !left))
            System.out.print(corner);
          else if (col == n - 1 && left)
            System.out.print(intersect);
          else
            System.out.print(hborder);
        } else if (col == 0 || col == n - 1)
          if (row == 0 || row == n - 1)
            System.out.print(corner);
          else
            System.out.print(vborder);
        else if (row == col)
          if (row == n / 2 && n % 2 != 0)
            System.out.print(center);
          else
            System.out.print(mdiag);
        else if (row == n - col - 1)
          System.out.print(rdiag);
        else
          System.out.print(gap);

        if (left && col == n - 1) {
          left = false;
          col = 0;
        }
      }
      System.out.println();
    }
  }

  private static void pattern5(int n) {
    int box = 0;
    int limit = n % 2 == 0 ? n + 1 : n;

    for (int row = 0; row < limit; ++row) {
      for (int col = 0; col < limit; ++col) {
        if (n % 2 == 0 && col == n / 2)
          continue;

        if (row % 2 == 0) {
          if ((col >= 2 * box && col < n - 2 * box) || (col % 2 == 0))
            System.out.print(star);
          else
            System.out.print(gap);
        } else {
          if (col % 2 == 0 && (col <= 2 * box || col >= n - 2 * box - 1))
            System.out.print(star);
          else
            System.out.print(gap);
        }
      }
      System.out.println();

      if (row < n / 2 && row % 2 != 0)
        ++box;
      else if (row >= n / 2 && row % 2 == 0)
        --box;
    }
  }

  private static void pattern6(int n) {
    int box = 0;
    int limit = n % 2 == 0 ? n + 1 : n;

    for (int row = 0; row < limit; ++row) {
      for (int col = 0; col < limit; ++col) {
        if (n % 2 == 0 && col == n / 2)
          continue;

        if (row % 2 == 0) {
          if ((col >= 2 * box && col < n - 2 * box)
              || (col % 2 == 0)
              || (col == row - 1))
            System.out.print(star);
          else
            System.out.print(gap);
        } else {
          if (col % 2 == 0
              && (col <= 2 * box || col >= n - 2 * box - 1)
              && (col != 2 * box)
              || (col == n - row - 1 - n % 2 && row > n / 2))
            System.out.print(star);
          else
            System.out.print(gap);
        }
      }

      System.out.println();

      if (row < n / 2 && row % 2 != 0)
        ++box;
      else if (row >= n / 2 && row % 2 == 0)
        --box;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = 0;

    System.out.print("(PATTERN)\nInput (rows, >= 3): ");

    try {
      n = scanner.nextInt();
    } catch (Exception e) {
      System.out.printf("Invalid input!\n\nERROR: %s\n", e.toString());
      System.exit(0);
    } finally {
      scanner.close();
    }

    if (n < N_MIN) {
      System.out.printf("Invalid input!\n\nERROR: input is less than minimum (%d)\n", N_MIN);
      System.exit(0);
    }

    String line = "";
    for (int i = 0; i < 2 * n - 1; ++i)
      line += '=';

    System.out.println();
    pattern1(n);
    System.out.printf("\n%s\n\n", line);
    pattern2(n);
    System.out.printf("\n%s\n\n", line);
    pattern3(n);
    System.out.printf("\n%s\n\n", line);
    pattern4(n);
    System.out.printf("\n%s\n\n", line);
    pattern5(n);
    System.out.printf("\n%s\n\n", line);
    pattern6(n);
  }
}
