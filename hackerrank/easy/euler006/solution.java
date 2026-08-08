import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        for (int a0 = 0; a0 < t; a0++) {

            long n = in.nextLong();

            // Sum of first n natural numbers
            long sum = n * (n + 1) / 2;

            // Square of the sum
            long squareOfSum = sum * sum;

            // Sum of squares
            long sumOfSquares =
                    n * (n + 1) * (2 * n + 1) / 6;

            long result =
                    Math.abs(squareOfSum - sumOfSquares);

            System.out.println(result);
        }

        in.close();
    }
}
