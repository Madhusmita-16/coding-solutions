# Project Euler #26: Reciprocal cycles

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
**Submitted:** 2026-08-09T10:15:09.491Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        int[] queries = new int[t];

        int maxN = 0;

        for (int i = 0; i < t; i++) {
            queries[i] = sc.nextInt();
            maxN = Math.max(maxN, queries[i]);
        }

        // best[n] = denominator < n having longest recurring cycle
        int[] best = new int[maxN + 1];

        int[] remainderPosition = new int[maxN + 1];

        int bestD = 0;
        int bestCycle = 0;

        for (int d = 2; d < maxN; d++) {

            Arrays.fill(remainderPosition, -1);

            int remainder = 1;
            int position = 0;

            while (remainder != 0 && remainderPosition[remainder] == -1) {

                remainderPosition[remainder] = position;

                remainder = (remainder * 10) % d;
                position++;
            }

            int cycle = 0;

            if (remainder != 0) {
                cycle = position - remainderPosition[remainder];
            }

            if (cycle > bestCycle) {
                bestCycle = cycle;
                bestD = d;
            }

            // For every N from d+1 onward, d is currently the best.
            best[d + 1] = bestD;
        }

        // Fill unanswered values
        for (int i = 2; i <= maxN; i++) {
            if (best[i] == 0) {
                best[i] = best[i - 1];
            }
        }

        for (int n : queries) {
            System.out.println(best[n]);
        }

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler027/problem)