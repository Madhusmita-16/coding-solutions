# Distinct Subsequences II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given a string s, return  *the number of  **distinct non-empty subsequences**  of*  `s`. Since the answer may be very large, return it  **modulo**  `109 + 7`.

A  **subsequence**  of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., `"ace"` is a subsequence of `"abcde"` while `"aec"` is not.

 

 **Example 1:** 

```
Input: s = "abc"
Output: 7
Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".

```

 **Example 2:** 

```
Input: s = "aba"
Output: 6
Explanation: The 6 distinct subsequences are "a", "b", "ab", "aa", "ba", and "aba".

```

 **Example 3:** 

```
Input: s = "aaa"
Output: 3
Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".

```

 

 **Constraints:** 

- 1 <= s.length <= 2000
- s consists of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 3 ms (beats 89.36%)  
**Memory:** 43.3 MB (beats 56.03%)  
**Submitted:** 2026-09-07T14:15:03.066Z  

```java
class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;

        // last[c] = number of distinct subsequences
        // that were created ending with character c
        long[] last = new long[26];

        long total = 0;

        for (char ch : s.toCharArray()) {
            int c = ch - 'a';

            // Every existing subsequence can append ch,
            // and ch itself forms a new subsequence.
            long newSubseq = (total + 1) % MOD;

            // Remove the old contribution ending with ch
            // to avoid duplicate subsequences.
            total = (total + newSubseq - last[c] + MOD) % MOD;

            // Store the new contribution for this character
            last[c] = newSubseq;
        }

        return (int) total;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/distinct-subsequences-ii/)