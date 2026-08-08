# Project Euler #4: Largest palindrome product

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

This problem is a programming version of Problem 9 from projecteuler.net

A Pythagorean triplet is a set of three natural numbers,, for which,

For example,

Given, Check if there exists any Pythagorean triplet for which
Find maximum possible value of among all such Pythagorean triplets, If there is no such Pythagorean triplet print.

 **Input Format** 

The first line contains an integer i.e. number of test cases.
The next lines will contain an integer.

 **Constraints** 

-
-

 **Output Format** 

Print the value corresponding to each test case in separate lines.

 **Sample Input 0** 

```
2
12
4

```

 **Sample Output 0** 

```
60
-1

```

 **Explanation 0** 

- For, we have a triplet, whose product is.
- For, we don't have any pythagorean triple.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-08T11:24:36.191Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    static boolean isPalindrome(int num) {

        int original = num;
        int reverse = 0;

        while (num > 0) {
            reverse = reverse * 10 + num % 10;
            num /= 10;
        }

        return original == reverse;
    }

    static int largestPalindrome(int n) {

        int max = 0;

        for (int i = 999; i >= 100; i--) {

            for (int j = i; j >= 100; j--) {

                int product = i * j;

                if (product >= n) {
                    continue;
                }

                if (product <= max) {
                    break;
                }

                if (isPalindrome(product)) {
                    max = product;
                }
            }
        }

        return max;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader =
                new BufferedReader(
                        new InputStreamReader(System.in));

        BufferedWriter bufferedWriter =
                new BufferedWriter(
                        new FileWriter(
                                System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(
                bufferedReader.readLine().trim());

        for (int i = 0; i < t; i++) {

            int n = Integer.parseInt(
                    bufferedReader.readLine().trim());

            int result = largestPalindrome(n);

            bufferedWriter.write(
                    String.valueOf(result));

            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler009/problem)