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

            BigInteger factorial = BigInteger.ONE;

            for (int i = 2; i <= n; i++) {
                factorial = factorial.multiply(BigInteger.valueOf(i));
            }

            String value = factorial.toString();

            int sum = 0;

            for (int i = 0; i < value.length(); i++) {
                sum += value.charAt(i) - '0';
            }

            System.out.println(sum);
        }

        in.close();
    }
}
