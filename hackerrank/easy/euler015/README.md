# Project Euler #15: Lattice paths

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 15 from projecteuler.net

Starting in the top left corner of a grid, and only being able to move to the right and down, there are exactly routes to the bottom right corner.

How many such routes are there through a grid? As number of ways can be very large, print it modulo.

 **Input Format** 

The first line contains an integer, i.e., number of test cases.
Next lines will contain integers and.

 **Constraints** 

-
-
-

 **Output Format** 

Print the values corresponding to each test case.

 **Sample Input** 

```
2
2 2
3 2

```

 **Sample Output** 

```
6
10

```

 **Explanation** 

For as shown in statement above.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T09:47:30.349Z  

```java
import java.io.*;
import java.util.*;
import java.math.*;

public class Solution {

    static final long MOD = 1000000007L;

    static long modPow(long a, long b) {
        long result = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % MOD;
            }

            a = (a * a) % MOD;
            b >>= 1;
        }

        return result;
    }

    static long combination(int n, int r) {

        if (r > n - r) {
            r = n - r;
        }

        long numerator = 1;
        long denominator = 1;

        for (int i = 1; i <= r; i++) {
            numerator = (numerator * (n - r + i)) % MOD;
            denominator = (denominator * i) % MOD;
        }

        // numerator / denominator modulo MOD
        return (numerator * modPow(denominator, MOD - 2)) % MOD;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        while (t-- > 0) {

            int n = in.nextInt();
            int m = in.nextInt();

            System.out.println(combination(n + m, n));
        }

        in.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler015/problem)