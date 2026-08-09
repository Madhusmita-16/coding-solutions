# Project Euler #10: Summation of primes

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

This problem is a programming version of Problem 10 from projecteuler.net

The sum of the primes below is.

Find the sum of all the primes not greater than given.

 **Input Format** 

The first line contains an integer i.e. number of the test cases.
The next lines will contains an integer.

 **Constraints** 

-
-

 **Output Format** 

Print the value corresponding to each test case in separate line.

 **Sample Input 0** 

```
2
5
10

```

 **Sample Output 0** 

```
10
17

```

 **Explanation 0** 

- For, we have primes as and the sum is.
- For, we have primes as and the sum is.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T09:35:37.099Z  

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        int[] queries = new int[t];

        int maxN = 0;

        // Read all test cases and find maximum n
        for (int i = 0; i < t; i++) {
            queries[i] = in.nextInt();
            maxN = Math.max(maxN, queries[i]);
        }

        // Sieve of Eratosthenes
        boolean[] isPrime = new boolean[maxN + 1];

        Arrays.fill(isPrime, true);

        if (maxN >= 0) {
            isPrime[0] = false;
        }

        if (maxN >= 1) {
            isPrime[1] = false;
        }

        for (int i = 2; (long) i * i <= maxN; i++) {

            if (isPrime[i]) {

                for (int j = i * i; j <= maxN; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // Prefix sum of primes
        long[] prefix = new long[maxN + 1];

        for (int i = 1; i <= maxN; i++) {
            prefix[i] = prefix[i - 1];

            if (isPrime[i]) {
                prefix[i] += i;
            }
        }

        // Answer each query
        StringBuilder output = new StringBuilder();

        for (int n : queries) {
            output.append(prefix[n]).append('\n');
        }

        System.out.print(output);

        in.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler010/problem)