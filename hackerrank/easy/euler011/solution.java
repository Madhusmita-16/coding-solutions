import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int[][] grid = new int[20][20];

        // Read the 20 x 20 grid
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                grid[i][j] = in.nextInt();
            }
        }

        long maxProduct = 0;

        // Check every cell as the starting point
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {

                // Right
                if (j + 3 < 20) {
                    long product = 1;

                    for (int k = 0; k < 4; k++) {
                        product *= grid[i][j + k];
                    }

                    maxProduct = Math.max(maxProduct, product);
                }

                // Down
                if (i + 3 < 20) {
                    long product = 1;

                    for (int k = 0; k < 4; k++) {
                        product *= grid[i + k][j];
                    }

                    maxProduct = Math.max(maxProduct, product);
                }

                // Diagonal down-right
                if (i + 3 < 20 && j + 3 < 20) {
                    long product = 1;

                    for (int k = 0; k < 4; k++) {
                        product *= grid[i + k][j + k];
                    }

                    maxProduct = Math.max(maxProduct, product);
                }

                // Diagonal down-left
                if (i + 3 < 20 && j - 3 >= 0) {
                    long product = 1;

                    for (int k = 0; k < 4; k++) {
                        product *= grid[i + k][j - k];
                    }

                    maxProduct = Math.max(maxProduct, product);
                }
            }
        }

        System.out.println(maxProduct);

        in.close();
    }
}
