import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Solution {

    static final BigInteger MOD = BigInteger.TEN.pow(10);

    static BigInteger power(long base, long exponent) {

        BigInteger b = BigInteger.valueOf(base);
        BigInteger result = BigInteger.ONE;

        while (exponent > 0) {

            if (exponent % 2 == 1) {
                result = result.multiply(b).mod(MOD);
            }

            b = b.multiply(b).mod(MOD);
            exponent /= 2;
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        BigInteger sum = BigInteger.ZERO;

        for (int i = 1; i <= n; i++) {
            sum = sum.add(power(i, i)).mod(MOD);
        }

        System.out.println(sum);

        sc.close();
    }
}
