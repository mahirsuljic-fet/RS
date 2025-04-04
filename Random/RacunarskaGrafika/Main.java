public class Main {

    public static void main(String[] args) {
        CoordinateSystem2D system = new CoordinateSystem2D();
        system.draw();

        system.add(1, 1, Color.RED, 'A');
        system.add(2, 1, Color.GREEN, 'B');
        system.add(2, 2, Color.BLUE, 'C');
        system.add(1, 2, Color.YELLOW, 'D');
        system.add(1, 1.5, Color.WHITE);
        system.add(2, 1.5, Color.WHITE);
        system.add(1.5, 1.5, '✖');
        system.add(1.5, 1);
        system.add(1.5, 2);

        System.out.println();
        system.draw();

        system.translate(1, 1);

        System.out.println();
        system.draw();

        system.rotate(-45);

        System.out.println();
        system.draw();

        system.scale(Math.sqrt(2), Math.sqrt(2));

        System.out.println();
        system.draw();

        system.translate(-2, 0);

        System.out.println();
        system.draw();

        system.shear(-1, 1);

        System.out.println();
        system.draw();

        system.translate(2, -2);

        System.out.println();
        system.draw();

        system.scale(0.5, 0.5);

        System.out.println();
        system.draw();

        system.translate(-1, -1);

        System.out.println();
        system.draw();

        System.out.println();
        system.printPoints();
    }
}
