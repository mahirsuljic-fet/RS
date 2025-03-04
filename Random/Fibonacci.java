public class Fibonacci {
  private static long fib(int n) {
    long result = 0;
    long prev = 1;

    for (int i = 0; i < n; ++i) {
      long temp = result;
      result += prev;
      prev = temp;
    }

    return result;
  }

  private static long fib_rec(int n) {
    if (n <= 1)
      return n;
    return fib_rec(n - 1) + fib_rec(n - 2);
  }

  public static void main(String[] args) {
    int n = 40;
    System.out.printf("Fibonacci %d -> %d\n", n, fib(n));
    System.out.printf("Fibonacci %d -> %d\n", n, fib_rec(n));
  }
}
