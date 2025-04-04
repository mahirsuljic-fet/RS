import java.util.ArrayList;

public class CoordinateSystem2D implements Transform {
    private final ArrayList<Point> points;

    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    private double granularityX;
    private double granularityY;

    private static final double DEFAULT_GRANULARITY = 0.25;
    private static final double DEFAULT_GRANULARITY_X = DEFAULT_GRANULARITY / 2;
    private static final double DEFAULT_GRANULARITY_Y = DEFAULT_GRANULARITY;
    private static final char POINT_SYM = '•';
    private static final char EMPTY_SYM = ' ';
    private static final char ORIGIN_SYM = '╋';
    private static final String UP_SYM = "🭯";
    private static final String RIGHT_SYM = "🭬";
    private static final String LEFT_SYM = "🭮";
    private static final String DOWN_SYM = "🭭";
    private static final char HLINE_SYM = '━';
    private static final char VLINE_SYM = '┃';
    private static final char HMARK_SYM = '┿';
    private static final char VMARK_SYM = '╂';
    private static final String LABEL_Y = "Y";
    private static final String LABEL_X = "X";

    public CoordinateSystem2D() {
        this(1, 1);
    }

    public CoordinateSystem2D(double width, double height) {
        if (width < 1 || height < 1)
            throw new IllegalArgumentException("CoordinateSystem2D requires a positive size");

        points = new ArrayList<>();
        granularityX = DEFAULT_GRANULARITY_X;
        granularityY = DEFAULT_GRANULARITY_Y;
        minX = -width;
        maxX = width;
        minY = -height;
        maxY = height;
    }

    public void setGranularityX(double granularityX) {
        if (granularityX <= 0)
            throw new IllegalArgumentException("Granularity must be greater than 0");

        this.granularityX = granularityX;
    }

    public void setGranularityY(double granularityY) {
        if (granularityY <= 0)
            throw new IllegalArgumentException("Granularity must be greater than 0");

        this.granularityY = granularityY;
    }

    public void setGranularity(double granularity) {
        setGranularityX(granularity);
        setGranularityY(granularity);
    }

    public void setGranularity(double granularityX, double granularityY) {
        setGranularityX(granularityX);
        setGranularityY(granularityY);
    }

    public void add(Vector2DH point, Color color, char label) {
        points.add(new Point(point, color, label));
        updateMinMax(points.getLast());
    }

    public void add(double x, double y, Color color, char label) {
        add(new Vector2DH(x, y), color, label);
    }

    public void add(Vector2DH point, ColorType color, char label) {
        add(point, new Color(color), label);
    }

    public void add(double x, double y, ColorType color, char label) {
        add(new Vector2DH(x, y), color, label);
    }

    public void add(Vector2DH point, ColorType color) {
        add(point, color, POINT_SYM);
    }

    public void add(double x, double y, ColorType color) {
        add(new Vector2DH(x, y), color, POINT_SYM);
    }

    public void add(Vector2DH point, Color color) {
        add(point, color, POINT_SYM);
    }

    public void add(double x, double y, Color color) {
        add(new Vector2DH(x, y), color, POINT_SYM);
    }

    public void add(Vector2DH point, char label) {
        add(point, Color.WHITE, label);
    }

    public void add(double x, double y, char label) {
        add(new Vector2DH(x, y), label);
    }

    public void add(Vector2DH point) {
        add(point, Color.WHITE, POINT_SYM);
    }

    public void add(double x, double y) {
        add(new Vector2DH(x, y));
    }

    private void updateMinMax(Point point) {
        double x = point.position().x();
        double y = point.position().y();

        if (x + granularityX >= maxX)
            maxX = x + granularityX;
        if (y + granularityY >= maxY)
            maxY = y + granularityY;
        if (x - granularityX <= minX)
            minX = x - granularityX;
        if (y - granularityX <= minY)
            minY = y - granularityY;

        normalizeMinMax();
    }

    private void normalizeMinMax() {
        minX = roundDown(minX, granularityX);
        minY = roundDown(minY, granularityY);
        maxX = roundUp(maxX, granularityX);
        maxY = roundUp(maxY, granularityY);

        if (minX > -1) minX = -1;
        if (minY > -1) minY = -1;
        if (maxX < 1) maxX = 1;
        if (maxY < 1) maxY = 1;
    }

    public void draw() {
        System.out.print(this);
    }

    public void printPoints() {
        System.out.println("Points (" + points.size() + "):");
        for (Point point : points) {
            System.out.print(point.getSymbol() + " " + point.position().transposeCopy());
        }
    }

    public static double roundUp(double value, double base) {
        double temp = value % base;

        if (temp == 0)
            return value;

        if (temp < 0)
            temp = base + temp;

        return value + base - temp;
    }

    public static double roundDown(double value, double base) {
        double temp = value % base;

        if (temp == 0)
            return value;

        if (temp < 0)
            temp = base + temp;

        return value - temp;
    }

