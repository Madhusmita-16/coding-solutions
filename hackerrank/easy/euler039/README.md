# Project Euler #39: Integer right triangles

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 39 from projecteuler.net

If is the perimeter of a right angle triangle with integral length sides, {a, b, c}, there are exactly three solutions for

For which value of, is the number of solutions maximised? If there are multiple values print smallest.

 **Input Format** 

First line contains that denotes the number of test cases. This is followed by lines, each containing an integer,.

 **Constraints** 

 **Output Format** 

Print the required answer for each test case.

 **Sample Input** 

```
2
12
80

```

 **Sample Output** 

```
12
60

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T09:37:00.996Z  

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

[View on HackerRank](https://www.hackerrank.com/challenges/euler039/problem)