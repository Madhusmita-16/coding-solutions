import java.io.*;
import java.util.*;

public class Solution {

    static long sumOfMultiples(long n, long k) {
        long m = (n - 1) / k;
        return k * m * (m + 1) / 2;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        while (t-- > 0) {
            long n = in.nextLong();

            long ans = sumOfMultiples(n, 3)
                     + sumOfMultiples(n, 5)
                     - sumOfMultiples(n, 15);

            System.out.println(ans);
        }

        in.close();
    }
}
