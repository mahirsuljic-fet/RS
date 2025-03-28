import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Fakultet[] fakulteti = new Fakultet[2];
    Student[] studenti = new Student[2];

    Scanner scanner = new Scanner(System.in);

    fakulteti[0] = new Fakultet("Fakultet elektrotehnike", "Franjevacka 2");
    fakulteti[1] = new Fakultet("Ekonomski fakultet", "Franjevacka 2");

    System.out.println("Fakulteti:");
    for (int i = 0; i < fakulteti.length; i++) {
      System.out.println(i + " -> " + fakulteti[i].ime);
    }
    System.out.println();

    for (int i = 0; i < studenti.length; i++) {
      studenti[i] = new Student();

      System.out.println("Unesite ime studenta:");
      studenti[i].ime = scanner.nextLine();

      System.out.println("Unesite prezime studenta:");
      studenti[i].prezime = scanner.nextLine();

      System.out.println("Unesite JMBG studenta:");
      studenti[i].jmbg = scanner.nextLong();

      System.out.println("Unesite broj indeksa studenta:");
      studenti[i].br_index = scanner.nextInt();

      System.out.println("Unesite broj fakulteta studenta:");
      int n = scanner.nextInt();
      studenti[i].fakultet = fakulteti[n];

      scanner.nextLine(); // flush end of line char ('\n')
    }

    scanner.close();

    // sort
    if (studenti[0].ime.charAt(0) > studenti[1].ime.charAt(0)) {
      Student temp = studenti[0];
      studenti[0] = studenti[1];
      studenti[1] = temp;
    }

    System.out.println("Sortirani studenti:");
    for (Student student : studenti) {
      System.out.println(student.ime);
    }
  }
}

class Student {
  public String ime;
  public String prezime;
  public long jmbg;
  public int br_index;
  public Fakultet fakultet;
}

class Fakultet {
  public String ime;
  public String adresa;

  public Fakultet(String ime, String adresa) {
    this.ime = ime;
    this.adresa = adresa;
  }
}
