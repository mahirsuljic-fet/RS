public class Main {

    public static void main(String[] args) {
        CoordinateSystem2D system = new CoordinateSystem2D();

        system.add(1, -2, Color.RED, 'A');
        system.add(2, 1, Color.RED, 'B');
        system.add(2, -2, Color.RED, 'C');

        system.draw();
        system.printPoints();
        System.out.println();

        system.rotate(45);
        system.draw();
        system.printPointsDetailed();
        System.out.println();

        system.translate(2, 2);
        system.draw();
        system.printPointsDetailed();
        System.out.println();

        system.rotate(45);
        system.draw();
        system.printPointsDetailed();
        System.out.println();

        system.translate(1 - 2 * Math.sqrt(2), -2);
        system.draw();
        system.printPoints();
        System.out.println();

        system.rotate(-45);
        system.draw();
        system.printPointsDetailed();
        System.out.println();

        system.translate(-1.65, -1.1785);
        system.draw();
        system.printPointsDetailed();
        System.out.println();

        system.rotate(-45);
        system.draw();
        System.out.println();
        system.printVectors();
    }
}
