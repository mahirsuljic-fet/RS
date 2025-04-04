public class Vector2DH extends Vector {
    public Vector2DH(double[] array) {
        super(array);

        if (this.size() != 3)
            throw new IllegalArgumentException("Input is not a 2D homogenous vector");
    }

    public Vector2DH(double[][] array) {
        super(array);

        if (this.size() != 3)
            throw new IllegalArgumentException("Input is not a 2D homogenous vector");
    }

    public Vector2DH(Vector vector) {
        super(vector);

        if (this.size() != 3)
            throw new IllegalArgumentException("Input is not a 2D homogenous vector");
    }

    public Vector2DH(Matrix matrix) {
        super(matrix);

        if (this.size() != 3)
            throw new IllegalArgumentException("Input is not a 2D homogenous vector");
    }

    public Vector2DH(double x, double y) {
        this(new double[][]{
                {x},
                {y},
                {1}
        });
    }

    public double getX() {
        return this.get(0, 0);
    }

    public double getY() {
        if (this.isVertical())
            return this.get(1, 0);
        else
            return this.get(0, 1);
    }

    public double x() {
        return this.getX();
    }

    public double y() {
        return this.getY();
    }
}
