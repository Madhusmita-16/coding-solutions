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
**Submitted:** 2026-08-10T10:35:38.644Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    static final int LIMIT = 1000000;
    static boolean[] prime;

    // Sieve of Eratosthenes
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

    /*
     * Create a unique key based on digit frequencies.
     *
     * For example:
     * 1487 -> digits 1,4,7,8
     * 4817 -> digits 1,4,7,8
     *
     * Both get the same key.
     */
    static long getKey(int n) {

        int[] count = new int[10];

        while (n > 0) {
            count[n % 10]++;
            n /= 10;
        }

        long key = 0;

        for (int i = 0; i < 10; i++) {
            key = key * 11 + count[i];
        }

        return key;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        sieve();

        /*
         * Group primes having the same digits.
         */
        HashMap<Long, ArrayList<Integer>> groups =
                new HashMap<Long, ArrayList<Integer>>();

        for (int p = 2; p < LIMIT; p++) {

            if (!prime[p]) {
                continue;
            }

            long key = getKey(p);

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
             * Choose the first element.
             */
            for (int i = 0; i < list.size(); i++) {

                int start = list.get(i);

                // First element must be less than N.
                if (start >= n) {
                    break;
                }

                /*
                 * Choose the second element.
                 * This determines the common difference.
                 */
                for (int j = i + 1; j < list.size(); j++) {

                    int difference = list.get(j) - start;

                    boolean valid = true;

                    /*
                     * Check all remaining terms.
                     */
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
         * Every sequence has terms of the same digit length
         * within a permutation group, so lexicographical
         * ordering is sufficient for numeric ordering here.
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