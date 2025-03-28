public class Circle extends Shape implements Drawable {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double circumference() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void draw() {
        for (double i = -radius; i <= radius; i++) {
            for (double j = -radius; j <= radius; j++) {
                if (i * i + j * j <= radius * radius) System.out.print(fillString);
                else System.out.print(emptyString);
            }
            System.out.println();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Circle circle)
            return this.radius == circle.radius;

        return false;
    }
}