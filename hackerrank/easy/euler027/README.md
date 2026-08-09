# Project Euler #27: Quadratic primes

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 27 from projecteuler.net

Euler published the remarkable quadratic formula:

It turns out that the formula will produce 40 primes for the consecutive values to. However, when, is divisible by, and certainly when, is clearly divisible by.

Using computers, the incredible formula was discovered, which produces primes for the consecutive values to. The product of the coefficients, and, is.

Considering quadratics of the form:

where is the modulus/absolute value of
e.g. and

Find the coefficients, and, for the quadratic expression that produces the maximum number of primes for consecutive values of, starting with.

 **Note**  For this challenge you can assume solution to be unique.

 **Input Format** 

The first line contains an integer.

 **Output Format** 

Print the value of and separated by space.

 **Constraints** 

 **Sample Input** 

```
42

```

 **Sample Output** 

```
-1 41

```

 **Explanation** 

for and, you get 42 primes.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T10:18:31.662Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int bestA = 0;
        int bestB = 0;
        int maxCount = 0;

        // b must be positive and prime
        for (int b = 2; b <= n; b++) {

            if (!isPrime(b)) {
                continue;
            }

            // a is between -n and n
            for (int a = -n; a <= n; a++) {

                int count = 0;

                while (true) {

                    long value = (long) count * count
                               + (long) a * count
                               + b;

                    if (value < 2 || !isPrime((int) value)) {
                        break;
                    }

                    count++;
                }

                if (count > maxCount) {
                    maxCount = count;
                    bestA = a;
                    bestB = b;
                }
            }
        }

        System.out.println(bestA + " " + bestB);

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler027/problem)