public class Vector extends Matrix implements Transform {
    private int size;
    private boolean vertical;

    public Vector(int n) {
        super(n, 1);
        this.vertical = true;
    }

    public Vector(double[] array) {
        super(array.length, 1);
        for (int i = 0; i < array.length; i++)
            this.matrix[i][0] = array[i];

        this.size = array.length;
        this.vertical = true;
    }

    public Vector(double[][] array) {
        this(new Matrix(array));

        this.vertical = super.height() > super.width();

        if (this.vertical)
            this.size = super.height();
        else
            this.size = super.width();
    }

    public Vector(Vector vector) {
        this((Matrix) vector);

        this.size = vector.size();
        ;
        this.vertical = vector.isVertical();
    }

    public Vector(Matrix matrix) {
        super(matrix);

        if (matrix.width() == 1) {
            this.size = matrix.height();
            this.vertical = true;
        } else if (matrix.height() == 1) {
            this.size = matrix.width();
            this.vertical = false;
        } else {
            throw new IllegalArgumentException("Not a vector");
        }
    }

    public int size() {
        return size;
    }

    public void transpose() {
        double[][] result;

        if (vertical)
            result = transposeToHorizontal();
        else
            result = transposeToVertical();

        this.matrix = result;
        this.vertical = !this.vertical;
    }

    private double[][] transposeToHorizontal() {
        double[][] oldVector = this.matrix;
        double[][] newVector = new double[1][size];

        for (int i = 0; i < size; i++)
            newVector[0][i] = oldVector[i][0];

        super.setSize(size, 1);
        return newVector;
    }

    private double[][] transposeToVertical() {
        double[][] oldVector = this.matrix;
        double[][] newVector = new double[size][1];

        for (int i = 0; i < size; i++)
            newVector[i][0] = oldVector[0][i];

        super.setSize(1, size);
        return newVector;
    }

    public Vector transposeCopy() {
        Vector result = new Vector(this);
        result.transpose();
        return result;
    }

    public boolean isVertical() {
        return vertical;
    }

    public boolean isHorizontal() {
        return !vertical;
    }

    public double dot(Vector other) {
        if (this.size != other.size)
            throw new IllegalArgumentException("Size mismatch");

        double result = 0;

        if (this.vertical) {
            if (other.vertical) {
                for (int i = 0; i < this.size; i++)
                    result += this.matrix[i][0] * other.matrix[i][0];
            } else {
                for (int i = 0; i < this.size; i++)
                    result += this.matrix[i][0] * other.matrix[0][i];
            }
        } else {
            if (other.vertical) {
                for (int i = 0; i < this.size; i++)
                    result += this.matrix[0][i] * other.matrix[i][0];
            } else {
                for (int i = 0; i < this.size; i++)
                    result += this.matrix[0][i] * other.matrix[0][i];
            }
        }

        return result;
    }

    public Vector cross(Vector other) {
        return this.crossRight(other);
    }

    public Vector crossLeft(Vector other) {
        return other.crossRight(this);
    }

    public Vector crossRight(Vector other) {
        if (this.size != 3 || other.size != 3)
            throw new IllegalArgumentException("Vector cross product is defined only for 3D and 7D vectors and is implemented only for 3D vectors");

        Vector va;
        Vector vb;

        if (this.vertical) {
            va = this;
            vb = other;
        } else {
            va = this.transposeCopy();
            vb = other.transposeCopy();
        }

        double ax = va.matrix[0][0];
        double ay = va.matrix[1][0];
        double az = va.matrix[2][0];

        double bx = vb.matrix[0][0];
        double by = vb.matrix[1][0];
        double bz = vb.matrix[2][0];

        Vector result = new Vector(3);

        result.matrix[0][0] = ay * bz - az * by;
        result.matrix[1][0] = az * bx - ax * bz;
        result.matrix[2][0] = ax * by - ay * bx;

        return result;
    }

    public double magnitude() {
        if (this.vertical)
            return magnitudeVertical();
        else
            return magnitudeHorizontal();
    }

    private double magnitudeVertical() {
        double sum = 0;
        for (int i = 0; i < this.size; i++)
            sum += this.matrix[i][0] * this.matrix[i][0];
        return Math.sqrt(sum);
    }

    private double magnitudeHorizontal() {
        double sum = 0;
        for (int i = 0; i < this.size; i++)
            sum += this.matrix[0][i] * this.matrix[0][i];
        return Math.sqrt(sum);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void translate(double x, double y) {
        if (this.vertical)
            this.composeLeft(Transform.TranslatePointMatrix2D(x, y));
        else
            this.composeRight(Transform.TTranslatePointMatrix2D(x, y));
    }

    @Override
    public void rotate(double angle) {
        if (this.vertical)
            this.composeLeft(Transform.RotatePointMatrix2D(angle));
        else
            this.composeRight(Transform.TRotatePointMatrix2D(angle));
    }

    @Override
    public void scale(double x, double y) {
        if (this.vertical)
            this.composeLeft(Transform.ScalePointMatrix2D(x, y));
        else
            this.composeRight(Transform.TScalePointMatrix2D(x, y));
    }

    @Override
    public void shear(double x, double y) {
        if (this.vertical)
            this.composeLeft(Transform.ShearPointMatrix2D(x, y));
        else
            this.composeRight(Transform.TShearPointMatrix2D(x, y));
    }
}