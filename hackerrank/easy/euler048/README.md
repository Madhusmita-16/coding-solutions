# Project Euler #47: Distinct primes factors

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 48 from projecteuler.net

The series,

Find the last ten digits of the series,

 **Note**  You do not need to print leading zeros. See sample.

 **Input Format** 

Input contains an integer

 **Constraints** 

 **Output Format** 

Print the answer corresponding to the test case.

 **Sample Input** 

```
10

```

 **Sample Output** 

```
405071317

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T10:27:05.197Z  

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

[View on HackerRank](https://www.hackerrank.com/challenges/euler048/problem)