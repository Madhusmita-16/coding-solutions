# Project Euler #39: Integer right triangles

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 40 from projecteuler.net

An irrational decimal fraction is created by concatenating the positive integers:

It can be seen that the digit of the fractional part is 1.

If represents the digit of the fractional part, find the value of the following expression.

 **Input Format** 

First line contains that denotes the number of test cases. This is followed by lines, each containing an integers.

 **Constraints** 

 **Output Format** 

Print the answer corresponding to the test case.

 **Sample Input** 

```
1
1 2 3 4 5 6 7

```

 **Sample Output** 

```
5040

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T09:37:03.182Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        int[] queries = new int[t];
        int maxN = 0;

        for (int i = 0; i < t; i++) {
            queries[i] = sc.nextInt();
            maxN = Math.max(maxN, queries[i]);
        }

        int[] count = new int[maxN + 1];

        /*
         * Generate primitive Pythagorean triples.
         *
         * a = m*m - n*n
         * b = 2*m*n
         * c = m*m + n*n
         */
        for (int m = 2; m * m + m * m <= maxN; m++) {

            for (int n = 1; n < m; n++) {

                if ((m - n) % 2 == 0) {
                    continue;
                }

                if (gcd(m, n) != 1) {
                    continue;
                }

                int a = m * m - n * n;
                int b = 2 * m * n;
                int c = m * m + n * n;

                int perimeter = a + b + c;

                for (int p = perimeter; p <= maxN; p += perimeter) {
                    count[p]++;
                }
            }
        }

        int[] best = new int[maxN + 1];

        int bestPerimeter = 0;
        int bestCount = 0;

        for (int p = 1; p <= maxN; p++) {

            if (count[p] > bestCount) {
                bestCount = count[p];
                bestPerimeter = p;
            }

            best[p] = bestPerimeter;
        }

        for (int q : queries) {
            System.out.println(best[q]);
        }

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler040/problem)