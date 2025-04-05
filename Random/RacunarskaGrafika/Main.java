public class Main {

    public static void main(String[] args) {
        CoordinateSystem2D system = new CoordinateSystem2D();

        system.add(1, 2, Color.RED, 'A');
        system.add(3, 3, Color.RED, 'B');
        system.add(4, 5, Color.RED, 'C');
        system.add(2, 4, Color.RED, 'D');

        system.showTransformMatrices();

        system.draw();
        System.out.println();
        system.printPoints();
        System.out.println();

        system.translate(1, 2);

        System.out.println();
        system.draw();
        System.out.println();
        system.printPoints();
        System.out.println();

        system.shear(0.5, 0.5);

        System.out.println();
        system.draw();
        System.out.println();
        system.printPoints();
        System.out.println();

        system.scale(2.0 / 3.0, 2.0 / 3.0);

        System.out.println();
        system.draw();
        System.out.println();
        system.printVectors();
        system.rotate(-45);

        System.out.println();
        system.draw();
        System.out.println();
        system.printPointsDetailed();
        System.out.println();

        system.scale(Math.sqrt(2), Math.sqrt(2));

        System.out.println();
        system.draw();
        System.out.println();
        system.printVectors();
    }
}
