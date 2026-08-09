# Project Euler #31: Coin sums

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 31 from projecteuler.net

In England the currency is made up of pound,, and pence,, and there are eight coins in general circulation:

It is possible to make in the following way:

How many different ways can be made using any number of coins? As the result can be large print answer mod

 **Input Format** 

The first line contains an integer, i.e., number of test cases.
Next lines will contain an integer.

 **Note:**  N is given as and not

 **Constraints** 

 **Output Format** 

Print the values corresponding to each test case.

 **Sample Input** 

```
3
10
15
20

```

 **Sample Output** 

```
11
22
41

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T11:54:32.476Z  

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

[View on HackerRank](https://www.hackerrank.com/challenges/euler031/problem)