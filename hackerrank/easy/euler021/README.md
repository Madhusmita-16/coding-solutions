# Project Euler #21: Amicable numbers

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 21 from projecteuler.net

Let be defined as the sum of proper divisors of (numbers less than which divide evenly into).
If and, where, then and are an amicable pair and each of and are called amicable numbers.

For example, the proper divisors of are therefore. The proper divisors of are so.

Evaluate the sum of all the amicable numbers under.

 **Input Format** 

The first line contains an integer, i.e., number of test cases.
Next lines will contain an integer.

 **Constraints** 

-
-

 **Output Format** 

Print the values corresponding to each test case.

 **Sample Input** 

```
1
300

```

 **Sample Output** 

```
504

```

 **Explanation** 

Under we only have and, sum is

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T10:01:02.101Z  

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        int[] queries = new int[t];
        int maxN = 0;

        for (int i = 0; i < t; i++) {
            queries[i] = in.nextInt();
            maxN = Math.max(maxN, queries[i]);
        }

        // sumDivisors[x] = sum of proper divisors of x
        long[] sumDivisors = new long[maxN];

        /*
         * Add divisor i to all multiples of i.
         * Start from 2*i because i itself is not a proper
         * divisor of itself.
         */
        for (int i = 1; i < maxN; i++) {

            for (int j = i * 2; j < maxN; j += i) {
                sumDivisors[j] += i;
            }
        }

        /*
         * prefix[i] = sum of amicable numbers < i
         */
        long[] prefix = new long[maxN];

        for (int i = 1; i < maxN; i++) {

            prefix[i] = prefix[i - 1];

            long partner = sumDivisors[i];

            /*
             * i and partner are amicable if:
             *
             * sumDivisors[i] = partner
             * sumDivisors[partner] = i
             *
             * and they must be different.
             */
            if (partner != i &&
                partner > 0 &&
                partner < maxN &&
                sumDivisors[(int)partner] == i) {

                prefix[i] += i;
            }
        }

        for (int n : queries) {
            System.out.println(prefix[n - 1]);
        }

        in.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler021/problem)