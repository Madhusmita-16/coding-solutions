# Project Euler #37: Truncatable primes

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 38 from projecteuler.net

Take the number and multiply it by each of,, and :

By concatenating each product we get the to pandigital,. We will call the concatenated product of and

The same can be achieved by starting with and multiplying by,,,, and, giving the pandigital,, which is the concatenated product of and. Let's call 9 as the Multiplier

The similar process can be shown for to pandigital also. when multiplied by gives which is pandigital.

You are given and where = or, find the multipliers for that given below and print them in ascending order.

 **Input Format** 

Input contains two integer and.

 **Constraints** 

 **Output Format** 

Print the answer corresponding to the test case.

 **Sample Input** 

```
100 8

```

 **Sample Output** 

```
18
78

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T09:31:45.375Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; (long) i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    // Remove digits from right to left
    static boolean truncatableFromRight(int n) {
        while (n > 0) {
            if (!isPrime(n)) {
                return false;
            }

            n /= 10;
        }

        return true;
    }

    // Remove digits from left to right
    static boolean truncatableFromLeft(int n) {

        int divisor = 1;

        while (divisor <= n / 10) {
            divisor *= 10;
        }

        while (divisor > 0) {

            if (!isPrime(n)) {
                return false;
            }

            n %= divisor;
            divisor /= 10;
        }

        return true;
    }

    static boolean isTruncatablePrime(int n) {

        // 2, 3, 5, 7 are not considered truncatable.
        if (n < 10) {
            return false;
        }

        return isPrime(n)
                && truncatableFromRight(n)
                && truncatableFromLeft(n);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long sum = 0;

        for (int i = 10; i < n; i++) {

            if (isTruncatablePrime(i)) {
                sum += i;
            }
        }

        System.out.println(sum);
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler038/problem)