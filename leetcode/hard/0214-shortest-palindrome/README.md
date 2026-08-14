# Q1. Shortest Palindrome

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given a string `s`. You can convert `s` to a palindrome by adding characters in front of it.

Return  *the shortest palindrome you can find by performing this transformation*.

 

 **Example 1:** 

```
Input: s = "aacecaaa"
Output: "aaacecaaa"

```

 **Example 2:** 

```
Input: s = "abcd"
Output: "dcbabcd"

```

 

 **Constraints:** 

- 0 <= s.length <= 5 * 104
- s consists of lowercase English letters only.

## Solution

**Language:** Java  
**Runtime:** 8 ms (beats 61.08%)  
**Memory:** 47.2 MB (beats 16.10%)  
**Submitted:** 2026-08-14T15:36:08.118Z  

```java
class Solution {
    public String shortestPalindrome(String s) {

        if (s == null || s.length() <= 1) {
            return s;
        }

        String rev = new StringBuilder(s).reverse().toString();

        // Find the longest prefix of s that is also a suffix of rev
        String combined = s + "#" + rev;

        int[] lps = new int[combined.length()];

        for (int i = 1; i < combined.length(); i++) {

            int j = lps[i - 1];

            while (j > 0 && combined.charAt(i) != combined.charAt(j)) {
                j = lps[j - 1];
            }

            if (combined.charAt(i) == combined.charAt(j)) {
                j++;
            }

            lps[i] = j;
        }

        // Length of longest palindromic prefix
        int palindromeLength = lps[combined.length() - 1];

        // Remaining suffix
        String suffix = s.substring(palindromeLength);

        // Add reversed suffix to the front
        return new StringBuilder(suffix)
                .reverse()
                .append(s)
                .toString();
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/shortest-palindrome/)