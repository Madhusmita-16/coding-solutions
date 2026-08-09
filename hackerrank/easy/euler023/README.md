# Project Euler #23: Non-abundant sums

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 23 from projecteuler.net

A perfect number is a number for which the sum of its proper divisors is exactly equal to the number. For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.

A number is called deficient if the sum of its proper divisors is less than and it is called abundant if this sum exceeds.

As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.

Given, print `YES` if it can be expressed as sum of two abundant numbers, else print `NO`.

 **Input Format** 

The first line contains an integer, i.e., number of test cases.
Next lines will contain an integer.

 **Constraints** 

 **Output Format** 

Print the values corresponding to each test case.

 **Sample Input** 

```
2
24
49

```

 **Sample Output** 

```
YES
NO

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T10:05:10.733Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        // Maximum limit from the problem
        int limit = 28123;

        // Find sum of proper divisors for every number
        int[] divisorSum = new int[limit + 1];

        for (int i = 1; i <= limit / 2; i++) {
            for (int j = i * 2; j <= limit; j += i) {
                divisorSum[j] += i;
            }
        }

        // Store all abundant numbers
        ArrayList<Integer> abundant = new ArrayList<>();

        for (int i = 1; i <= limit; i++) {
            if (divisorSum[i] > i) {
                abundant.add(i);
            }
        }

        // Check each test case
        while (t-- > 0) {

            int n = sc.nextInt();

            if (n > 28123) {
                System.out.println("YES");
                continue;
            }

            boolean possible = false;

            for (int i = 0; i < abundant.size(); i++) {

                int a = abundant.get(i);

                if (a >= n) {
                    break;
                }

                int b = n - a;

                if (b > 0 && divisorSum[b] > b) {
                    possible = true;
                    break;
                }
            }

            if (possible) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler023/problem)