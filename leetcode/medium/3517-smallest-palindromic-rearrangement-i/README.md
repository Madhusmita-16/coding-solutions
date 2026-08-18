# Smallest Palindromic Rearrangement I

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a  **palindromic**  string `s`.

Return the  **lexicographically smallest**  palindromic permutation of `s`.

 

 **Example 1:** 

 **Input:**  s = "z"

 **Output:**  "z"

 **Explanation:** 

A string of only one character is already the lexicographically smallest palindrome.

 **Example 2:** 

 **Input:**  s = "babab"

 **Output:**  "abbba"

 **Explanation:** 

Rearranging `"babab"` → `"abbba"` gives the smallest lexicographic palindrome.

 **Example 3:** 

 **Input:**  s = "daccad"

 **Output:**  "acddca"

 **Explanation:** 

Rearranging `"daccad"` → `"acddca"` gives the smallest lexicographic palindrome.

 

 **Constraints:** 

- 1 <= s.length <= 105
- s consists of lowercase English letters.
- s is guaranteed to be palindromic.

## Solution

**Language:** Java  
**Runtime:** 35 ms (beats 33.78%)  
**Memory:** 48.3 MB (beats 21.38%)  
**Submitted:** 2026-08-18T14:29:44.963Z  

```java
class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder left = new StringBuilder();

        // Build the smallest possible left half
        for (int i = 0; i < 26; i++) {
            int count = freq[i] / 2;

            while (count-- > 0) {
                left.append((char) ('a' + i));
            }
        }

        // Find middle character for odd-length palindrome
        char middle = 0;

        if (s.length() % 2 == 1) {
            for (int i = 0; i < 26; i++) {
                if (freq[i] % 2 == 1) {
                    middle = (char) ('a' + i);
                    break;
                }
            }
        }

        // Right half is reverse of left half
        String right = new StringBuilder(left).reverse().toString();

        return left.toString()
                + (middle == 0 ? "" : String.valueOf(middle))
                + right;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/smallest-palindromic-rearrangement-i/)