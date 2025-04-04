public class Matrix {
    protected double[][] matrix;
    private int rows;
    private int columns;

    protected static final char BRACKET_LEFT_TOP = '┏';
    protected static final char BRACKET_LEFT_BOTTOM = '┗';
    protected static final char BRACKET_RIGHT_TOP = '┓';
    protected static final char BRACKET_RIGHT_BOTTOM = '┛';
    protected static final char BRACKET_MAIN = '┃';
    protected static final String BRACKET_LEFT = "\u001B[1m[\u001B[0m";
    protected static final String BRACKET_RIGHT = "\u001B[1m]\u001B[0m";

    public Matrix(int n) {
        this(n, n);
    }

    public Matrix(int rows, int columns) {
        this.matrix = new double[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public Matrix(double[][] matrix) {
        this(matrix.length, matrix[0].length);
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, matrix[i].length);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.rows, matrix.columns);
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                this.matrix[i][j] = matrix.get(i, j);
            }
        }
    }

    public int width() {
        return this.columns;
    }

    public int height() {
        return this.rows;
    }

    public double get(int row, int column) {
        return this.matrix[row][column];
    }

    public void set(int row, int column, double value) {
        this.matrix[row][column] = value;
    }

    public void print() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int getNumLength(int n) {
        int length = 1;

        if (n == 0)
            return 1;

        if (n < 0) {
            ++length;
            n *= -1;
        }

        while (n / 10 > 0) {
            length++;
            n = n / 10;
        }

        return length;
    }

    public void prettyPrint() {
        prettyPrint(true, 2);
    }

    public void prettyPrint(boolean brackets) {
        prettyPrint(brackets, 2);
    }

    public void prettyPrint(int decimalPlaces) {
        prettyPrint(true, decimalPlaces);
    }

    public void prettyPrint(boolean brackets, int decimalPlaces) {
        System.out.println(this.toString());
    }

    private String getFormatString(int decimalPlaces) {
        int maxNumLength = 0;
        boolean negative = false;

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                int currentNumLength = getNumLength((int) matrix[i][j]);

                if (currentNumLength > maxNumLength) {
                    maxNumLength = currentNumLength;
                    negative = matrix[i][j] < 0;
                } else if (currentNumLength == maxNumLength && !negative)
                    negative = matrix[i][j] < 0;
            }
        }

        if (decimalPlaces > 0)
            maxNumLength += 1 + decimalPlaces; // for .xx

        return (negative ? "% " : "%") + maxNumLength + "." + decimalPlaces + "f ";
    }

    public Matrix multiply(Matrix other) {
        Matrix result = new Matrix(this);
        result.compose(other);
        return result;
    }

    public Matrix multiplyLeft(Matrix other) {
        if (this.height() != other.width())
            throw new IllegalArgumentException("Matrix size missmatch");

        Matrix result = new Matrix(other.height(), this.width());

        for (int i = 0; i < other.height(); i++) {
            for (int j = 0; j < this.width(); j++) {
                double sum = 0;
                for (int k = 0; k < other.width(); k++)
                    sum += other.matrix[i][k] * this.matrix[k][j];
                result.matrix[i][j] = sum;
            }
        }

        return result;
    }

    public Matrix multiplyRight(Matrix other) {
        if (this.width() != other.height())
            throw new IllegalArgumentException("Matrix size missmatch");

        Matrix result = new Matrix(this.height(), other.width());

        for (int i = 0; i < this.height(); i++) {
            for (int j = 0; j < other.width(); j++) {
                double sum = 0;
                for (int k = 0; k < this.width(); k++)
                    sum += this.matrix[i][k] * other.matrix[k][j];
                result.matrix[i][j] = sum;
            }
        }

        return result;
    }

    public void compose(Matrix other) {
        this.composeRight(other);
    }

    public void composeLeft(Matrix other) {
        Matrix result = this.multiplyLeft(other);
        this.set(result);
    }

    public void composeRight(Matrix other) {
        Matrix result = this.multiplyRight(other);
        this.set(result);
    }

    public Matrix add(Matrix other) {
        if (this.height() != other.height() || this.width() != other.width())
            throw new IllegalArgumentException("Matrix size missmatch");

        Matrix result = new Matrix(this.rows, this.columns);

        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.columns; j++) {
                result.matrix[i][j] = this.matrix[i][j] + other.matrix[i][j];
            }
        }

        return result;
    }

    public Matrix sub(Matrix other) {
        return this.add(other.negateCopy());
    }

    public void negate() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.matrix[i][j] *= -1;
            }
        }
    }

    public Matrix negateCopy() {
        Matrix result = new Matrix(this);
        result.negate();
        return result;
    }

    protected void setWidth(int width) {
        this.columns = width;
    }

    protected void setHeight(int height) {
        this.rows = height;
    }

    protected void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void set(Matrix other) {
        this.matrix = other.matrix;
        this.rows = other.rows;
        this.columns = other.columns;
    }

    @Override
    public String toString() {
        return prettyFormat();
    }

    public String prettyFormat() {
        return prettyFormat(true, 2);
    }

    public String prettyFormat(boolean brackets) {
        return prettyFormat(brackets, 2);
    }

    public String prettyFormat(int decimalPlaces) {
        return prettyFormat(true, decimalPlaces);
    }

    public String prettyFormat(boolean brackets, int decimalPlaces) {
        String formatString = getFormatString(decimalPlaces);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.rows; i++) {
            if (brackets) {
                if (i == 0) {
                    if (this.rows > 1)
                        sb.append(BRACKET_LEFT_TOP + " ");
                    else
                        sb.append(BRACKET_LEFT + " ");
                } else if (i == this.rows - 1) {
                    sb.append(BRACKET_LEFT_BOTTOM + " ");
                } else {
                    sb.append(BRACKET_MAIN + " ");
                }
            }

            for (int j = 0; j < this.columns; j++)
                sb.append(String.format(formatString, this.matrix[i][j]));

            if (brackets) {
                if (i == 0) {
                    if (this.rows > 1)
                        sb.append(BRACKET_RIGHT_TOP);
                    else
                        sb.append(BRACKET_RIGHT);
                } else if (i == this.rows - 1) {
                    sb.append(BRACKET_RIGHT_BOTTOM);
                } else {
                    sb.append(BRACKET_MAIN);
                }
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}