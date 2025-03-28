public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Rectangle(5, 2),
                new Circle(5),
                new Triangle(1, 3, 5, 2, 4, 6),
                new Rectangle(2, 4),
                new Circle(8)
        };

        for (Shape shape : shapes) {
            System.out.println("Shape: " + shape.getClass().getSimpleName());
            System.out.printf("Area: %.2f\n", shape.area());
            System.out.printf("Circumference: %.2f\n", shape.circumference());
            System.out.println("Data:");

            if (shape instanceof Rectangle rectangle) {
                System.out.printf("\twidth: %.2f\n", rectangle.getWidth());
                System.out.printf("\theight: %.2f\n", rectangle.getHeight());
            } else if (shape instanceof Circle circle) {
                System.out.printf("\tradius: %.2f\n", circle.getRadius());
            } else if (shape instanceof Triangle triangle) {
                System.out.printf("\tpoint A: %s\n", triangle.getA());
                System.out.printf("\tpoint B: %s\n", triangle.getB());
                System.out.printf("\tpoint C: %s\n", triangle.getC());
            }

            if (shape instanceof Drawable) {
                System.out.println("Drawing:");
                ((Drawable) shape).draw();
            } else System.out.println("Shape is not drawable");

            System.out.println();
        }
    }
}
