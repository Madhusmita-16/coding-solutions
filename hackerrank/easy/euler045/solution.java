import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static long triangular(long n) {
        return n * (n + 1) / 2;
    }

    static long pentagonal(long n) {
        return n * (3 * n - 1) / 2;
    }

    static long hexagonal(long n) {
        return n * (2 * n - 1);
    }

    static boolean isTriangular(long x) {
        if (x <= 0) {
            return false;
        }

        long d = 8 * x + 1;
        long r = (long) Math.sqrt(d);

        while ((r + 1) * (r + 1) <= d) {
            r++;
        }

        while (r * r > d) {
            r--;
        }

        return r * r == d;
    }

    static boolean isPentagonal(long x) {
        if (x <= 0) {
            return false;
        }

        long d = 24 * x + 1;
        long r = (long) Math.sqrt(d);

        while ((r + 1) * (r + 1) <= d) {
            r++;
        }

        while (r * r > d) {
            r--;
        }

        return r * r == d && (1 + r) % 6 == 0;
    }

    static boolean isHexagonal(long x) {
        if (x <= 0) {
            return false;
        }

        long d = 8 * x + 1;
        long r = (long) Math.sqrt(d);

        while ((r + 1) * (r + 1) <= d) {
            r++;
        }

        while (r * r > d) {
            r--;
        }

        return r * r == d && (1 + r) % 4 == 0;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        long N = sc.nextLong();
        int a = sc.nextInt();
        int b = sc.nextInt();

        StringBuilder ans = new StringBuilder();

        /*
         * 3 = Triangular
         * 5 = Pentagonal
         * 6 = Hexagonal
         */

        if ((a == 3 && b == 5) || (a == 5 && b == 3)) {

            // Generate pentagonal numbers and check triangular.
            for (long i = 1; ; i++) {

                long p = pentagonal(i);

                if (p >= N) {
                    break;
                }

                if (isTriangular(p)) {
                    ans.append(p).append('\n');
                }
            }

        } else if ((a == 5 && b == 6) || (a == 6 && b == 5)) {

            // Generate hexagonal numbers and check pentagonal.
            for (long i = 1; ; i++) {

                long h = hexagonal(i);

                if (h >= N) {
                    break;
                }

                if (isPentagonal(h)) {
                    ans.append(h).append('\n');
                }
            }

        } else if ((a == 3 && b == 6) || (a == 6 && b == 3)) {

            /*
             * Every hexagonal number is triangular.
             * Therefore every hexagonal number below N
             * satisfies the condition.
             */
            for (long i = 1; ; i++) {

                long h = hexagonal(i);

                if (h >= N) {
                    break;
                }

                ans.append(h).append('\n');
            }
        }

        System.out.print(ans);

        sc.close();
    }
}
