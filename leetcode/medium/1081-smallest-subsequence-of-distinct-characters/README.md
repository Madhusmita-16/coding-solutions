# Smallest Subsequence of Distinct Characters

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a string `s`, return  *the  **lexicographically smallest*   *subsequence**  of*  `s`  *that contains all the distinct characters of*  `s`  *exactly once*.

 

 **Example 1:** 

```
Input: s = "bcabc"
Output: "abc"

```

 **Example 2:** 

```
Input: s = "cbacdcbc"
Output: "acdb"

```

 

 **Constraints:** 

- 1 <= s.length <= 1000
- s consists of lowercase English letters.

 

 **Note:**  This question is the same as 316: https://leetcode.com/problems/remove-duplicate-letters/

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 96.65%)  
**Memory:** 43.1 MB (beats 24.39%)  
**Submitted:** 2026-08-20T18:01:21.307Z  

```java
class Solution {
    public String smallestSubsequence(String s) {
        int[] last = new int[26];
        boolean[] used = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int idx = c - 'a';

            if (used[idx]) {
                continue;
            }

            while (stack.length() > 0) {
                char top = stack.charAt(stack.length() - 1);

                if (top > c && last[top - 'a'] > i) {
                    used[top - 'a'] = false;
                    stack.deleteCharAt(stack.length() - 1);
                } else {
                    break;
                }
            }

            stack.append(c);
            used[idx] = true;
        }

        return stack.toString();
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/)