import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        BigInteger sum = BigInteger.ZERO;

        for (int i = 0; i < n; i++) {
            String number = in.next();
            sum = sum.add(new BigInteger(number));
        }

        String result = sum.toString();

        System.out.println(result.substring(0, 10));

        in.close();
    }
}
