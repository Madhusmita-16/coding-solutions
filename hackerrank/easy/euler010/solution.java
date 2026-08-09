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

            long n = in.nextLong();
            long maxProduct = -1;

            // a < b < c
            // a can never be >= n/3
            for (long a = 1; a < n / 3; a++) {

                long numerator = n * (n - 2 * a);
                long denominator = 2 * (n - a);

                // b must be an integer
                if (numerator % denominator != 0) {
                    continue;
                }

                long b = numerator / denominator;
                long c = n - a - b;

                if (b > a && b < c) {
                    long product = a * b * c;
                    maxProduct = Math.max(maxProduct, product);
                }
            }

            System.out.println(maxProduct);
        }

        in.close();
    }
}
