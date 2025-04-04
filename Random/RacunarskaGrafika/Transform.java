public interface Transform {

    void translate(double x, double y);

    void rotate(double angle);

    void scale(double x, double y);

    void shear(double x, double y);


    static SquareMatrix TranslatePointMatrix2D(double x, double y) {
        return new SquareMatrix(new Matrix(new double[][]{
                {1, 0, x},
                {0, 1, y},
                {0, 0, 1}
        }));
    }

    static SquareMatrix RotatePointMatrix2D(double angle) {
        return new SquareMatrix(new Matrix(new double[][]{
                {Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle)), 0},
                {Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0},
                {0, 0, 1}
        }));
    }

    static SquareMatrix ScalePointMatrix2D(double x, double y) {
        return new SquareMatrix(new Matrix(new double[][]{
                {x, 0, 0},
                {0, y, 0},
                {0, 0, 1}
        }));
    }

    static SquareMatrix ShearPointMatrix2D(double x, double y) {
        return new SquareMatrix(new Matrix(new double[][]{
                {1, x, 0},
                {y, 1, 0},
                {0, 0, 1}
        }));
    }

    static SquareMatrix TTranslatePointMatrix2D(double x, double y) {
        SquareMatrix matrix = TranslatePointMatrix2D(x, y);
        matrix.transpose();
        return matrix;
    }

    static SquareMatrix TRotatePointMatrix2D(double angle) {
        SquareMatrix matrix = RotatePointMatrix2D(angle);
        matrix.transpose();
        return matrix;
    }

    static SquareMatrix TScalePointMatrix2D(double x, double y) {
        SquareMatrix matrix = ScalePointMatrix2D(x, y);
        matrix.transpose();
        return matrix;
    }

    static SquareMatrix TShearPointMatrix2D(double x, double y) {
        SquareMatrix matrix = ShearPointMatrix2D(x, y);
        matrix.transpose();
        return matrix;
    }

    static SquareMatrix TranslateSystemMatrix2D(double x, double y) {
        return TranslatePointMatrix2D(-x, -y);
    }

    static SquareMatrix RotateSystemMatrix2D(double angle) {
        return RotatePointMatrix2D(-angle);
    }

    static SquareMatrix ScaleSystemMatrix2D(double x, double y) {
        return ScalePointMatrix2D(x, y);
    }

    static SquareMatrix ShearSystemMatrix2D(double x, double y) {
        return ShearPointMatrix2D(-x, -y);
    }


    static SquareMatrix TTranslateSystemMatrix2D(double x, double y) {
        return TTranslatePointMatrix2D(-x, -y);
    }

    static SquareMatrix TRotateSystemMatrix2D(double angle) {
        return TRotatePointMatrix2D(-angle);
    }

    static SquareMatrix TScaleSystemMatrix2D(double x, double y) {
        return TScalePointMatrix2D(x, y);
    }

    static SquareMatrix TShearSystemMatrix2D(double x, double y) {
        return TShearPointMatrix2D(-x, -y);
    }
}