import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static long pentagonal(long n) {
        return n * (3 * n - 1) / 2;
    }

    static boolean isPentagonal(long x) {

        if (x <= 0) {
            return false;
        }

        long value = 24 * x + 1;

        long root = (long) Math.sqrt(value);

        while ((root + 1) * (root + 1) <= value) {
            root++;
        }

        while (root * root > value) {
            root--;
        }

        return root * root == value && (1 + root) % 6 == 0;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        long N = sc.nextLong();
        int K = sc.nextInt();

        StringBuilder output = new StringBuilder();

        for (long n = K + 1; n < N; n++) {

            long current = pentagonal(n);
            long previous = pentagonal(n - K);

            long difference = current - previous;
            long sum = current + previous;

            if (isPentagonal(difference) || isPentagonal(sum)) {

                output.append(current).append('\n');
            }
        }

        System.out.print(output);

        sc.close();
    }
}
