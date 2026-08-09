# Project Euler #26: Reciprocal cycles

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 26 from projecteuler.net

A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:

Where means, and has a 1-digit recurring cycle. It can be seen that has a 6-digit recurring cycle.

Find the value of smallest for which contains the longest recurring cycle in its decimal fraction part.

 **Input Format** 

The first line contains an integer, i.e., number of test cases.
Next lines will contain an integer.

 **Constraints** 

 **Output Format** 

Print the values corresponding to each test case.

 **Sample Input** 

```
2
5
10

```

 **Sample Output** 

```
3
7

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T10:14:44.803Z  

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

[View on HackerRank](https://www.hackerrank.com/challenges/euler026/problem)