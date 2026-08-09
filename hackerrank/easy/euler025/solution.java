import java.io.*;
import java.util.*;
import java.math.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        int[] q = new int[t];
        int max = 0;

        for (int i = 0; i < t; i++) {
            q[i] = sc.nextInt();
            max = Math.max(max, q[i]);
        }

        // For n >= 3:
        // digits(F(n)) = floor((n * log10(phi)) - log10(sqrt(5))) + 1
        double phi = (1 + Math.sqrt(5)) / 2;
        double logPhi = Math.log10(phi);
        double logSqrt5 = Math.log10(Math.sqrt(5));

        for (int digits : q) {

            if (digits == 1) {
                System.out.println(1);
                continue;
            }

            // Find first Fibonacci index with required digits
            long index = (long) Math.ceil(
                (digits - 1 + logSqrt5) / logPhi
            );

            // Correct possible floating-point rounding
            while (fibDigits(index) < digits) {
                index++;
            }

            while (index > 1 && fibDigits(index - 1) >= digits) {
                index--;
            }

            System.out.println(index);
        }

        sc.close();
    }

    static int fibDigits(long n) {
        if (n <= 2) {
            return 1;
        }

        double phi = (1 + Math.sqrt(5)) / 2;

        return (int) Math.floor(
            n * Math.log10(phi) - Math.log10(Math.sqrt(5))
        ) + 1;
    }
}
