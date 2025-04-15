import java.util.ArrayList;
import java.util.Arrays;

public class z2 {
    public static void main(String[] args) {
        // 1.
        double[] arr = new double[]{1.0, 2.0, 3.0, 4.0};
        ArrayList<Double> arrList = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0));

        Polinom polinom1 = new Polinom(arr);
        Polinom polinom2 = new Polinom(arrList);

        // 2.
        Polinom added = polinom1.add(polinom2);

        // 3.
        Polinom multiplied = polinom1.multiply(polinom2);

        // 4.
        double eval = polinom1.evaluate(2);

        // 5.
        System.out.println(polinom1);
        System.out.println(polinom2);
        System.out.println(added);
        System.out.println(multiplied);
        System.out.println(eval);
    }
}

class Polinom {
    private ArrayList<Double> coefficients;

    // 1.
    Polinom() {
        coefficients = new ArrayList<>();
    }

    Polinom(double[] coefficients) {
        this.coefficients = new ArrayList<>();
        for (int i = coefficients.length - 1; i >= 0; i--) {
            this.coefficients.add(coefficients[i]);
        }
    }

    Polinom(ArrayList<Double> coefficients) {
        this.coefficients = new ArrayList<>();
        for (int i = coefficients.size() - 1; i >= 0; i--) {
            this.coefficients.add(coefficients.get(i));
        }
    }

    // 2.
    public Polinom add(Polinom other) {
        int thisSize = coefficients.size();
        int otherSize = other.coefficients.size();

        Polinom bigger = (thisSize >= otherSize) ? this : other;
        Polinom smaller = (thisSize < otherSize) ? this : other;

        Polinom result = new Polinom();

        int biggerSize = bigger.coefficients.size();
        int smallerSize = smaller.coefficients.size();

        for (int i = 0; i < smallerSize; i++) {
            result.coefficients.add(this.coefficients.get(i) + other.coefficients.get(i));
        }

        for (int i = smallerSize; i < biggerSize; i++) {
            result.coefficients.add(bigger.coefficients.get(i));
        }

        return result;
    }

    // 3.
    public Polinom multiply(Polinom other) {
        int totalSize = this.coefficients.size() + other.coefficients.size() - 1;
        Polinom result = new Polinom();
        result.coefficients = new ArrayList<>();

        for (int i = 0; i < totalSize; i++) {
            result.coefficients.add(0.0);
        }

        for (int i = 0; i < this.coefficients.size(); i++) {
            for (int j = 0; j < other.coefficients.size(); j++) {
                double current = result.coefficients.get(i + j);
                double product = this.coefficients.get(i) * other.coefficients.get(j);
                result.coefficients.set(i + j, current + product);
            }
        }

        return result;

    }

    // 4.
    public double evaluate(double x) {
        double result = 0;

        for (int i = 0; i < this.coefficients.size(); i++) {
            result += this.coefficients.get(i) * Math.pow(x, i);
        }

        return result;
    }

    // 5.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int size = coefficients.size();

        for (int i = size - 1; i >= 0; i--) {
            double current = coefficients.get(i);

            if (current == 0) continue;

            if (current > 0 && i != size - 1) {
                sb.append("+");
            }

            if (i < size - 1) {
                sb.append(" ");
            }

            if (i == 0 || current != 1.0) {
                sb.append(current);
            }

            if (i > 0) {
                sb.append("x");

                if (i > 1) {
                    sb.append("^").append(i);
                }

                sb.append(" ");
            }
        }

        return sb.toString();
    }
}
