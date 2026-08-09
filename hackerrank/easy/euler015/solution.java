import java.io.*;
import java.util.*;
import java.math.*;

public class Solution {

    static final long MOD = 1000000007L;

    static long modPow(long a, long b) {
        long result = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % MOD;
            }

            a = (a * a) % MOD;
            b >>= 1;
        }

        return result;
    }

    static long combination(int n, int r) {

        if (r > n - r) {
            r = n - r;
        }

        long numerator = 1;
        long denominator = 1;

        for (int i = 1; i <= r; i++) {
            numerator = (numerator * (n - r + i)) % MOD;
            denominator = (denominator * i) % MOD;
        }

        // numerator / denominator modulo MOD
        return (numerator * modPow(denominator, MOD - 2)) % MOD;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        while (t-- > 0) {

            int n = in.nextInt();
            int m = in.nextInt();

            System.out.println(combination(n + m, n));
        }

        in.close();
    }
}
