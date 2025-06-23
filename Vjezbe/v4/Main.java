public class Main {
    public static void main(String[] args) {
        Map<Integer, Object> map = new Map<>();

        map.add(1, "one");
        map.add(5, 2);
        map.add(3, 3.3);

        System.out.println(map.get(1));
        System.out.println(map.get(5));
        System.out.println(map.get(3));

        System.out.println(map);
    }
}