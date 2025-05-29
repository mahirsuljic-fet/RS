import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        new Calculator();
    }
}

class Calculator extends JFrame {
    private final Font font = new Font("Arial", Font.BOLD, 40);

    private String lastOperand;
    private String currentOperand;
    private Character operator;

    private JButton[][] buttons;
    private JLabel display;
    private Character[][] symbols = new Character[][]{
            {'7', '8', '9', '+'},
            {'4', '5', '6', '-'},
            {'1', '2', '3', 'x'},
            {'C', '0', '=', '/'},
    };

    private boolean calculated = false;

    private int ROWS = symbols.length;
    private int COLS = symbols[0].length;

    private void createButtons() {
        buttons = new JButton[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                final Character symbol = symbols[i][j];

                buttons[i][j] = new JButton(symbol.toString());
                buttons[i][j].setFont(font);

                if (symbol == 'C') {
                    buttons[i][j].setBackground(Color.RED);

                    buttons[i][j].addActionListener(e -> {
                        lastOperand = "";
                        currentOperand = "";

                        display.setText(currentOperand);
                    });
                } else if (symbol >= '0' && symbol <= '9') {
                    buttons[i][j].addActionListener(e -> {

                        if (calculated) {
                            currentOperand = symbol.toString();
                            calculated = false;
                        } else {
                            currentOperand += symbol;
                        }

                        display.setText(currentOperand);
                    });
                } else if (symbol == '=') {
                    buttons[i][j].addActionListener(e -> {
                        double result = 0.0;

                        double lhs = Double.parseDouble(lastOperand);
                        double rhs = Double.parseDouble(currentOperand);

                        switch (operator) {
                            case '+' -> result = lhs + rhs;
                            case '-' -> result = lhs - rhs;
                            case 'x' -> result = lhs * rhs;
                            case '/' -> result = lhs / rhs;
                        }

                        lastOperand = currentOperand;
                        currentOperand = Double.toString(result);

                        calculated = true;

                        display.setText(currentOperand);
                    });
                } else {
                    buttons[i][j].addActionListener(e -> {
                        operator = symbol;
                        lastOperand = currentOperand;
                        currentOperand = "";

                        display.setText(currentOperand);
                    });
                }

            }
        }
    }

    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        setVisible(true);

        lastOperand = "";
        currentOperand = "";

        display = new JLabel();
        display.setPreferredSize(new Dimension(100, 50));
        display.setFont(font);

        createButtons();

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(ROWS, COLS));

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                buttonsPanel.add(buttons[i][j]);
            }
        }

        add(buttonsPanel, BorderLayout.CENTER);
        add(display, BorderLayout.NORTH);
    }
}