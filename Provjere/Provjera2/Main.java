import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // 1. Kreirati kolekciju stringova i popuniti je sa 50 nasumičnih stringova.
        ArrayList<String> collection = new ArrayList<>();
        Random rand = new Random();
        final int N_STRINGS = 50;
        final int MIN_STRING_LENGTH = 10;
        final int MAX_STRING_LENGTH = 20;

        for (int i = 0; i < N_STRINGS; ++i) {
            StringBuilder s = new StringBuilder();
            int n = rand.nextInt(MIN_STRING_LENGTH, MAX_STRING_LENGTH);

            for (int j = 0; j < n; ++j) {
                char c = (char) rand.nextInt('a', 'z');

                if (rand.nextBoolean())
                    c = Character.toUpperCase(c);

                s.append(c);
            }

            collection.add(s.toString());
        }

        System.out.println("PROVJERA:");
        for (String s : collection) {
            System.out.println(s);
        }

        System.out.println("------------------------------");

        // 2. Koristeći streamove, izbrisati duplikate

        collection.add("DUPLIKAT");
        collection.add("DUPLIKAT");

        collection =
                collection.stream()
                        .distinct()
                        .collect(Collectors.toCollection(ArrayList::new));

        System.out.println("PROVJERA:");
        for (String s : collection) {
            System.out.println(s);
        }

        System.out.println("------------------------------");

        // 3. Koristeći streamove, ispisati duzinu svakog stringa i spasiti u novu kolekciju (listu)
        collection.stream().forEach((s) -> System.out.println(s.length()));

        List<Integer> lengths =
                collection.stream()
                        .map(String::length)
                        .toList();

        System.out.println("PROVJERA:");
        for (int i = 0; i < collection.size(); i++) {
            System.out.println(collection.get(i) + " -> " + lengths.get(i));
        }

        System.out.println("------------------------------");

        // 4. Koristeći streamove, ispisati sortirane brojeve(od  najvećeg ka najmanjem) iz kolekcije(novonastale)
        lengths.stream().sorted((lhs, rhs) -> Integer.compare(rhs, lhs)).forEach(System.out::println);

        System.out.println("------------------------------");

        // 5. Koristeći streamove, pretvoriti brojeve u stringove
        lengths.stream()
                .map(Object::toString)
                .forEach((s) -> System.out.println(s + "\t(" + s.getClass() + ")")); // provjera

        System.out.println("------------------------------");

        // 6. Koristeci streamove, izbaciti sve neparne brojeve i ispisati rezultate(cijele brojeve) i snimiti u niz
        Integer[] even =
                lengths.stream()
                        .filter((n) -> n % 2 == 0)
                        .toArray(Integer[]::new);

        Arrays.stream(even).forEach(System.out::println);

        System.out.println("------------------------------");
    }
}