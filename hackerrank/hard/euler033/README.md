# Project Euler #33: Digit canceling fractions

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

This problem is a programming version of Problem 33 from projecteuler.net

The fraction is a curious fraction. An inexperienced mathematician while attempting to simplify it may incorrectly believe that is obtained by cancelling the s.

We shall consider fractions like,, to be trivial examples.

Which means fractions where trailing 0's are cancelled are trivial. So we will ignore all the cases where we have to cancel 0's.

You will be given 2 integers and. represents the number of digits in Numerator and Denominator, and represents the exact number of digits to be "cancelled" from Numerator and Denominator. Find every non-trivial fraction, (1) where numerator is less than denominator, (2) and the value of the reduced fraction is equal to the original fraction.

Sum all the Numerators and the Denominators of the original fractions, and print them separated by a space.

 **Input Format** 

Input contains two integers

 **Constraints** 

 **Output Format** 

Display 2 space separated integers that denote the sum of the Numerators and the sum of the Denominators respectively of original fractions.
 **Note**  You do not have to reduce the Numerator and Denominator.

 **Sample Input** 

```
2 1

```

 **Sample Output** 

```
110 322

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T09:15:34.613Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    static class Pair {
        int remaining;
        int original;

        Pair(int remaining, int original) {
            this.remaining = remaining;
            this.original = original;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Pair)) return false;

            Pair p = (Pair) obj;

            return remaining == p.remaining &&
                   original == p.original;
        }

        @Override
        public int hashCode() {
            return Objects.hash(remaining, original);
        }
    }

    static int n, k;

    /*
     * key:
     * sorted digits that were cancelled
     *
     * value:
     * all (remaining number, original number) pairs
     */
    static HashMap<String, HashSet<Pair>> groups =
            new HashMap<>();

    static int start;
    static int end;

    static int[] digits;

    /*
     * Generate every combination of exactly k positions
     * that can be cancelled.
     */
    static void generateCombinations(
            int index,
            int selected,
            boolean[] removed) {

        if (selected == k) {

            processCombination(removed);
            return;
        }

        if (index == n) {
            return;
        }

        /*
         * Not enough positions left.
         */
        if (n - index < k - selected) {
            return;
        }

        // Select this position for cancellation.
        removed[index] = true;

        generateCombinations(
                index + 1,
                selected + 1,
                removed
        );

        removed[index] = false;

        // Do not select this position.
        generateCombinations(
                index + 1,
                selected,
                removed
        );
    }

    static void processCombination(boolean[] removed) {

        StringBuilder cancelled =
                new StringBuilder();

        StringBuilder remaining =
                new StringBuilder();

        for (int i = 0; i < n; i++) {

            if (removed[i]) {
                cancelled.append(digits[i]);
            } else {
                remaining.append(digits[i]);
            }
        }

        /*
         * Any cancelled zero makes the fraction trivial,
         * so ignore it.
         */
        if (cancelled.indexOf("0") >= 0) {
            return;
        }

        if (remaining.length() == 0) {
            return;
        }

        int remainingNumber =
                Integer.parseInt(
                        remaining.toString()
                );

        if (remainingNumber == 0) {
            return;
        }

        /*
         * Sort cancelled digits.
         * This means cancellation of the same digits
         * belongs to the same group regardless of position.
         */
        char[] c =
                cancelled.toString().toCharArray();

        Arrays.sort(c);

        String key = new String(c);

        groups
                .computeIfAbsent(
                        key,
                        x -> new HashSet<>()
                )
                .add(
                        new Pair(
                                remainingNumber,
                                numberFromDigits(digits)
                        )
                );
    }

    static int numberFromDigits(int[] d) {

        int result = 0;

        for (int x : d) {
            result = result * 10 + x;
        }

        return result;
    }

    static int[] toDigits(int number) {

        int[] result = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            result[i] = number % 10;
            number /= 10;
        }

        return result;
    }

    /*
     * Numbers divisible by 10 or by 11...1111
     * are excluded by the HackerRank interpretation.
     */
    static boolean usable(int number) {

        int d1;
        int d2;

        if (n == 2) {
            d1 = 10;
            d2 = 11;
        } else if (n == 3) {
            d1 = 100;
            d2 = 111;
        } else {
            d1 = 1000;
            d2 = 1111;
        }

        return number % d1 != 0 &&
               number % d2 != 0;
    }

    static long[] solve() {

        /*
         * These are the ranges used by the HackerRank
         * solution for this problem.
         */
        if (n == 2) {
            start = 12;
            end = 98;
        } else if (n == 3) {
            start = 101;
            end = 998;
        } else {
            start = 1001;
            end = 9998;
        }

        /*
         * Step 1:
         * For every usable N-digit number, generate all
         * possible ways of cancelling exactly K digits.
         */
        for (int number = start;
             number <= end;
             number++) {

            if (!usable(number)) {
                continue;
            }

            digits = toDigits(number);

            generateCombinations(
                    0,
                    0,
                    new boolean[n]
            );
        }

        /*
         * Step 2:
         * Within each cancelled-digit group, find pairs:
         *
         * child = numerator
         * parent = denominator
         *
         * such that:
         *
         * child < parent
         *
         * remainingChild < remainingParent
         *
         * child / parent =
         * remainingChild / remainingParent
         */
        HashSet<Long> answer =
                new HashSet<>();

        long sumNumerator = 0;
        long sumDenominator = 0;

        for (HashSet<Pair> set :
                groups.values()) {

            ArrayList<Pair> list =
                    new ArrayList<>(set);

            for (Pair a : list) {

                for (Pair b : list) {

                    if (a.equals(b)) {
                        continue;
                    }

                    /*
                     * Reduced/remaining numerator must
                     * be smaller.
                     */
                    if (a.remaining >= b.remaining) {
                        continue;
                    }

                    /*
                     * Original numerator must be smaller.
                     */
                    if (a.original >= b.original) {
                        continue;
                    }

                    /*
                     * Cross multiplication:
                     *
                     * a.original / b.original
                     *
                     * =
                     *
                     * a.remaining / b.remaining
                     */
                    if ((long) a.original *
                            b.remaining
                            !=
                        (long) b.original *
                            a.remaining) {

                        continue;
                    }

                    /*
                     * Prevent duplicate fractions caused
                     * by different cancellation positions.
                     */
                    long key =
                            ((long) a.original << 32)
                            ^ (b.original & 0xffffffffL);

                    if (answer.add(key)) {

                        sumNumerator += a.original;
                        sumDenominator += b.original;
                    }
                }
            }
        }

        return new long[]{
                sumNumerator,
                sumDenominator
        };
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();

        long[] result = solve();

        System.out.println(
                result[0] + " " + result[1]
        );
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler033/problem)