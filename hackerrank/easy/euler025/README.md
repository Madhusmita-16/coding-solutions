# Project Euler #25: N-digit Fibonacci number

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 25 from projecteuler.net

The Fibonacci sequence is defined by the recurrence relation:

.

Hence the first 12 terms will be:

The term,, is the first term to contain three digits.
What is the first term in the Fibonacci sequence to contain digits?

 **Input Format** 

The first line contains an integer, i.e., number of test cases.
Next lines will contain an integer.

 **Constraints** 

 **Output Format** 

Print the values corresponding to each test case.

 **Sample Input** 

```
2
3
4

```

 **Sample Output** 

```
12
17

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T10:10:35.414Z  

```java
import java.io.*;
import java.util.*;
import java.math.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        int[] queries = new int[t];
        int maxDigits = 0;

        // Read all queries first
        for (int i = 0; i < t; i++) {
            queries[i] = sc.nextInt();
            maxDigits = Math.max(maxDigits, queries[i]);
        }

        // Fibonacci numbers
        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.ONE;

        // Answer for each digit length
        int[] answer = new int[maxDigits + 1];

        int index = 2;
        int currentDigits = 1;

        while (currentDigits < maxDigits) {

            BigInteger next = a.add(b);
            a = b;
            b = next;
            index++;

            int digits = b.toString().length();

            if (digits > currentDigits) {
                for (int d = currentDigits + 1; d <= digits && d <= maxDigits; d++) {
                    answer[d] = index;
                }
                currentDigits = digits;
            }
        }

        // Print answers
        for (int n : queries) {
            System.out.println(answer[n]);
        }

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler025/problem)