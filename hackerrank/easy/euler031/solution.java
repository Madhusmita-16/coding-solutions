import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        int[] queries = new int[t];
        int maxN = 0;

        for (int i = 0; i < t; i++) {
            queries[i] = sc.nextInt();

            if (queries[i] > maxN) {
                maxN = queries[i];
            }
        }

        long MOD = 1000000007L;

        int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};

        long[] dp = new long[maxN + 1];

        dp[0] = 1;

        for (int coin : coins) {

            for (int amount = coin; amount <= maxN; amount++) {

                dp[amount] = (dp[amount] + dp[amount - coin]) % MOD;
            }
        }

        for (int n : queries) {
            System.out.println(dp[n]);
        }

        sc.close();
    }
}
