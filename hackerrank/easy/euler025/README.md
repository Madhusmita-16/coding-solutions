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
**Submitted:** 2026-08-09T10:08:28.381Z  

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        String letters = "abcdefghijklm";

        // Pre-calculate factorials
        long[] fact = new long[14];
        fact[0] = 1;

        for (int i = 1; i <= 13; i++) {
            fact[i] = fact[i - 1] * i;
        }

        while (t-- > 0) {

            long n = sc.nextLong();

            // Convert to 0-based index
            n--;

            ArrayList<Character> list = new ArrayList<>();

            for (char c : letters.toCharArray()) {
                list.add(c);
            }

            StringBuilder answer = new StringBuilder();

            // Select the first 12 characters.
            // The last character is automatically determined.
            for (int remaining = 12; remaining >= 0; remaining--) {

                long blockSize = fact[remaining];

                int index = (int)(n / blockSize);

                answer.append(list.get(index));
                list.remove(index);

                n = n % blockSize;
            }

            System.out.println(answer);
        }

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler025/problem)