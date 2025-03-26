public class Main {

  public static void main(String[] args) {
    int a = 2;
    int b = 3;
    double c = 4;

    for (int i = 0; i < 4; i++) {
      if (a >= 5 || b >= 5 || c >= 5)
        continue;

      --a;
      ++b;
      c /= 2;
    }

    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
  }

}
