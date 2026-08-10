# Project Euler #36: Double-base palindromes

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 37 from projecteuler.net

The number has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right, and remain prime at each stage:,,, and. Similarly we can work from right to left:,,, and.

Find the sum of primes that are both truncatable from left to right and right to left below.

NOTE:,,, and are not considered to be truncatable primes.

 **Input Format** 

Input contains an integer.

 **Constraints** 

 **Output Format** 

Print the answer corresponding to the test case.

 **Sample Input** 

```
100

```

 **Sample Output** 

```
186

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T09:30:30.252Z  

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

[View on HackerRank](https://www.hackerrank.com/challenges/euler037/problem)