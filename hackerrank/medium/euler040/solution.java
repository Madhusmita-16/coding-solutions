import java.io.*;
import java.util.*;

public class Solution {

    static int getDigit(long position) {

        long digits = 1;
        long count = 9;
        long start = 1;

        while (position > digits * count) {
            position -= digits * count;
            digits++;
            count *= 10;
            start *= 10;
        }

        long number = start + (position - 1) / digits;

        int index = (int)((position - 1) % digits);

        String s = String.valueOf(number);

        return s.charAt(index) - '0';
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {

            long product = 1;

            /*
             * Each test case contains 7 positions.
             */
            for (int i = 0; i < 7; i++) {

                long position = sc.nextLong();

                product *= getDigit(position);
            }

            System.out.println(product);
        }

        sc.close();
    }
}
