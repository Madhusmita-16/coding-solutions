import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        BigInteger MOD = BigInteger.valueOf(1000000007L);
        BigInteger TWO = BigInteger.valueOf(2);
        BigInteger FOUR = BigInteger.valueOf(4);
        BigInteger EIGHT = BigInteger.valueOf(8);
        BigInteger SIXTEEN = BigInteger.valueOf(16);
        BigInteger ONE = BigInteger.ONE;

        while (t-- > 0) {

            BigInteger n = sc.nextBigInteger();

            BigInteger x = n.divide(TWO);

            BigInteger xPlusOne = x.add(ONE);
            BigInteger twoXPlusOne = x.multiply(TWO).add(ONE);

            BigInteger sumSquares = x
                    .multiply(xPlusOne)
                    .multiply(twoXPlusOne)
                    .divide(BigInteger.valueOf(6));

            BigInteger sumNumbers = x
                    .multiply(xPlusOne)
                    .divide(TWO);

            BigInteger answer = ONE
                    .add(sumSquares.multiply(SIXTEEN))
                    .add(sumNumbers.multiply(FOUR))
                    .add(x.multiply(FOUR));

            answer = answer.mod(MOD);

            System.out.println(answer);
        }

        sc.close();
    }
}
