# Project Euler #8: Largest product in a series

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 8 from projecteuler.net

Find the greatest product of consecutive digits in the digit number.

 **Input Format** 

First line contains that denotes the number of test cases.
First line of each test case will contain two integers &.
Second line of each test case will contain a digit integer.

 **Constraints** 

-
-
-

 **Output Format** 

Print the required answer for each test case.

 **Sample Input 0** 

```
2
10 5
3675356291
10 5
2709360626

```

 **Sample Output 0** 

```
3150
0

```

 **Explanation 0** 

- For and selecting consequetive digits, we have,,,, and. Where gives maximum product as
- For, lies in all selection of consequetive digits hence maximum product remains

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-08T11:18:39.652Z  

```java
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



public class Solution {

    static long largestProduct(String num, int k) {
        long maxProduct = 0;
        int n = num.length();
        for (int i = 0; i + k <= n; i++) {
            long product = 1;
            for (int j = i; j < i + k; j++) {
                product *= (num.charAt(j) - '0');
            }
            if (product > maxProduct) {
                maxProduct = product;
            }
        }
        return maxProduct;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int k = Integer.parseInt(firstMultipleInput[1]);

                String num = bufferedReader.readLine();

                System.out.println(largestProduct(num, k));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler008/problem)