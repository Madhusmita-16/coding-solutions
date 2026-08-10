# Project Euler #49: Prime permutations

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

This problem is a programming version of Problem 49 from projecteuler.net

The arithmetic sequence, in which each of the terms increases by is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.

There are no arithmetic sequences made up of three,, or primes, exhibiting this property.
You are given and, find all size sequences where first element is less than and elements are permutations of each other, are prime and are in AP(Arithmetic Progression).

Print the answer as concatenated integer formed by joining terms.

 **Input Format** 

Input contains two integers and

 **Constraints** 

 **Output Format** 

Print the answer corresponding to the test case. each in new line in numerically sorted order of smallest value.

 **Sample Input** 

```
2000 3

```

 **Sample Output** 

```
148748178147

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T10:38:39.831Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    static final int LIMIT = 1000000;
    static boolean[] prime;

    static void sieve() {

        prime = new boolean[LIMIT];

        Arrays.fill(prime, true);

        prime[0] = false;
        prime[1] = false;

        for (int i = 2; (long) i * i < LIMIT; i++) {

            if (prime[i]) {

                for (int j = i * i; j < LIMIT; j += i) {
                    prime[j] = false;
                }
            }
        }
    }

    static String signature(int n) {

        char[] digits = String.valueOf(n).toCharArray();

        Arrays.sort(digits);

        return new String(digits);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int limit = sc.nextInt();
        int k = sc.nextInt();

        sieve();

        /*
         * Group prime numbers having exactly the same digits.
         */
        HashMap<String, ArrayList<Integer>> groups =
                new HashMap<String, ArrayList<Integer>>();

        for (int p = 1000; p < LIMIT; p++) {

            if (!prime[p]) {
                continue;
            }

            String key = signature(p);

            ArrayList<Integer> list = groups.get(key);

            if (list == null) {
                list = new ArrayList<Integer>();
                groups.put(key, list);
            }

            list.add(p);
        }

        ArrayList<String> answers = new ArrayList<String>();

        /*
         * Find arithmetic progressions inside each permutation group.
         */
        for (ArrayList<Integer> list : groups.values()) {

            if (list.size() < k) {
                continue;
            }

            Collections.sort(list);

            HashSet<Integer> set = new HashSet<Integer>(list);

            for (int i = 0; i < list.size(); i++) {

                int start = list.get(i);

                if (start >= limit) {
                    break;
                }

                for (int j = i + 1; j < list.size(); j++) {

                    int difference = list.get(j) - start;

                    boolean valid = true;

                    for (int x = 2; x < k; x++) {

                        long value =
                                (long) start +
                                (long) x * difference;

                        if (value >= LIMIT ||
                            !set.contains((int) value)) {

                            valid = false;
                            break;
                        }
                    }

                    if (!valid) {
                        continue;
                    }

                    StringBuilder result = new StringBuilder();

                    for (int x = 0; x < k; x++) {
                        result.append(
                                start + x * difference
                        );
                    }

                    answers.add(result.toString());
                }
            }
        }

        /*
         * IMPORTANT:
         * Sort numerically, not lexicographically.
         *
         * If two answers have different lengths,
         * the shorter number is numerically smaller.
         */
        Collections.sort(answers, new Comparator<String>() {

            public int compare(String a, String b) {

                if (a.length() != b.length()) {
                    return a.length() - b.length();
                }

                return a.compareTo(b);
            }
        });

        for (String answer : answers) {
            System.out.println(answer);
        }

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler049/problem)