# Project Euler #47: Distinct primes factors

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 47 from projecteuler.net

The first two consecutive numbers to have two distinct prime factors are:

The first three consecutive numbers to have three distinct prime factors are:

Given find all the consecutive integers, where first integer is to have exactly distinct prime factors. Print the first of these numbers in ascending order.

 **Input Format** 
Input contains two integers and.

 **Output Format** 
Print the answer corresponding to the test case. Print each integer in a new line.

 **Constraints** 

 **Sample Input#00** 

```
20 2

```

 **Sample Output#00** 

```
14
20

```

 **Sample Input#01** 

```
644 3

```

 **Sample Output** 

```
644

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T10:26:58.984Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int limit = n + k - 1;

        // count[i] = number of distinct prime factors of i
        int[] count = new int[limit + 1];

        // Sieve
        for (int p = 2; p <= limit; p++) {

            if (count[p] == 0) {

                for (int j = p; j <= limit; j += p) {
                    count[j]++;
                }
            }
        }

        int consecutive = 0;

        // Check up to n + k - 1
        for (int i = 2; i <= limit; i++) {

            if (count[i] == k) {
                consecutive++;
            } else {
                consecutive = 0;
            }

            // We have k consecutive valid numbers
            if (consecutive >= k) {

                int start = i - k + 1;

                // Starting number must be <= n
                if (start <= n) {
                    System.out.println(start);
                }
            }
        }

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler047/problem)