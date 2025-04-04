public class SquareMatrix extends Matrix {
    private final int size;

    public SquareMatrix(int n) {
        super(n);
        this.size = n;
    }

    public SquareMatrix(double[][] matrix) {
        super(matrix);
        this.size = matrix.length;

        if (matrix.length != matrix[0].length)
            throw new IllegalArgumentException("Not a square matrix");
    }

    public SquareMatrix(Matrix matrix) {
        this(matrix.matrix);
    }

    public SquareMatrix(SquareMatrix matrix) {
        super(matrix);
        this.size = matrix.size;
    }

    public void transpose() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                double temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public SquareMatrix TransposeCopy() {
        SquareMatrix result = new SquareMatrix(this);
        result.transpose();
        return result;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}