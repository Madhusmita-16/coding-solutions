import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {

            long n = sc.nextLong();

            /*
             * Triangle number:
             *
             * T_k = k * (k + 1) / 2
             *
             * Therefore:
             *
             * k^2 + k - 2n = 0
             *
             * k = (-1 + sqrt(1 + 8n)) / 2
             */

            long d = 1 + 8 * n;
            long root = (long) Math.sqrt(d);

            if (root * root == d && (root - 1) % 2 == 0) {
                System.out.println((root - 1) / 2);
            } else {
                System.out.println(-1);
            }
        }

        sc.close();
    }
}
