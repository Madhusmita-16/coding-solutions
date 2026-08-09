import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static String[] ones = {
        "", "One", "Two", "Three", "Four", "Five",
        "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
        "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    static String[] tens = {
        "", "", "Twenty", "Thirty", "Forty",
        "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    static String convert(long n) {

        if (n == 0) {
            return "Zero";
        }

        if (n < 20) {
            return ones[(int)n];
        }

        if (n < 100) {
            return tens[(int)(n / 10)] +
                   (n % 10 == 0 ? "" : " " + ones[(int)(n % 10)]);
        }

        if (n < 1000) {
            return ones[(int)(n / 100)] + " Hundred" +
                   (n % 100 == 0 ? "" : " " + convert(n % 100));
        }

        if (n < 1000000) {
            return convert(n / 1000) + " Thousand" +
                   (n % 1000 == 0 ? "" : " " + convert(n % 1000));
        }

        if (n < 1000000000) {
            return convert(n / 1000000) + " Million" +
                   (n % 1000000 == 0 ? "" : " " + convert(n % 1000000));
        }

        if (n < 1000000000000L) {
            return convert(n / 1000000000) + " Billion" +
                   (n % 1000000000 == 0 ? "" : " " + convert(n % 1000000000));
        }

        return convert(n / 1000000000000L) + " Trillion" +
               (n % 1000000000000L == 0 ? "" : " " + convert(n % 1000000000000L));
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        while (t-- > 0) {

            long n = in.nextLong();

            System.out.println(convert(n));
        }

        in.close();
    }
}
