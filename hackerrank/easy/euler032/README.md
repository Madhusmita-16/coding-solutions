# Project Euler #31: Coin sums

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 32 from projecteuler.net

We shall say that an -digit number is pandigital if it makes use of all the digits to exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.

The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.

Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through pandigital.

 **HINT:**  Some products can be obtained in more than one way so be sure to only include it once in your sum.

 **Input Format** 

Input contains an integer

 **Constraints** 

 **Output Format** 

Print the answer corresponding to the test case.

 **Sample Input** 

```
4

```

 **Sample Output** 

```
12

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T11:54:38.960Z  

```java
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

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler032/problem)