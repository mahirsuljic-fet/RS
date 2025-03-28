public class Triangle extends Shape {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triangle(double ax, double ay, double bx, double by, double cx, double cy) {
        this.a = new Point(ax, ay);
        this.b = new Point(bx, by);
        this.c = new Point(cx, cy);
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    public void setA(Point a) {
        this.a = a;
    }

    public void setB(Point b) {
        this.b = b;
    }

    public void setC(Point c) {
        this.c = c;
    }

    public void setA(double ax, double ay) {
        this.a = new Point(ax, ay);
    }

    public void setB(double bx, double by) {
        this.b = new Point(bx, by);
    }

    public void setC(double cx, double cy) {
        this.c = new Point(cx, cy);
    }

    @Override
    public double area() {
        return Math.abs(a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) / 2.0;
    }

    @Override
    public double circumference() {
        return a.distance(b) + b.distance(c) + c.distance(a);
    }
}