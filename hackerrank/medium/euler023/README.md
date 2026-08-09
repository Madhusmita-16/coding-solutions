# euler023

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

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
**Submitted:** 2026-08-09T10:03:41.943Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Number of names
        int n = sc.nextInt();

        String[] names = new String[n];

        // Read names
        for (int i = 0; i < n; i++) {
            names[i] = sc.next();
        }

        // Sort names alphabetically
        Arrays.sort(names);

        // Store name -> score
        HashMap<String, Long> scores = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String name = names[i];

            long alphabeticalValue = 0;

            // Calculate alphabetical value
            for (char c : name.toCharArray()) {
                alphabeticalValue += c - 'A' + 1;
            }

            // Position is i + 1 because indexing starts from 0
            long score = alphabeticalValue * (i + 1L);

            scores.put(name, score);
        }

        // Number of queries
        int q = sc.nextInt();

        // Answer each query
        for (int i = 0; i < q; i++) {
            String query = sc.next();
            System.out.println(scores.get(query));
        }

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler023/problem)