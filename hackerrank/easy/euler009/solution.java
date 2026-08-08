import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        while (t-- > 0) {
            long n = in.nextLong();

            long sum = 0;
            long a = 2;
            long b = 8;

            while (a <= n) {
                sum += a;
                long c = 4 * b + a;
                a = b;
                b = c;
            }

            System.out.println(sum);
        }

        in.close();
    }
}
