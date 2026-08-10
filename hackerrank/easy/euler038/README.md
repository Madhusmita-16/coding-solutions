# Project Euler #38: Pandigital multiples

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 38 from projecteuler.net

Take the number and multiply it by each of,, and :

By concatenating each product we get the to pandigital,. We will call the concatenated product of and

The same can be achieved by starting with and multiplying by,,,, and, giving the pandigital,, which is the concatenated product of and. Let's call 9 as the Multiplier

The similar process can be shown for to pandigital also. when multiplied by gives which is pandigital.

You are given and where = or, find the multipliers for that given below and print them in ascending order.

 **Input Format** 

Input contains two integer and.

 **Constraints** 

 **Output Format** 

Print the answer corresponding to the test case.

 **Sample Input** 

```
100 8

```

 **Sample Output** 

```
18
78

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T09:35:08.555Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    static boolean isPandigital(String s, int k) {

        if (s.length() != k) {
            return false;
        }

        boolean[] used = new boolean[10];

        for (char c : s.toCharArray()) {

            int digit = c - '0';

            // K-pandigital uses digits 1..K.
            if (digit == 0 || digit > k) {
                return false;
            }

            if (used[digit]) {
                return false;
            }

            used[digit] = true;
        }

        return true;
    }

    static boolean isValid(int x, int k) {

        StringBuilder sb = new StringBuilder();

        for (int multiplier = 1; ; multiplier++) {

            sb.append(x * multiplier);

            if (sb.length() > k) {
                return false;
            }

            if (sb.length() == k) {
                return isPandigital(sb.toString(), k);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        /*
         * For an 8/9 digit pandigital concatenation,
         * the multiplier cannot have more than 4 digits.
         *
         * Testing beyond 9999 is unnecessary because
         * x * 1 already occupies 5+ digits and the next
         * product makes the concatenation too long.
         */
        int limit = Math.min(n - 1, 9999);

        for (int x = 2; x <= limit; x++) {

            if (isValid(x, k)) {
                System.out.println(x);
            }
        }
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler038/problem)