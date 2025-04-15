import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class z1_6 {
    private static int sum;

    public static void main(String[] args) {
        String fileName = "Zad1a.txt";
        int[][] nums = null;

        try {
            nums = readFile(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("I/O Error");
            System.exit(1);
        }

        if (nums.length != nums[0].length) {
            System.out.println("Not a square");
            return;
        }

        for (int[] arr : nums) {
            for (int num : arr) {
                System.out.print(num + "\t");
            }
            System.out.println();
        }

        if (checkMagic(nums)) {
            System.out.println("Magic (sum is " + sum + ")");
        } else {
            System.out.println("Not Magic");
        }

    }

    private static int[][] readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        line = reader.readLine();

        String[] firstRow = line.split("\t");

        int SIZE = firstRow.length;
        int[][] nums = new int[SIZE][SIZE];
        int row = 0;

        sum = 0;
        for (int i = 0; i < SIZE; i++) {
            nums[row][i] = Integer.parseInt(firstRow[i]);
            sum += nums[row][i];
        }

        while ((line = reader.readLine()) != null) {
            if (line.isBlank()) continue;

            ++row;

            String[] currentNums = line.split("\t");

            for (int j = 0; j < currentNums.length; j++) {
                nums[row][j] = Integer.parseInt(currentNums[j]);
            }
        }

        reader.close();

        return nums;
    }

    private static boolean checkMagic(int[][] nums) {
        if (!checkRows(nums)) {
            return false;
        }

        if (!checkColumns(nums)) {
            return false;
        }

        if (!checkDiagonals(nums)) {
            return false;
        }

        return true;
    }

    private static boolean checkRows(int[][] nums) {
        for (int[] row : nums) {
            int rowSum = 0;

            for (int num : row) {
                rowSum += num;
            }

            if (rowSum != sum) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkColumns(int[][] nums) {
        for (int col = 0; col < nums[0].length; col++) {
            int colSum = 0;

            for (int[] row : nums) {
                colSum += row[col];
            }

            if (colSum != sum) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkDiagonals(int[][] nums) {
        return checkDiagPrimary(nums) && checkDiagSecondary(nums);
    }

    private static boolean checkDiagPrimary(int[][] nums) {
        int diagSum = 0;

        for (int i = 0; i < nums.length; i++) {
            diagSum += nums[i][i];
        }

        return diagSum == sum;
    }

    private static boolean checkDiagSecondary(int[][] nums) {
        int diagSum = 0;

        for (int i = 0; i < nums.length; i++) {
            diagSum += nums[i][nums.length - i - 1];
        }

        return diagSum == sum;
    }
}
