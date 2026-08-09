import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        while (t-- > 0) {

            int n = in.nextInt();

            long[][] triangle = new long[n][n];

            // Read triangle
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    triangle[i][j] = in.nextLong();
                }
            }

            // Bottom-up DP
            for (int i = n - 2; i >= 0; i--) {

                for (int j = 0; j <= i; j++) {

                    triangle[i][j] += Math.max(
                        triangle[i + 1][j],
                        triangle[i + 1][j + 1]
                    );
                }
            }

            System.out.println(triangle[0][0]);
        }

        in.close();
    }
}
