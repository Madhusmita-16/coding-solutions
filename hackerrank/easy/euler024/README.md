# Project Euler #24: Lexicographic permutations

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 24 from projecteuler.net

A permutation is an ordered arrangement of objects. For example, is one possible permutation of the word. If all of the permutations are listed alphabetically, we call it lexicographic order. The lexicographic permutations of are:

What is the lexicographic permutation of the word ?

 **Input Format** 

The first line contains an integer, i.e., number of test cases.
Next lines will contain an integer.

 **Constraints** 

 **Output Format** 

Print the values corresponding to each test case.

 **Sample Input** 

```
2
1
2

```

 **Sample Output** 

```
abcdefghijklm
abcdefghijkml

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T10:05:13.730Z  

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

[View on HackerRank](https://www.hackerrank.com/challenges/euler024/problem)