# Smallest Palindromic Rearrangement II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given a  **palindromic**  string `s` and an integer `k`.

Return the  **k-th**   **lexicographically smallest**  palindromic permutation of `s`. If there are fewer than `k` distinct palindromic permutations, return an empty string.

 **Note:**  Different rearrangements that yield the same palindromic string are considered identical and are counted once.

 

 **Example 1:** 

 **Input:**  s = "abba", k = 2

 **Output:**  "baab"

 **Explanation:** 

- The two distinct palindromic rearrangements of "abba" are "abba" and "baab".
- Lexicographically, "abba" comes before "baab". Since k = 2, the output is "baab".

 **Example 2:** 

 **Input:**  s = "aa", k = 2

 **Output:**  ""

 **Explanation:** 

- There is only one palindromic rearrangement: "aa".
- The output is an empty string since k = 2 exceeds the number of possible rearrangements.

 **Example 3:** 

 **Input:**  s = "bacab", k = 1

 **Output:**  "abcba"

 **Explanation:** 

- The two distinct palindromic rearrangements of "bacab" are "abcba" and "bacab".
- Lexicographically, "abcba" comes before "bacab". Since k = 1, the output is "abcba".

 

 **Constraints:** 

- 1 <= s.length <= 104
- s consists of lowercase English letters.
- s is guaranteed to be palindromic.
- 1 <= k <= 106

## Solution

**Language:** Java  
**Runtime:** 37 ms (beats 20.48%)  
**Memory:** 47.6 MB (beats 13.34%)  
**Submitted:** 2026-08-18T14:28:28.855Z  

```java
class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Only the first half needs to be arranged.
        int[] half = new int[26];
        int halfLen = s.length() / 2;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
        }

        // Check whether at least k permutations exist.
        if (countPermutations(half, k) < k) {
            return "";
        }

        StringBuilder left = new StringBuilder();
        int remaining = halfLen;

        while (remaining > 0) {
            for (int i = 0; i < 26; i++) {
                if (half[i] == 0) {
                    continue;
                }

                // Try putting character i at the current position.
                half[i]--;
                long ways = countPermutations(half, k);

                if (ways >= k) {
                    // This character is part of the answer.
                    left.append((char) ('a' + i));
                    remaining--;
                    break;
                } else {
                    // Skip all permutations beginning with this character.
                    k -= ways;
                    half[i]++;
                }
            }
        }

        // Construct the palindrome.
        StringBuilder right = new StringBuilder(left).reverse();

        char middle = 0;

        if (s.length() % 2 == 1) {
            for (int i = 0; i < 26; i++) {
                if (freq[i] % 2 == 1) {
                    middle = (char) ('a' + i);
                    break;
                }
            }
        }

        return left.toString()
                + (middle == 0 ? "" : String.valueOf(middle))
                + right.toString();
    }

    // Returns the number of distinct permutations,
    // capped at LIMIT.
    private long countPermutations(int[] count, int LIMIT) {
        long result = 1;
        int used = 0;

        for (int c : count) {
            if (c == 0) {
                continue;
            }

            long combinations = binomialCapped(
                    used + c,
                    c,
                    LIMIT
            );

            result *= combinations;

            if (result >= LIMIT) {
                return LIMIT;
            }

            used += c;
        }

        return result;
    }

    // Computes C(n, r), but stops once it reaches LIMIT.
    private long binomialCapped(int n, int r, int LIMIT) {
        r = Math.min(r, n - r);

        long result = 1;

        for (int i = 1; i <= r; i++) {
            // Use BigInteger-like protection through a division-first
            // calculation using gcd.
            long a = n - r + i;
            long b = i;

            long g = gcd(a, b);
            a /= g;
            b /= g;

            g = gcd(result, b);
            result /= g;
            b /= g;

            // If multiplication would exceed LIMIT,
            // we only need to know that it is >= LIMIT.
            if (b > 1 && result >= (LIMIT + b - 1) / b) {
                return LIMIT;
            }

            result *= a;
            result /= b;

            if (result >= LIMIT) {
                return LIMIT;
            }
        }

        return result;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/smallest-palindromic-rearrangement-ii/)