    @Override
    public String toString() {
        final boolean numbersX = granularityX <= 0.5;
        final boolean numbersY = granularityY <= 0.5;
        final boolean labelX = true;
        final boolean labelY = true;

        StringBuilder sb = new StringBuilder();
        Point[][] pointGrid = getPointGrid();

        sb.append(getArrowY(labelY));

        for (double y = maxY; y >= minY; y -= granularityY) {
            for (double x = minX; x <= maxX; x += granularityX) {
                int i = (int) Math.round((x - minX) / granularityX);
                int j = (int) Math.round((y - minY) / granularityY);

                if (pointGrid[i][j] != null) {
                    sb.append(pointGrid[i][j].getSymbol());
                } else if (x == 0) {
                    sb.append(handleAxisY(y));
                } else if (y == 0) {
                    sb.append(handleAxisX(x));

                    if (x == maxX) {
                        sb.append(RIGHT_SYM);

                        if (labelX) {
                            sb.append(LABEL_X);
                        }
                    }
                } else {
                    if (numbersY) {
                        int ry = (int) Math.round(y);
                        if (x + 2 * granularityX == 0 && y == ry) {
                            if (y > 0) {
                                sb.append(EMPTY_SYM);
                            }
                            sb.append(ry);
                            x += granularityX;
                            continue;
                        } else if (x + granularityX == 0 && y == ry && y > 0) {
                            sb.append(ry);
                            continue;
                        }
                    }

                    if (numbersX && x != maxX) {
                        double X = x + granularityX;
                        int rx = (int) Math.round(x);
                        int rX = (int) Math.round(X);
                        if (y + granularityY == 0) {
                            if (x == rx && rx > 0) {
                                sb.append(rx);
                                continue;
                            }

                            if (rX == 0 || X != rX) {
                                sb.append(EMPTY_SYM);
                                continue;
                            }

                            if (rX > 0)
                                sb.append(EMPTY_SYM);
                            sb.append(rX);
                            x += granularityX;
                            continue;
                        }
                    }

                    sb.append(EMPTY_SYM);
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private String getArrowY(boolean label) {
        StringBuilder sb = new StringBuilder();
        final double arrow_pos = -granularityX * LABEL_Y.length();

        for (double x = minX; x <= maxX; x += granularityX) {
            if (x == arrow_pos) {
                if (label) {
                    sb.append(LABEL_Y);
                } else {
                    sb.append(String.valueOf(EMPTY_SYM).repeat(LABEL_Y.length()));
                }

                sb.append(UP_SYM + "\n");
                break;
            } else {
                sb.append(EMPTY_SYM);
            }
        }

        return sb.toString();
    }

    private char handleAxisX(double x) {
        if (x == Math.round(x)) {
            return HMARK_SYM;
        } else {
            return HLINE_SYM;
        }
    }

    private char handleAxisY(double y) {
        if (y == 0) {
            return ORIGIN_SYM;
        } else if (y == Math.round(y)) {
            return VMARK_SYM;
        } else {
            return VLINE_SYM;
        }
    }

    private Point[][] getPointGrid() {
        int countX = (int) Math.ceil((maxX - minX) / granularityX) + 1;
        int countY = (int) Math.ceil((maxY - minY) / granularityY) + 1;
        Point[][] grid = new Point[countX][countY];

        for (Point point : points) {
            int x = (int) Math.round((point.position().getX() - minX) / granularityX);
            int y = (int) Math.round((point.position().getY() - minY) / granularityY);
            grid[x][y] = point;
        }

        return grid;
    }

    @Override
    public void translate(double x, double y) {
        minX = 0;
        minY = 0;
        maxX = 0;
        maxY = 0;

        for (Point point : points) {
            point.translate(-x, -y);
            updateMinMax(point);
        }
    }

    @Override
    public void rotate(double angle) {
        minX = 0;
        minY = 0;
        maxX = 0;
        maxY = 0;

        for (Point point : points) {
            point.rotate(-angle);
            updateMinMax(point);
        }
    }

    @Override
    public void scale(double x, double y) {
        minX = 0;
        minY = 0;
        maxX = 0;
        maxY = 0;

        for (Point point : points) {
            point.scale(x, y);
            updateMinMax(point);
        }
    }

    @Override
    public void shear(double x, double y) {
        minX = 0;
        minY = 0;
        maxX = 0;
        maxY = 0;

        for (Point point : points) {
            point.shear(-x, -y);
            updateMinMax(point);
        }
    }
}

record Point(Vector2DH position, Color color, char symbol) implements Transform {

    public String getSymbol() {
        return color.getANSI() + symbol + Color.getANSI(Color.RESET);
    }

    @Override
    public void translate(double x, double y) {
        position.translate(x, y);
    }

    @Override
    public void rotate(double angle) {
        position.rotate(angle);
    }

    @Override
    public void scale(double x, double y) {
        position.scale(x, y);
    }

    @Override
    public void shear(double x, double y) {
        position.shear(x, y);
    }
}