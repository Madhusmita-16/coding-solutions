# Project Euler #36: Double-base palindromes

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 36 from projecteuler.net

The decimal number, (binary), is palindromic in both bases.

Find the sum of all natural numbers, less than, which are palindromic in base and base.

(Please note that the palindromic number, in either base, may not include leading zeros.)

 **Input Format** 

Input contains two integers and.

 **Constraints** 

 **Output Format** 

Print the answer corresponding to the test case.

 **Sample Input** 

```
10 2

```

 **Sample Output** 

```
25

```

 **Explanation** 

These numbers are palindromic in their decimal as well as base representation:. Their sum is

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T09:30:16.438Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    static String toBase(long num, int base) {

        if (num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            int digit = (int)(num % base);

            // Digits are represented using 0-9, A-Z
            if (digit < 10) {
                sb.append((char)('0' + digit));
            } else {
                sb.append((char)('A' + digit - 10));
            }

            num /= base;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();
        int base = sc.nextInt();

        long sum = 0;

        for (long i = 1; i < n; i++) {

            // Check palindrome in decimal.
            if (!isPalindrome(String.valueOf(i))) {
                continue;
            }

            // Convert to the required base and check palindrome.
            String representation = toBase(i, base);

            if (isPalindrome(representation)) {
                sum += i;
            }
        }

        System.out.println(sum);
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler036/problem)