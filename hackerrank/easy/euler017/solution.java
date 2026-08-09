import java.io.*;
import java.util.*;
import java.math.*;

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        while (t-- > 0) {

            int n = in.nextInt();

            BigInteger number = BigInteger.valueOf(2).pow(n);

            String value = number.toString();

            int sum = 0;

            for (int i = 0; i < value.length(); i++) {
                sum += value.charAt(i) - '0';
            }

            System.out.println(sum);
        }

        in.close();
    }
}
