# Project Euler #35: Circular primes

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 35 from projecteuler.net

The number,, is called a circular prime because all rotations of the digits:,, and, are themselves prime.

There are thirteen such primes below :. Sum of which is

Find the sum of circular primes that are below ?

 **Note** 
Rotations can exceed.

 **Input Format** 

Input contains an integer

 **Constraints** 

-

 **Output Format** 

Print the answer corresponding to the test case.

 **Sample Input** 

```
100

```

 **Sample Output** 

```
446

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T09:19:08.732Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    static boolean[] isPrime;

    static void sieve(int n) {
        isPrime = new boolean[n + 1];

        Arrays.fill(isPrime, true);

        if (n >= 0) isPrime[0] = false;
        if (n >= 1) isPrime[1] = false;

        for (int i = 2; (long) i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    static boolean isCircularPrime(int num) {

        String s = String.valueOf(num);

        /*
         * Rotations can be larger than N,
         * so we use the full integer value of each rotation.
         */

        for (int i = 0; i < s.length(); i++) {

            String rotation =
                    s.substring(i) + s.substring(0, i);

            int value = Integer.parseInt(rotation);

            if (value >= isPrime.length ||
                !isPrime[value]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        /*
         * Rotations of numbers below N can have
         * the same number of digits, so N is enough
         * as the prime-table limit.
         */
        sieve(n);

        long answer = 0;

        for (int i = 2; i < n; i++) {

            if (isPrime[i] && isCircularPrime(i)) {
                answer += i;
            }
        }

        System.out.println(answer);
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler035/problem)