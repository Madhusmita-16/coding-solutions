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
**Submitted:** 2026-08-10T10:34:43.657Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    static final int MAX = 1000000;
    static boolean[] prime;

    static void sieve() {

        prime = new boolean[MAX];

        Arrays.fill(prime, true);

        prime[0] = false;
        prime[1] = false;

        for (int i = 2; i * i < MAX; i++) {

            if (prime[i]) {

                for (int j = i * i; j < MAX; j += i) {
                    prime[j] = false;
                }
            }
        }
    }

    // Two numbers are permutations if their sorted digits are equal.
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
         * Group all primes >= 1000 by their digits.
         */
        HashMap<String, ArrayList<Integer>> groups =
                new HashMap<String, ArrayList<Integer>>();

        for (int p = 1000; p < MAX; p++) {

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
         * Process each permutation group.
         */
        for (ArrayList<Integer> list : groups.values()) {

            if (list.size() < k) {
                continue;
            }

            Collections.sort(list);

            HashSet<Integer> set = new HashSet<Integer>(list);

            /*
             * Try every possible starting prime.
             */
            for (int i = 0; i < list.size(); i++) {

                int start = list.get(i);

                // Only the first element must be below limit.
                if (start >= limit) {
                    break;
                }

                /*
                 * Choose the second element.
                 * It determines the common difference.
                 */
                for (int j = i + 1; j < list.size(); j++) {

                    int difference = list.get(j) - start;

                    boolean valid = true;

                    /*
                     * Check:
                     *
                     * start
                     * start + difference
                     * start + 2*difference
                     * ...
                     */
                    for (int x = 2; x < k; x++) {

                        long value =
                                (long) start +
                                (long) x * difference;

                        if (value >= MAX ||
                            !set.contains((int) value)) {

                            valid = false;
                            break;
                        }
                    }

                    if (!valid) {
                        continue;
                    }

                    /*
                     * Build concatenated answer.
                     */
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
         * Numerical ordering of the smallest starting value.
         *
         * All numbers in a sequence have the same number
         * of digits, so sorting the concatenated strings gives
         * the required ordering.
         */
        Collections.sort(answers);

        for (String answer : answers) {
            System.out.println(answer);
        }

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler049/problem)