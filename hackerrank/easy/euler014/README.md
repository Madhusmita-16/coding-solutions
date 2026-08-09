# Project Euler #14: Longest Collatz sequence

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 14 from projecteuler.net

The following iterative sequence is defined for the set of positive integers:

Using the rule above and starting with 13, we generate the following sequence:

It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.

Which starting number, produces the longest chain? If many possible such numbers are there print the maximum one.

 **Note:**  Once the chain starts the terms are allowed to go above.

 **Input Format** 

The first line contains an integer, i.e., number of test cases.
Next lines will contain an integers.

 **Constraints** 

-
-

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
9
9
19

```

 **Explanation** 

Collatz sequence for is,

containing steps and is the longest for

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T09:43:47.830Z  

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static long[] chainLength;

    static long getChainLength(long n) {

        if (n == 1) {
            return 1;
        }

        // Use memoization only when n fits in our array
        if (n < chainLength.length && chainLength[(int)n] != 0) {
            return chainLength[(int)n];
        }

        long next;

        if (n % 2 == 0) {
            next = n / 2;
        } else {
            next = 3 * n + 1;
        }

        long length = 1 + getChainLength(next);

        if (n < chainLength.length) {
            chainLength[(int)n] = length;
        }

        return length;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        int[] queries = new int[t];
        int maxN = 0;

        for (int i = 0; i < t; i++) {
            queries[i] = in.nextInt();

            if (queries[i] > maxN) {
                maxN = queries[i];
            }
        }

        chainLength = new long[maxN + 1];

        chainLength[1] = 1;

        // best[i] = starting number <= i with longest chain
        int[] best = new int[maxN + 1];

        if (maxN >= 1) {
            best[1] = 1;
        }

        long longest = 1;

        for (int i = 2; i <= maxN; i++) {

            long length = getChainLength(i);

            /*
             * Use >= because when chain lengths are equal,
             * we need the larger starting number.
             */
            if (length >= longest) {
                longest = length;
                best[i] = i;
            } else {
                best[i] = best[i - 1];
            }
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < t; i++) {
            output.append(best[queries[i]]).append('\n');
        }

        System.out.print(output);

        in.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler014/problem)