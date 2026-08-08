# Project Euler #4: Largest palindrome product

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

This problem is a programming version of Problem 4 from projecteuler.net

A palindromic number reads the same both ways. The smallest 6 digit palindrome made from the product of two 3-digit numbers is.

Find the largest palindrome made from the product of two 3-digit numbers which is less than.

 **Input Format** 

First line contains that denotes the number of test cases. This is followed by lines, each containing an integer,.

 **Constraints** 

-
-

 **Output Format** 

Print the required answer for each test case in a new line.

 **Sample Input 0** 

```
2
101110
800000

```

 **Sample Output 0** 

```
101101
793397

```

 **Explanation 0** 

- is product of and.
- is product of and.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-08T11:24:31.135Z  

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

[View on HackerRank](https://www.hackerrank.com/challenges/euler004/problem)