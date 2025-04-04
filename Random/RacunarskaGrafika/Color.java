public class Color {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[1;90m";
    private static final String ANSI_RED = "\u001B[1;91m";
    private static final String ANSI_GREEN = "\u001B[1;92m";
    private static final String ANSI_YELLOW = "\u001B[1;93m";
    private static final String ANSI_BLUE = "\u001B[1;94m";
    private static final String ANSI_PURPLE = "\u001B[1;95m";
    private static final String ANSI_CYAN = "\u001B[1;96m";
    private static final String ANSI_WHITE = "\u001B[1;97m";

    public static final ColorType RESET = ColorType.NONE;
    public static final ColorType WHITE = ColorType.WHITE;
    public static final ColorType BLACK = ColorType.BLACK;
    public static final ColorType RED = ColorType.RED;
    public static final ColorType GREEN = ColorType.GREEN;
    public static final ColorType YELLOW = ColorType.YELLOW;
    public static final ColorType BLUE = ColorType.BLUE;
    public static final ColorType PURPLE = ColorType.PURPLE;
    public static final ColorType CYAN = ColorType.CYAN;

    private final ColorType color;

    public Color(ColorType color) {
        this.color = color;
    }

    public String getANSI() {
        return getANSI(this.color);
    }

    public static String getANSI(ColorType color) {
        return switch (color) {
            case WHITE -> ANSI_WHITE;
            case BLACK -> ANSI_BLACK;
            case RED -> ANSI_RED;
            case GREEN -> ANSI_GREEN;
            case YELLOW -> ANSI_YELLOW;
            case BLUE -> ANSI_BLUE;
            case PURPLE -> ANSI_PURPLE;
            case CYAN -> ANSI_CYAN;
            default -> ANSI_RESET;
        };
    }
}